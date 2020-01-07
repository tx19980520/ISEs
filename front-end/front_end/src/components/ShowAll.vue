<template>
  <div>
    <div align="center" v-if="showAll">
      <el-input v-model="search" maxLength="25" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入物品名关键字搜索"/>
    </div>
    <div style="padding-top: 10px;" v-if="showAll">
      <el-row :gutter="20">
        <el-col :span="12" :offset="2">
          <el-card class="box-card" style="width: 900px;">
            <el-table :data="goodsData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
              <el-table-column prop="name" label="物品名" align="center"></el-table-column>
              <el-table-column prop="pledgeId" label="抵押人" align="center"></el-table-column>
              <el-table-column prop="status" label="状态" align="center">
                <template slot-scope="scope">
                  <el-tag v-if="scope.row.logicalState === 'INWAREHOUSING'">入库</el-tag>
                  <el-tag v-if="scope.row.logicalState === 'INWAREHOUSED'" type="success">在库</el-tag>
                  <el-tag v-if="scope.row.logicalState === 'EXWAREHOUSING'" type="danger">出库</el-tag>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                  <el-button size="medium" type="primary" icon="el-icon-view" circle @click="handleDetail(scope.row)"></el-button>
                  <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.row)"></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-col>
      </el-row>
    </div>
<!--查看物品详情-->
    <div v-if="!showAll">
      <el-row :gutter="20">
        <el-col :span="12" :offset="2">
          <el-card class="box-card" style="width: 900px;">
            <div slot="header" class="clearfix">
              <span style="font-size: 16px;">物品信息</span>
              <el-button style="float: right;" size="mini" type="danger" plain icon="el-icon-back" @click="goBack()">返回</el-button>
              <el-button size="mini" type="primary" plain icon="el-icon-view" @click="showChart()">查看统计图表</el-button>
              <el-button size="mini" type="primary" plain icon="el-icon-view" @click="showImage()">查看摄像头图像</el-button>
            </div>
            <el-form label-width="110px" :model="correntGoods" :inline="true">
              <el-form-item label="物品名">
                <el-input v-model="correntGoods.name" disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="温度">
                <el-input v-model="correntGoods.t" disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="湿度">
                <el-input v-model="correntGoods.s" disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="逻辑状态">
                <el-input v-model="correntGoods.logicalState" disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="位置信息">
                <el-input v-model="correntGoods.location.locationId" disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="描述">
                <el-input v-model="correntGoods.location.description" type="textarea" :autosize="{ minRows: 2, maxRows: 5}" style="width: 715px;" disabled="true"></el-input>
              </el-form-item>
            </el-form>
          </el-card>
          <el-card class="box-card" style="width: 900px;" >
            <div id="myChart" :style="{width: '700px', height: '300px'}"></div>
          </el-card>
          <el-card class="box-card" style="width: 900px;" id="myCamera" href="#myCamera" v-if="showDetail==2">
            <span v-for="(x, index) in pictures" :key="index">
              <img :src="x['picture']" style="width: 280px;">
            </span>
          </el-card>
          <el-card id="Camera" href="#Camera" class="box-card" style="width: 900px;height: 300px">
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ShowAll',
  data () {
    return {
      search: '',
      showAll: true,
      showDetail: 1,
      correntGoods: {
        // name: '钢卷',
        // time: [
        //   '2019-11-15 00:44:53',
        //   '2020-02-13 00:44:53'
        // ],
        // owner: '张三',
        // phone: '1234567890',
        // content: '无'
      },
      goodsData: [
        // {
        //   name: '钢卷',
        //   pledgeId: 0,
        //   status: 0
        // },
        // {
        //   name: '钢卷',
        //   pledgeId: 1,
        //   status: 1
        // },
        // {
        //   name: '钢卷',
        //   pledgeId: 2,
        //   status: 2
        // }
      ],
      pictures: []
    }
  },
  mounted () {
    this.loadAll()
  },
  methods: {
    loadAll () {
      let bodyFormData = new FormData()
      let url = '/pledge/getAll'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        console.log(response.data)
        this.goodsData = response.data
      })
    },
    getGoods (goods) {
      let url = '/pledge/getOne'
      this.$axios.get(url, {
        params: {
          pledgeID: goods.pledgeId
        }
      }).then(response => {
        console.log(response.data)
        this.correntGoods = response.data
      })
    },
    handleDetail (row) {
      this.getGoods(row)
      this.showAll = false
    },
    handleDelete (row) {
      let url = 'http://106.15.225.249:3031/pledge/exwarehousing'
      this.$axios.get(url, {
        params: {
          id: row.pledgeId
        }
      }
      ).then(response => {
        console.log(response)
        this.$message({
          type: 'success',
          message: response.data
        })
      })
    },
    showImage () {
      this.showDetail = 2
      document.querySelector('#Camera').scrollIntoView(true)
      let url = 'http://106.15.225.249:3031/pledge/image'
      this.$axios({
        method: 'get',
        url: url }
      ).then(response => {
        this.pictures = response.data
        for (let i = 0; i < this.pictures.length; i++) {
          this.pictures[i]['picture'] = 'data:image/jpg;base64,' + this.pictures[i]['picture']
        }
      })
    },
    showChart () {
      this.showDetail = 1
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('myChart'))
      // 绘制图表
      myChart.setOption({
        tooltip: {},
        xAxis: {
          name: '日期',
          nameLocation: 'end',
          nameTextStyle: {
            padding: -24
          },
          data: ['2018-12-01 00:01:00', '2018-12-01 00:02:00', '2018-12-01 00:03:00', '2018-12-01 00:04:00', '2018-12-01 00:05:00',
            '2018-12-01 00:06:00', '2018-12-01 00:07:00', '2018-12-01 00:08:00', '2018-12-01 00:09:00', '2018-12-01 00:10:00',
            '2018-12-01 00:11:00', '2018-12-01 00:12:00', '2018-12-01 00:13:00', '2018-12-01 00:14:00', '2018-12-01 00:15:00',
            '2018-12-01 00:16:00', '2018-12-01 00:17:00', '2018-12-01 00:18:00', '2018-12-01 00:19:00', '2018-12-01 00:20:00',
            '2018-12-01 00:21:00', '2018-12-01 00:22:00', '2018-12-01 00:23:00', '2018-12-01 00:24:00', '2018-12-01 00:25:00',
            '2018-12-01 00:26:00', '2018-12-01 00:27:00', '2018-12-01 00:28:00', '2018-12-01 00:29:00', '2018-12-30 00:30:00',
            '2018-12-01 00:31:00', '2018-12-01 00:32:00', '2018-12-01 00:33:00', '2018-12-01 00:34:00', '2018-12-01 00:35:00'],
          type: 'category',
          boundaryGap: false
        },
        grid: [{
          left: '20%',
          bottom: '20%',
          top: '20%',
          right: '0%'
        }],
        dataZoom: [
          { // 这个dataZoom组件，默认控制x轴。
            type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
            start: 0, // 左边在 0% 的位置。
            end: 20 // 右边在 30% 的位置。
          }
        ],
        color: ['#dcb7fe'],
        yAxis: {
          type: 'value',
          name: '分数',
          nameLocation: 'end',
          nameTextStyle: {
            padding: -8
          },
          axisLabel: {
            formatter: function (value) {
              var texts = []
              if (value === 1) {
                texts.push('稳定')
              } else if (value === 2) {
                texts.push('有震动')
              }
              return texts
            }
          }
        },
        series: [
          {
            data: [1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
            type: 'line'
          }
        ]
      })
    },
    goBack () {
      this.pictures = []
      this.showAll = true
    }
  }
}
</script>

<style scoped>

</style>
