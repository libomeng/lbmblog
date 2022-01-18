<template>
  <div>
    <el-card class="bm-server">
      <div>
      <h4>网站已稳定运行14天！</h4>
      </div>
      <div class="bm-task-box">
      <el-divider content-position="left">每日任务</el-divider>
        <p style="font-size: 14px">浏览量</p>
        <el-progress class="bm-server-Task1" :text-inside="true" :stroke-width="26" :percentage="(viewCount/taskCount)*100" :format="viewTask"></el-progress></div>
    </el-card>
  </div>
</template>

<script>
    export default {
        name: "CardServer",
      data(){
          return{
            taskCount:300
          }
      },
      props:{
        viewCount:Number
      },
      methods:{
        viewTask(){
          if(this.viewCount>this.taskCount){
            return "我滴任务完成啦"
          }
          return `${this.viewCount}/${this.taskCount}`
        },
      }
    }
</script>

<style scoped>
  .bm-server{

  }
.bm-server-Task1{
  margin-top: 5px;
}
.bm-task-box{
    margin-top: 40px;
}
</style>
