<template>
  <div v-title data-title="码神之路">
    <el-container>
<!--      <el-aside>-->
<!--        <card-me ></card-me>-->
<!--&lt;!&ndash;        <card-tag :tags="hotTags"></card-tag>&ndash;&gt;-->
<!--&lt;!&ndash;        <card-article cardHeader="最热文章" :articles="hotArticles"></card-article>&ndash;&gt;-->

<!--&lt;!&ndash;        <card-archive cardHeader="文章归档" :archives="archives"></card-archive>&ndash;&gt;-->

<!--&lt;!&ndash;        <card-article cardHeader="最新文章" :articles="newArticles"></card-article>&ndash;&gt;-->

<!--      </el-aside>-->
      <div class="em-container-me" v-show="screenWidth>1100">
      <card-me ></card-me>
        <card-server class="bm-container-server" :viewCount="viewCount"></card-server>
      </div>
      <el-main :style="mainClass">
        <article-scroll-page></article-scroll-page>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import CardMe from '@/components/card/CardMe'
  import CardArticle from '@/components/card/CardArticle'
  import CardArchive from '@/components/card/CardArchive'
  import CardTag from '@/components/card/CardTag'
  import ArticleScrollPage from '@/views/common/ArticleScrollPage'
  import CardServer from "@/components/card/CardServer";
  import count from "@/api/count";
  import {getArticles, getHotArtices, getNewArtices} from '@/api/article'
  import {getHotTags} from '@/api/tag'
  import {listArchives} from '@/api/article'

  export default {
    name: 'Index',
    created() {
      this.count()
      this.listArchives()
      this.mainHandler()
      let Ip=returnCitySN['cip']
      let cityname=returnCitySN['cname']
      console.log(Ip,cityname)
    },
    data() {
      return {
        hotTags: [],
        hotArticles: [],
        newArticles: [],
        archives: [],
        mainClass:'',
        viewCount:0,
      }
    },
    props:{
      screenWidth:Number
    },

    watch:{
      screenWidth(val){
        // 为了避免频繁触发resize函数导致页面卡顿，使用定时器
        if(!this.timer){
          // 一旦监听到的screenWidth值改变，就将其重新赋给data里的screenWidth
          this.screenWidth = val
          this.timer = true
          let that = this
          setTimeout(function(){
            // 打印screenWidth变化的值
            // console.log(that.screenWidth)
            that.mainHandler()
            that.timer = false
          },400)
        }
      }
    },
    methods: {
      getHotArtices() {
        let that = this
        getHotArtices().then(data => {
          that.hotArticles = data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '最热文章加载失败!', showClose: true})
          }

        })

      },
      getNewArtices() {
        let that = this
        getNewArtices().then(data => {
          that.newArticles = data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '最新文章加载失败!', showClose: true})
          }

        })

      },
      getHotTags() {
        let that = this
        getHotTags().then(data => {
          that.hotTags = data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '最热标签加载失败!', showClose: true})
          }

        })
      },
      listArchives() {
        listArchives().then((data => {
          this.archives = data.data
        })).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '文章归档加载失败!', showClose: true})
          }
        })
      },
      mainHandler(){
        if(this.screenWidth<1100){
         this.mainClass = `margin-left: 0%;margin-right: 0%;`
        }else {
          this.mainClass = `margin-left: 25%;margin-right: 25%;`
        }
      },
      count(){
        count.count()
        .then(result=>{
          this.viewCount = result.data
        })
      }
    },
    components: {
      'card-me': CardMe,
      'card-article': CardArticle,
      'card-tag': CardTag,
      ArticleScrollPage,
      CardArchive,
      CardServer,
    }
  }
</script>

<style scoped>
  .me-area{

  }
  .el-container {
    width: 100%;
  }

  /deep/ .el-aside {
    margin-left: 5%;
    margin-right: 5%;
    width: 15%;
  }

  .bm-articles-simple {

  }
  .bm-articles{
    padding: 0px;
    margin-left: 25%;
    margin-right: 25%;
  }

  .el-card {
    border-radius: 0;
  }

  .el-card:not(:first-child) {
    margin-top: 20px;
  }
  .em-container-me{
    position: fixed;
    margin-top: 20px;
    margin-left: 40px;
  }
  .bm-container-server{
    margin-top: 20px;

  }
</style>
