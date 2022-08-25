<template>
  <div v-title data-title="李搏猛的博客">

    <!--    <el-container style="width: 100%">-->
    <!--      <el-aside>-->
    <!--        <card-me ></card-me>-->
    <!--&lt;!&ndash;        <card-tag :tags="hotTags"></card-tag>&ndash;&gt;-->
    <!--&lt;!&ndash;        <card-article cardHeader="最热文章" :articles="hotArticles"></card-article>&ndash;&gt;-->

    <!--&lt;!&ndash;        <card-archive cardHeader="文章归档" :archives="archives"></card-archive>&ndash;&gt;-->

    <!--&lt;!&ndash;        <card-article cardHeader="最新文章" :articles="newArticles"></card-article>&ndash;&gt;-->
    <el-container>
      <el-main>
        <el-row>
          <el-col :span="screenWidth>1100?5:0">
            <div v-show="screenWidth>1100">
              <card-me v-bind="$attrs" v-on="$listeners"></card-me>
              <card-server class="bm-container-server" :viewCount="viewCount"></card-server>
            </div>
          </el-col>
          <el-col :offset="screenWidth>800 && screenWidth<1100?5:0" :span="screenWidth>800?14:24">
            <article-scroll-page></article-scroll-page>
          </el-col>
          <el-col :offset="screenWidth>800 && screenWidth<1100?5:0.5" :span="screenWidth>1100?4.5:0">
            <div v-show="screenWidth>1100">
            <card-article cardHeader="热门文章" :articles="hotArticles"></card-article>
            <card-article cardHeader="最新文章" :articles="newArticles"></card-article>
            </div>
          </el-col>
        </el-row>
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
    this.getHotArtices()
    this.getNewArtices()
    this.mainHandler()
  },
  data() {
    return {
      hotTags: [],
      hotArticles: [],
      newArticles: [],
      mainClass: '',
      viewCount: 0,
    }
  },
  props: {
    screenWidth: Number,
  },
  watch: {
    screenWidth(val) {
      // 为了避免频繁触发resize函数导致页面卡顿，使用定时器
      if (!this.timer) {
        // 一旦监听到的screenWidth值改变，就将其重新赋给data里的screenWidth
        this.screenWidth = val
        this.timer = true
        let that = this
        setTimeout(function () {
          // 打印screenWidth变化的值
          // console.log(that.screenWidth)
          that.mainHandler()
          that.timer = false
        }, 400)
      }
    }
  },
  methods: {
    mainHandler() {
      if (this.screenWidth < 1100) {
        this.mainClass = `margin-left: 0%;margin-right: 0%;`
      } else {
        this.mainClass = `margin-left: 15%;margin-right: 15%;`
      }
    },
    count() {
      count.count()
        .then(result => {
          this.viewCount = result.data
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

.el-card:not(:first-child) {
  margin-top: 20px;
}

.bm-container-server {
  margin-top: 20px;
  width: 218px;
}

</style>
