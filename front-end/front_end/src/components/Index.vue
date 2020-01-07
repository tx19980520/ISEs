<template>
  <el-container style="border: 0 solid #eee">
    <el-header style="padding-left: 0;">
      <section class="text-white tm-font-big tm-parallax">
        <nav class="navbar navbar-expand-md tm-navbar scroll" id="tmNav">
          <div class="container">
            <div class="tm-next">
              <img src="../../public/img/logo.png" width="45px">
              <a class="navbar-brand" @click="toIndex()">仓储管理系统</a>
            </div>
          </div>
        </nav>
      </section>
    </el-header>
    <el-container style="padding-top: 20px;">
      <el-aside width="150px" style="background-color: rgb(238, 241, 246)">
        <el-menu default-active="1" style="height:100vh;width:150px;float:left;overflow-y:auto;">
          <div align="center" style="padding-bottom: 15px;">
            <i class="fas fa-3x fa-user-circle text-center tm-icon"></i>
            <div style="clear:both"></div>
            <div style="clear:both"></div>
          </div>
          <el-menu-item index="1" @click="toPage(1, 1)">
            <i class="el-icon-folder-add"></i>
            <span slot="title" style="font-size: 16px">新增入库</span>
          </el-menu-item>
          <el-menu-item index="2" @click="toPage(2, 2)">
            <i class="el-icon-folder-opened"></i>
            <span slot="title" style="font-size: 16px">查看全部</span>
          </el-menu-item>
          <el-menu-item index="3" @click="toPage(3, 3)">
            <i class="el-icon-folder-opened"></i>
            <span slot="title" style="font-size: 16px">查看车流</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <keep-alive>
          <component :is="tabView"></component>
        </keep-alive>
      </el-main>
    </el-container>
    <el-dialog title="警报" :visible.sync="dialogFormVisible" width="400px">
      <el-form >
        <el-form-item label="设备" >
          {{data.id}}
        </el-form-item>
        <el-form-item label="情况">
          {{data.alertType}}
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleDetailFinish()">确定</el-button>
      </div>
    </el-dialog>
  </el-container>
</template>

<script>
import page1 from './InWarehouse.vue'
import page2 from './ShowAll.vue'
import page3 from './ShowTraffic.vue'
export default {
  name: 'Index',
  data () {
    return {
      tabView: 'page1',
      openList: ['1'],
      dialogFormVisible: false,
      message: 'a',
      data: ''
    }
  },
  methods: {
    handleDetailFinish () {
      this.dialogFormVisible = false
    },
    toIndex () {
    },
    toPage (id1, id2) {
      this.openList[0] = id1
      this.tabView = `page${id2}`
    },
    loadData () {
    }
  },
  components: {
    page1,
    page2,
    page3
  },
  mounted () {
    this.loadData()
    let that = this
    this.message = 'c'
    const source = new EventSource('/stream-sse/connecttime')
    source.onopen = function () {
      that.message = 'connected'
      window.console.log(that.message)
    }

    source.addEventListener('connecttime', function (event) {
      // 测试成功，稳定sse时使用窗口
      that.dialogFormVisible = true
      that.data = event.data
      that.data = JSON.parse(that.data)
      window.console.log(that.data)
    }, false)
  }
}
</script>

<style scoped>

</style>
