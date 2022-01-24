<template>
  <div v-title :data-title="$route.params.title?`${$route.params.title}-文章归档`:'文章归档'">
    <el-container>
      <el-main class="me-articles" v-if="reFresh">
        <span class="bm-tag-title" v-if="this.$route.params.title">{{$route.params.title}}</span>
        <el-timeline :reverse="false" class="bm-timeline">
          <el-timeline-item v-if="simple" placement="top" size="large"  style="line-height:50px">
            <h3>目前共{{articles.length}}篇文章，继续努力！</h3>
          </el-timeline-item>
          <template v-for="y in year">
          <el-timeline-item v-if="simple" placement="top" size="large" style="line-height:50px">
            <h2>{{y}}</h2>
          </el-timeline-item>
          <el-timeline-item style="line-height:20px" placement="top"
            v-for="article in articles"
            v-if="DateHandler(article,y)"
            :key="article.id"
            :timestamp="article.gmtCreate | moment('YYYY-MM-DD HH:mm')">
            <el-link @click="view(article.id)">{{article.title}}</el-link>
            <el-divider style="margin: 2px"></el-divider>
          </el-timeline-item>
        </template>
        </el-timeline>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import ArticleScrollPage from '@/views/common/ArticleScrollPage'
  import {getArticleSample} from '@/api/article'
  import { formatTime } from '@/utils/time'
  import moment from 'moment'
  export default {
    name: "BlogArchive",
    data(){
      return{
        reFresh:true,
        articles:[],
        query:{
          tagId:'',
          categoryId:''
        },
        simple:true
      }
    },
    created(){
     this.routeHandler()
    },
    computed:{
      year(){
        const arr=[]
        this.articles.forEach((item,index)=>{
          let year = moment(item.gmtCreate).format("YYYY")
          if(!arr.includes(year)){
            arr.push(year)
          }
        })
        return arr
      }
    },
    watch: {
      //路由参数改变时从新判断参数
      $route(){
        this.routeHandler()
      },
    },
      methods: {
        getArticle(val) {
          getArticleSample(val).then(result => {
            this.articles = result.data
          })
        },
        view(id) {
          this.$router.push({path: `/view/${id}`})
        },
        simpleHandler() {
          if (this.query.tagId || this.query.categoryId) {
            this.simple = false
          } else {
            this.simple = true
          }
        },
        //判断路由传来参数的方法
        routeHandler() {
          if (this.$route.params.type && this.$route.params.id) {
            if (this.$route.params.type === 'tag') {
              this.query.tagId = this.$route.params.id
            }
            if (this.$route.params.type === 'category') {
              this.query.categoryId = this.$route.params.id
            }
            this.simpleHandler()
            this.getArticle(this.query);
          } else {
            this.query={}
            this.simpleHandler()
            this.getArticle(this.query);
          }
        },
        Handler(){


        },
        DateHandler(article,y){
        return moment(article.gmtCreate).format('YYYY')===y
        }
      }
  }
</script>

<style scoped>

  .el-container {
    width: 100%;
  }

  .el-main {
    padding: 0px;
    line-height:30px;

  }

 @media screen and (min-width: 1000px) {
    .el-main {
      padding: 0px;
      line-height:30px;
      width: 750px;
   }
}


  .bm-timeline{
      margin-left: 4px;
  }
  .el-divider--horizontal{
    margin: 4px 0;
  }
  .bm-tag-title{
  font-size: 26px;
    display: block;
    margin-bottom: 40px ;
    color: #323232;
  }
</style>
