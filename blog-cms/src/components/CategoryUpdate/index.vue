<template>
<div class="bm-category-update">
  <el-dialog
    title="编辑分类"
    :visible.sync="visible"
    width="30%"
    :before-close="handleClose">
    <el-form ref="form" :model="categoryCopy" :rules="rules">
      <el-form-item label="分类" prop="categoryName">
        <el-input v-model="categoryCopy.categoryName" />
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input v-model="categoryCopy.description" />
      </el-form-item>
    </el-form>
    <el-button @click="handleClose">取 消</el-button>
    <el-button type="primary" @click="submit('form')">确 定</el-button>
  </el-dialog>
</div>
</template>
<script>
import category from '@/api/category'
export default {
  name: 'CategoryUpdate',
  data() {
    return {
      categoryCopy: {},
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
  props: {
    visible: {
      type: Boolean,
      require: true
    },
    category: {
      type: Object,
      require: true
    }
  },
  created() {
    this.categoryCopy = JSON.parse(JSON.stringify(this.category))
  },
  methods: {
    submit(name) {
      console.log(this.$refs[name])
      this.$refs[name].validate((valid) => {
        if (valid) {
          category.update(this.categoryCopy).then(result => {
            this.$message.success(result.message)
          })
          this.$emit('visible', false)
          this.categoryCopy = null
        } else {
          return false
        }
      })
    },
    handleClose() {
      this.$emit('visible', false)
      this.categoryCopy = null
    }
  }
}
</script>

<style scoped>

</style>
