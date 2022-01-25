<template>
  <div class="bm-article-list">
    <el-container>
      <el-main>
        <el-table
          :data="articles"
          style="width: 100%">
          <el-table-column
            prop="title"
            label="标题"
            width="180">
          </el-table-column>
          <el-table-column
            prop="viewCounts"
            label="浏览量"
            width="180">
          </el-table-column>
          <el-table-column prop="category" label="所属分类" width="180">
          </el-table-column>
          <el-table-column prop="isPublish" label="发布" width="80">
            <template slot-scope="scope">
            <el-switch
              v-model="scope.row.isPublish"
              active-value="1"
              inactive-value="0">
            </el-switch>
            </template>
          </el-table-column>
          <el-table-column prop="isWeight" label="置顶" width="80">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.isWeight"
                active-value="1"
                inactive-value="0">
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column prop="isWeight" label="开启评论" width="80">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.isComment"
                active-value="1"
                inactive-value="0">
              </el-switch>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @current-change="handleCurrentChange"
          background
          style="padding: 30px;text-align:right"
          layout="total, prev, pager, next"
          :page-size="page.limit"
          :total="page.total">
        </el-pagination>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import article from '@/api/article'
export default {
  name: 'ArticleList',
  data(){
    return{
      articles:[],
      page:{
        page:1,
        limit:5,
        currentPage:1,
        total:0
      }
    }
  },
  created() {
    this.getArticleList()
  },
  methods:{
    getArticleList(){
      article.getArticleList(this.page.page,this.page.limit)
      .then(result=>{
        this.articles = result.data.records
        this.page.currentPage = result.data.current
        this.page.total = result.data.total
      })
    },
    //使用当前页码进行查询
    handleCurrentChange(current) {
      this.page.page = current
      this.getArticleList()
    },
  }
}
</script>

<style scoped>
.www{
  color: #0879ec;
}
</style>
