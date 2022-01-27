<template>
  <div class="bm-editor">
    <mavon-editor class="editor" ref="md" @imgAdd="$imgAdd" v-model="editor.value" v-bind="editor"/>
  </div>
</template>
<script>
export default {
  name: 'Editor',
  props: {
    editor: {
      type: Object,
      required: true
    }
  },
  computed: {
    html() {
      return this.$refs.md.d_render
    }
  },
  watch: {
    'editor.value'() {
      this.editor.html = this.html
    }
  },
  methods: {
    // 绑定@imgAdd event
    $imgAdd(pos, $file) {
      // 第一步.将图片上传到服务器.
      var formdata = new FormData()
      formdata.append('image', $file)
      // axios({
      //   url: 'server url',
      //   method: 'post',
      //   data: formdata,
      //   headers: { 'Content-Type': 'multipart/form-data' }
      // }).then((url) => {
      //   // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
      //   /**
      //    * $vm 指为mavonEditor实例，可以通过如下两种方式获取
      //    * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
      //    * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
      //    */
      //   '$vm'.$img2Url(pos, url)
      // })
    }
  }
}
</script>

<style scoped>
.editor {
  margin: auto;
  width: 100%;
  height: 400px;
}
</style>
