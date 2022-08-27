<template>
  <div class="bm-tag">
    <el-container>
      <el-main class="el-main">
        <tag-item v-if="visible" :visible="visible" :tag="tag" @visible="visibleHandler" @success="successHandler"></tag-item>
        <!--分页查询-->
        <el-form :inline="true"  :model="queryWrapper" class="demo-form-inline" ref="page">
          <el-form-item label="搜索名称" prop="tagName">
            <el-input v-model="queryWrapper.tagName" placeholder="标签名称"></el-input>
          </el-form-item>
          <el-form-item label="按日期筛选" prop="begin">
            <el-col :span="11">
              <el-form-item>
                <el-date-picker
                  type="datetime"
                  placeholder="开始日期"
                  value-format="timestamp"
                  v-model="queryWrapper.begin"
                />
              </el-form-item>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-form-item prop="end">
                <el-date-picker
                  type="datetime"
                  placeholder="结束日期"
                  value-format="timestamp"
                  v-model="queryWrapper.end"
                />
              </el-form-item>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="getList">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button @click="resetForm('page')">清空</el-button>
          </el-form-item>
        </el-form>
        <el-table :data="tags">
          <el-table-column prop="tagName" label="标签名称">
          </el-table-column>
          <el-table-column label="修改时间">
            <template slot-scope="scope">
              {{ scope.row.gmtModified | moment('YYYY-MM-DD HH:mm:ss') }}
            </template>
          </el-table-column>
          <el-table-column label="文章数量">
            <template slot-scope="scope">
              {{scope.row.articleCount}}
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
                    @click="update(scope.row)"
                  >编辑
                  </el-button>
                </el-col>
                <el-button
                  size="mini"
                  type="danger"
                  icon="el-icon-delete"
                  @click="remove(scope.row)"
                :disabled="removeHandle(scope.row.articleCount)">删除
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
          :page-size="query.limit"
          :total="query.total"
        >
        </el-pagination>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import tag from '@/api/tag'
import TagItem from '@/components/TagItem'

export default {
  name: 'Tag',
  components: {
    TagItem
  },
  data() {
    return {
      tags: [],
      visible: false,
      tag: {},
      queryWrapper: {},
      query: {
        page: 1,
        limit: 10,
        current: 1,
        total: 0
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.query.page = this.query.current
      tag.getTagPage(this.query.page, this.query.limit, this.queryWrapper).then(result => {
        this.tags = result.data.records
        this.query.current = result.data.current
        this.query.total = result.data.total
      })
    },
    update(val) {
      this.tag = val
      this.visible = true
    },
    remove(val) {
      tag.remove(val.id).then(result => {
        this.$message.success(result.message)
        this.getList()
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    visibleHandler(val) {
      this.visible = val
    },
    successHandler() {
      this.getList()
    },
    handleCurrentChange(current) {
      this.query.page = current
      this.getList()
    },
    removeHandle(articleCount) {
      if (articleCount > 0) {
        return true
      } else {
        return false
      }
    }
  }
}
</script>

<style scoped>

</style>
