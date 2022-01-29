<template>
  <div class="bm-category-add">
    <el-container>
      <el-main class="bm-category-add">
        <el-card>
        <el-form ref="form" :model="category" :rules="rules">
          <el-form-item label="分类名称" prop="categoryName">
            <el-input v-model="category.categoryName"/>
          </el-form-item>
          <el-form-item label="分类描述" prop="description">
            <el-input v-model="category.description"/>
          </el-form-item>
        </el-form>
        <el-button @click="reset('form')">重置</el-button>
        <el-button type="primary" @click="submit('form')">确 定</el-button>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import category from '@/api/category'

export default {
  name: 'Add',
  data() {
    return {
      category: {},
      rules: {
        categoryName: [
          { require: true, message: '请输入标签名称', trigger: 'blur' },
          { min: 2, max: 10, message: '分类名称需要在2-10个字符', trigger: 'blur' }
        ],
        description: [
          { require: true, message: '请输入描述', trigger: 'blur' },
          { min: 2, max: 20, message: '分类名称需要在2-20个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          category.create(this.category).then(result => {
            this.$message.success(result.message)
            this.$router.push({ name: 'categoryList' })
          })
        }
      })
    },
    reset(name) {
      this.$refs[name].resetFields()
    }
  }

}
</script>

<style scoped>
.bm-category-add {
  margin-left: 20%;
  margin-right: 20%;
}
</style>
