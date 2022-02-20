<template>
  <div class="bm-article-list">
    <el-container>
      <el-main>
        <el-table
          :data="articles"
          style="width: 100%"
        >
          <el-table-column
            prop="title"
            label="标题"
            min-width="80"
          >
          </el-table-column>
          <el-table-column
            prop="viewCounts"
            label="浏览量"
            width="80"
          >
          </el-table-column>
          <el-table-column prop="category" label="所属分类" width="80">
          </el-table-column>
          <el-table-column prop="isPublish" label="发布" width="80">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.isPublish"
                :active-value=1
                :inactive-value=0
                @change="articleStateHandler(scope.row)"
              >
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column prop="isWeight" label="置顶" width="80">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.isWeight"
                :active-value=1
                :inactive-value=0
                @change="articleStateHandler(scope.row)"
              >
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column prop="isWeight" label="开启评论" width="80">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.isComment"
                :active-value=1
                :inactive-value=0
                @change="articleStateHandler(scope.row)"
              >
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column prop="gmtCreate" label="修改时间" width="150">
            <template slot-scope="scope">
              {{ scope.row.gmtCreate | moment('YYYY-MM-DD HH:mm:ss') }}
            </template>
          </el-table-column>
          <el-table-column
            label="操作" width="180"
          >
            <template slot-scope="scope">
              <el-row>
                <el-col :span="12">
                  <el-button
                    size="mini"
                    type="primary"
                    icon="el-icon-edit"
                    @click="edit(scope.row)"
                  >编辑
                  </el-button>
                </el-col>
                <el-button
                  size="mini"
                  type="danger"
                  icon="el-icon-delete"
                  @click="deleteConfirm(scope.row)"
                >删除
                </el-button>
              </el-row>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @current-change="handleCurrentChange"
          background
          style="padding: 30px;text-align:right"
          layout="total, prev, pager, next"
          :page-size="page.limit"
          :total="page.total"
        >
        </el-pagination>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import article from '@/api/article'

export default {
  name: 'ArticleList',
  data() {
    return {
      articles: [],
      page: {
        page: 1,
        limit: 10,
        currentPage: 1,
        total: 0
      }
    }
  },
  created() {
    this.getArticleList()
  },
  methods: {
    getArticleList() {
      article.getArticleList(this.page.page, this.page.limit)
        .then(result => {
          this.articles = result.data.records
          console.log(result.data.records)
          this.page.currentPage = result.data.current
          this.page.total = result.data.total
        })
    },
    handleCurrentChange(current) {
      this.page.page = current
      this.getArticleList()
    },
    edit(val) {
      this.$router.push({ name: 'articleAdd', params: { id: val.id } })
    },
    articleStateHandler(art) {
      const state = {
        id: art.id,
        isWeight: art.isWeight,
        isPublish: art.isPublish,
        isComment: art.isComment
      }
      article.updateState(state).then(result => {
        this.$message.success(result.message)
      })
    },
    deleteConfirm(art) {
      article.deleteById(art.id).then(result => {
        this.$message.success(`删除${art.title}文章成功`)
        this.getArticleList()
      })
    }
  }
}
</script>

<style scoped>
.www {
  color: #0879ec;
}
</style>
