<template>
  <div class="app-container">
    <el-table
      :data="articleList"
      style="width: 100%"
    >
      <el-table-column
        prop="title"
        label="标题"
        min-width="80"
      >
      </el-table-column>
      <el-table-column prop="categoryName" label="所属分类" width="80">
      </el-table-column>
      <el-table-column prop="gmtCreate" label="删除时间" width="200">
        <template slot-scope="scope">
          {{ scope.row.gmtModified | moment('YYYY-MM-DD HH:mm:ss') }}
        </template>
      </el-table-column>
      <el-table-column
        label="操作" width="200"
      >
        <template slot-scope="scope">
          <el-row>
            <el-col :span="12">
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-refresh"
                @click="recycle(scope.row.id)"
              >回收
              </el-button>
            </el-col>
            <el-col :span="12">
              <el-button
                size="mini"
                type="danger"
                icon="el-icon-delete"
                @click="removeCompletely(scope.row)"
              >彻底删除
              </el-button>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import article from '@/api/article'

export default {
  name: 'RemoveList',
  data() {
    return {
      articleList: [],
      visible: false
    }
  },
  created() {
    this.getArticleList()
  },
  methods: {
    getArticleList() {
      article.getDeletedArticle().then(result => {
        this.articleList = result.data
      })
    },
    recycle(id) {
      article.recycleArticle(id).then(result => {
        this.$message.success('回收文章成功')
        this.getArticleList()
      })
    },
    removeCompletely(art) {
      this.$confirm(`此操作将删除文章,此操作不可逆，是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        article.deleteArticle(art.id, art.bodyId).then(result => {
          this.visible = false
          this.$message.success('文章删除成功')
          this.getArticleList()
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
