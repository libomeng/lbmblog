<template>
  <div class="me-editor">
    <mavon-editor
      ref="md"
      v-model="editor.value"
      @imgAdd="imgAdd"
      v-bind="editor"
      codeStyle="dark"

      :ishljs="true">
    </mavon-editor>
  </div>
</template>


<script>

import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

import {upload} from '@/api/upload'

export default {
  name: 'MarkdownEditor',
  props: {
    editor: Object
  },
  data() {
    return {}
  },
  mounted() {
    this.$set(this.editor, 'ref', this.$refs.md)
  },
  methods: {
    imgAdd(pos, $file) {
      let that = this
      let formdata = new FormData();
      formdata.append('image', $file);

      upload(formdata).then(data => {
        // 第二步.将返回的url替换到文本原位置![...](./0) -> ![...](url)
        if (data.success) {

          that.$refs.md.$img2Url(pos, data.data);
        } else {
          that.$message({message: data.msg, type: 'error', showClose: true})
        }

      }).catch(err => {
        that.$message({message: err, type: 'error', showClose: true});
      })
    }
  },
  components: {
    mavonEditor
  }
}
</script>
<style scoped>

.me-editor {

}

.v-note-wrapper.fullscreen {
  top: 60px !important
}

.v-note-wrapper.shadow {
}

.v-note-wrapper {
  z-index: 0;
}
</style>
