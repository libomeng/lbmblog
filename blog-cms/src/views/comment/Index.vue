<template>
  <div class="bm-comment">
    <el-table :data="comments">
      <el-table-column prop="" label="用户id">
      </el-table-column>
      <el-table-column prop="" label="用户昵称">
      </el-table-column>
      <el-table-column prop="" label="ip地址">
      </el-table-column>
      <el-table-column prop="" label="评论时间">
      </el-table-column>
      <el-table-column prop="" label="评论内容">
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
  </div>
</template>

<script>
import comment from '@/api/comment'
export default {
  name: 'Comment',
  data() {
    return {
      comments: [],
      page: {
        page: 1,
        limit: 10,
        currentPage: 1,
        total: 0
      }
    }
  },
  created() {
  },
  methods: {
    getList() {
      comment.getComments(this.page.page, this.page.limit).then(result => {
        this.comments = result.data
      })
    },
    handleCurrentChange(current) {
      this.page.page = current
      this.getList()
    }
  }

}
</script>

<style scoped>

</style>
