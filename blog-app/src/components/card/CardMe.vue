<template>
  <div class="me-lbm">
  <el-card class="me-card">
    <el-image class="avatar" shape="square"  src="http://cdn.libomeng.cn/122d63d7-4ec9-4351-8aac-c6ad518b99dd.jpg"></el-image>
    <h4 class="me-author-name">李搏猛</h4>
    <div class="em-count-box">
      <div class="em-count">
        <el-link href="/#/archives">{{articleCount}}<br/>文章</el-link>
      </div>
      <div class="em-count">
       <el-link href="/#/category/all">{{categoryCount}}<br/>分类</el-link>
      </div>
      <div class="em-count">
       <el-link href="/#/tags/all">{{tagCount}}<br/>标签</el-link>
      </div>
    </div>
    <br/>
    <div class="bm-collapse">
      <el-collapse>
        <el-collapse-item title="擅长的语言" name="1">
          <div>当然是JAVA！</div>
        </el-collapse-item>
        <el-collapse-item title="喜欢的日漫" name="2">
          <div>火影忍者</div>
          <div>死神</div>
        </el-collapse-item>
        <el-collapse-item title="喜欢的音乐" name="3">
          <div><i class="el-icon-video-play"></i> <el-link>Moment Apart</el-link></div>
        </el-collapse-item>
      </el-collapse>
    </div>
  </el-card>
  </div>

</template>

<script>
  import {getArticleCount} from '@/api/article'
  import {getTagTotal} from '@/api/tag'
  import {getCategoryCount} from '@/api/category'
  export default {
    name: 'CardMe',
    data() {
      return {
        articleCount:0,
        tagCount:0,
        categoryCount:0,
      }
    },
    created() {
      this.getArticleCount()
      this.getTagCount()
      this.getCategoryCount()
    },
    methods: {
      showTool(tool) {
        this.$message({
          duration: 0,
          showClose: true,
          dangerouslyUseHTMLString: true,
          message: '<strong>' + tool.message + '</strong>'
        });
      },
      getArticleCount(){
        let that=this
        getArticleCount().then(result =>{
           that.articleCount = result.data
        })
      },
      getTagCount(){
        let that=this
        getTagTotal().then(result =>{
          that.tagCount = result.data
        })
      },
      getCategoryCount(){
        let that=this
        getCategoryCount().then(result=>{
          that.categoryCount = result.data
        })
      }
    }
  }
</script>

<style scoped>
  .me-author-name {
    text-align: center;
    margin-top: 10px;
  }

  .me-author-description {
    padding: 8px 0;
  }

  .me-icon-job {
    padding-left: 16px;
  }

  .me-author-tool {
    text-align: center;
    padding-top: 10px;
  }

  .me-author-tool i {
    cursor: pointer;
    padding: 4px 10px;
    font-size: 30px;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  .em-info{
    padding-top: 10px;
  }
  .me-lbm{
    width: 220px;
    background-color: #2C3E50;
  }
  .me-card{

  }
  .em-count{
    float: left;
    width: 33.3%;
    text-align:center
  }
  .em-count-box{
    margin-top: 20px;
    margin-bottom: 15px;
    height: 30px;
  }
  .bm-collapse{
    width: 100%
  }
</style>
