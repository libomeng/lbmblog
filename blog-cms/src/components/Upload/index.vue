<template>
  <div v-loading="loading">
    <el-upload
      :name="name"
      class="avatar-uploader"
      :action="action"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :on-progress="handleUploading"
      :before-upload="beforeAvatarUpload"
    >
      <el-image v-if="obj.img" :src="obj.img" class="avatar">
        <div slot="placeholder" class="image-slot">
          加载中<span class="dot">...</span>
        </div>
      </el-image>
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
  </div>
</template>

<script>
export default {
  name: 'Upload',
  /**
   *
   * @returns {{res: {}  上传后的返回值
   * , success: boolean   上传成功 true
   * }}
   */
  data() {
    return {
      res: {},
      success: false,
      loading: false
    }
  },
  props: {
    obj: {
      type: Object,
      required: true
    },
    action: {
      type: String,
      required: true
    },
    name: String
  },
  methods: {
    handleAvatarSuccess(res, file) {
      if (res.success) {
        this.success = true
        this.obj.img = res.data
      } else {
        this.$message.error(res.message)
      }
      this.loading = false
    },
    handleUploading() {
      this.loading = true
    },

    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 5

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 5MB!')
      }
      return isJPG && isLt2M
    }

  }
}
</script>

<style scoped>
/deep/ .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

/deep/ .avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

/deep/ .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  line-height: 178px;
  text-align: center;
}
/deep/ .avatar{
  width: 100%;
  height: 100%;
}

</style>
