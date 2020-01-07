<template>
  <div>
    <p>{{message}}</p>
    <el-dialog title="警报" :visible.sync="dialogFormVisible" width="400px">
      <el-form >
        <el-form-item label="设备">
          <p>{{data.id}}</p>
        </el-form-item>
        <el-form-item label="情况">
          <p>{{data.alertType}}</p>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleDetailFinish()">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: 'sse-test',
    data() {
      return {
        message: "a",
        data:'',
        dialogFormVisible: false
      };
    },
    mounted() {
      let that = this;
      this.message = 'c';
      const source = new EventSource('/stream-sse/connecttime');
      source.onopen = function () {
        that.message = 'connected';
        window.console.log(that.message);
      };

      source.addEventListener('connecttime', function (event) {
        that.dialogFormVisible = true;
        that.data = event.data;
        that.data = JSON.parse(that.data);
        window.console.log(that.data);
      }, false);
    },
    methods: {
      handleDetailFinish() {
        this.dialogFormVisible = false;
      }
    }
  };
</script>
