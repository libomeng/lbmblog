<template>
  <div class="bm-tag-item">
    <el-dialog
      :title="tagCopy === null ? '添加标签':'编辑标签'"
      :visible.sync="visible"
      width="30%"
      :before-close="handleClose"
    >
      <el-form ref="form" :model="tagCopy" :rules="rules">
        <el-form-item prop="categoryName">
          <el-input v-model="tagCopy.tagName"/>
        </el-form-item>
        <!--      <el-form-item label="描述" prop="description">-->
        <!--        <el-input v-model="categoryCopy.description"/>-->
        <!--      </el-form-item>-->
      </el-form>
      <el-button @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="submit('form')">确 定</el-button>
    </el-dialog>
  </div>
</template>

<script>

import tag from '@/api/tag'

export default {
  name: 'TagItem',
  props: {
    visible: {
      type: Boolean,
      require: true
    },
    tag: {
      type: Object,
      require: true
    }
  },
  data() {
    return {
      tagCopy: {},
      rules: {
        tagName: [
          { require: true, message: '请输入标签名称', trigger: 'blur' },
          { min: 2, max: 10, message: '分类名称需要在2-10个字符', trigger: 'blur' }
        ]
        // description: [
        //   { require: true, message: '请输入描述', trigger: 'blur' },
        //   { min: 2, max: 20, message: '分类名称需要在2-20个字符', trigger: 'blur' }
        // ]
      }
    }
  },
  created() {
    this.tagCopy = JSON.parse(JSON.stringify(this.tag))
  },
  methods: {
    submit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          tag.update(this.tagCopy).then(result => {
            this.$message.success(result.message)
            this.$emit('success')
            this.$emit('visible', false)
            this.tagCopy = null
          })
        } else {
          return false
        }
      })
    },
    handleClose() {
      this.$emit('visible', false)
      this.tagCopy = null
    }
  }
}
</script>
<style scoped>

</style>
