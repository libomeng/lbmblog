<template>
  <el-card class="me-area" :body-style="{ padding: '16px' }">

    <div class="me-article-info">
      <div class="me-article-header">
      <el-link @click="view(id)" class="me-article-title">{{title}}</el-link>
        <el-tag v-if="isWeight > 0" size="mini">置顶</el-tag>
      </div>
      <div class="me-article-time">
        <span>
	    	  <i class="el-icon-date"></i>&nbsp;发表于&nbsp;{{gmtCreate|moment("YYYY-MM-DD HH:mm")}} &nbsp;&nbsp;|&nbsp;&nbsp;  <i class="el-icon-folder-opened"/>分类于
          <a v-if="category" style="text-decoration: underline">{{category.categoryName }}</a>&nbsp;&nbsp;|&nbsp;&nbsp;<i class="el-icon-view"></i>&nbsp;{{viewCounts}} &nbsp;&nbsp;|&nbsp;&nbsp;<i class="el-icon-chat-dot-round"></i>&nbsp;{{commentCounts}}
	      </span>
    </div>
      <div class="me-article-description">
      {{summary}}
      </div>
      <div class="em-article-images">
        <el-image class="article-images" :src="img" :preview-src-list="arrayImg" fit="cover"></el-image>
      </div>
      <div class="el-article-button" >
        <el-button  icon="el-icon-right" type="primary" size="mini"  @click="view(id)">阅读原文</el-button>
      </div>
      <div class="me-article-footer">
	  	<span class="me-article-author">
	    	<i class="me-icon-author"></i>&nbsp;{{author}}
	    </span>
      <el-tag v-for="t in tags" :key="t.tagName" size="mini" >{{t.tagName}}</el-tag>
    </div>
    </div>
  </el-card>

</template>

<script>
  export default {
    name: 'ArticleItem',
    props: {
      id: String,
      isWeight: Number,
      title: String,
      commentCounts: Number,
      viewCounts: Number,
      summary: String,
      author: String,
      tags: Array,
      gmtCreate: String,
      category:Object,
      img:String
    },
    data() {
      return {
      }
    },
    computed :{
      arrayImg(){
        return new Array(this.img)
      }
    },
    methods: {
      view(id) {
        this.$router.push({path: `/view/${id}`})
      }
    }
  }
</script>

<style scoped>
  .me-area{

  }
  .me-article-info{
  }
  .me-article-header {
  }

  .me-article-title {
    font-weight: 500;
    font-size: 26px;
    color: #555;
  }

  .me-article-icon {
    padding: 3px 8px;
  }

  .me-article-time{
    margin-top:14px;
    color: #a6a6a6;
    font-size: 13px;
  }

  .me-article-description {
    margin-top:24px;
    font-size: 14px;
    line-height: 24px;
    margin-bottom: 10px;

  }

  .me-article-author {
    color: #a6a6a6;
    padding-right: 18px;
    font-size: 13px;
  }

  .el-tag {
    margin-left: 6px;
  }

  .me-article-footer{
     margin-top: 20px;
  }
  .el-article-button{
  text-align: center;
  }
  .em-article-images {
    margin-top: 10px;
  }
  .article-images{
    width: 100%;
    height: 500px;
  }
</style>
