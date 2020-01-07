<template>
  <div>
    <div align="center">
      <el-input v-model="search" maxLength="25" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入车牌号搜索"/>
      <el-row>
        <template>
          <p style="font-size: 16px; padding-top: 10px;">
            起始时间:
            <el-input v-model="starttime" type="date" size="mini" style="width: 150px;"/>
            终止时间:
            <el-input v-model="endtime"   type="date" size="mini" style="width: 150px;"/>
            <el-button size="small" @click="statistics()">确定</el-button>
          </p>
        </template>
      </el-row>
    </div>
    <div>
      <el-row :gutter="20">
        <el-col :span="12" :offset="2">
          <el-card class="box-card" style="width: 900px;">
            <el-table :data="data_show.filter(data => !search || data.traffic.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
              <el-table-column prop="time" label="时间" align="center"></el-table-column>
              <el-table-column prop="traffic" label="车牌号" align="center"></el-table-column>
<!--              <el-table-column label="查看" align="center">-->
<!--                <template slot-scope="scope">-->
<!--                  <el-button size="medium" type="primary" icon="el-icon-view" circle @click="handleDetail(scope.row)"></el-button>-->
<!--                </template>-->
<!--              </el-table-column>-->
            </el-table>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'showTraffic',
  data () {
    return {
      search: '',
      starttime: '',
      endtime: '',
      traffic: [
        {
          time: '2019-11-02 12:23:12',
          traffic: '沪BW232'
        }
      ],
      data_show: []
    }
  },
  mounted () {
    this.loadData()
    this.data_show = this.traffic
  },
  methods: {
    loadData () {
    },
    handleDetail () {
    },
    statistics () {
      // eslint-disable-next-line camelcase
      let data_all = this.traffic.slice()
      this.data_show = []
      while (data_all.length > 0) {
        let data = data_all.pop()
        if ((!this.starttime || data.time >= this.starttime) && (!this.endtime || data.time <= this.endtime)) {
          this.data_show.push(data)
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
