<template>
  <div class="bm-category">
    <el-container>
      <category-update v-if="visible" :visible="visible" :category="row" @visible="visibleHandler" @success="successHandler"></category-update>
      <el-main>
        <el-table :data="categorys">
          <el-table-column prop="categoryName" label="分类名称" >
          </el-table-column>
          <el-table-column prop="description" label="分类描述" >
          </el-table-column>
          <el-table-column prop="count" label="文章数量" >
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
                    :disabled="removeHandler(scope.row)">删除
                  </el-button>
              </el-row>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import category from '@/api/category'
import categoryUpdate from '@/components/CategoryUpdate'

export default {
  name: 'Category',
  components: {
    categoryUpdate
  },
  data() {
    return {
      categorys: [],
      visible: false,
      row: null
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      category.getList().then(result => {
        this.categorys = result.data
      })
    },
    update(val) {
      this.row = val
      this.visible = true
    },
    remove(val) {
      category.remove(val.id).then(result => {
        this.$message.success(result.message)
        this.getList()
      })
    },
    visibleHandler(val) {
      this.visible = val
    },
    successHandler() {
      this.getList()
    },
    removeHandler(row) {
      if (row.count > 0) {
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
