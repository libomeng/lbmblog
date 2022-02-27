<template>
  <div class="me-view-body" v-title :data-title="title">
    <el-container>
      <el-main class="el-main">
        <div class="me-view-card">
          <h3 class="me-view-title">{{ article.title }}</h3>
          <div class="me-view-author">
            <div class="me-view-info">
              <div class="me-view-meta">
                <span>{{ article.createDate | format }}</span>
                <span>阅读 {{ article.viewCounts }}</span>
                <span>评论 {{ article.commentCounts }}</span>
              </div>
            </div>
          </div>
          <div :style="preWidth.width" class="me-view-content">
            <markdown-editor class="bm-editor" :editor=article.editor></markdown-editor>
          </div>
          <div class="me-view-tag">
            标签：
            <el-tag v-for="t in article.tags" :key="t.id" class="me-view-tag-item" size="mini" type="primary">
              {{ t.tagName }}
            </el-tag>
          </div>
          <div class="me-view-tag">
            文章分类：
            <el-button @click="tagOrCategory('category', article.category.id)" size="mini" type="primary" round plain>
              {{ article.category.categoryName }}
            </el-button>
          </div>
          <div class="me-view-comment" v-if="article.isComment===1">
            <div class="me-view-comment-write">
              <el-form :model="comment" :rules="commentRules" ref="comment">
              <el-row :gutter="20">
                <el-col :offset="2" :span="20">
                  <el-form-item prop="content">
                  <el-input
                    type="textarea"
                    :autosize="{ minRows: 2}"
                    placeholder="说点什么"
                    class="me-view-comment-text"
                    v-model="comment.content"
                    resize="none"
                  @focus="showComment=true">
                  </el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="10" v-if="showComment">
                <el-col :offset="2" :span="6">
                  <el-input placeholder="你的邮箱(必填)" v-model="comment.email"></el-input>
                </el-col>
                <el-col :span="6">
                  <el-input placeholder="你的邮箱(必填)" v-model="comment.email"></el-input>
                </el-col>
                <el-col :span="2" >
                  <el-button type="primary" size="" @click="publishComment('comment')">评论</el-button>
                </el-col>
              </el-row>
              </el-form>
            </div>
            <div class="bm-view-comment">
              <span>{{ article.commentCounts }} 条评论</span>
            </div>
            <div>
              <commment-item
                v-for="(c,index) in comments"
                :comment="c"
                :articleId="article.id"
                :index="index"
                :rootCommentCounts="comments.length"
                @commentCountsIncrement="commentCountsIncrement"
                :key="c.id">
              </commment-item>
            </div>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import MarkdownEditor from '@/components/markdown/MarkdownEditor'
import CommmentItem from '@/components/comment/CommentItem'
import {viewArticle} from '@/api/article'
import {getCommentsByArticle, publishComment} from '@/api/comment'
import {mavonEditor} from 'mavon-editor'
import default_avatar from '@/assets/img/default_avatar.png'

export default {
  name: 'BlogView',
  created() {
    this.getArticle()
    this.preWidthHandler()
  },
  watch: {
    '$route': 'getArticle',
    screenWidth() {
      this.preWidthHandler()
    },
  },
  data() {
    return {
      article: {
        id: '',
        title: '',
        commentCounts: 0,
        viewCounts: 0,
        summary: '',
        author: {},
        tags: [],
        category: {},
        createDate: '',
        isComment: false,
        editor: {
          value: '',
          html: '',
          toolbarsFlag: false,
          subfield: false,
          defaultOpen: 'preview'
        }
      },
      comments: [],
      comment: {
        article: {},
        content: '',
        nickName:'',
        email:''
      },
      mainStyle: '',
      preWidth: {
        width: ''
      },
      commentRules:{
        nickName: [
          { required: true, message: '请输入昵称', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' }
        ],
      },
      showComment: false
    }

  },
  props: {
    screenWidth: Number
  },
  computed: {
    avatar() {
      let avatar = this.$store.state.avatar
      if (avatar.length > 0) {
        return avatar
      }
      return default_avatar
    },
    title() {
      return `${this.article.title}`
    },
  },
  methods: {
    tagOrCategory(type, id) {
      this.$router.push({path: `/${type}/${id}`})
    },
    editArticle() {
      this.$router.push({path: `/write/${this.article.id}`})
    },
    getArticle() {
      let that = this
      viewArticle(that.$route.params.id).then(data => {
        Object.assign(that.article, data.data)
        that.article.editor.value = data.data.body.content
        that.article.editor.html = data.data.body.html
        if (data.data.isComment === 1) {
          that.getCommentsByArticle()
        }
      }).catch(error => {
        if (error !== 'error') {
          that.$message({type: 'error', message: '文章加载失败', showClose: true})
        }
      })
    },
    publishComment(ref) {
      this.$refs[ref].validate((valid) => {
        if (valid) {
          let that = this
          if (!that.comment.content) {
            return;
          }
          that.comment.article.id = that.article.id
          let parms = {articleId: that.article.id, content: that.comment.content ,nickName: that.comment.nickName ,email: that.comment.email }
          publishComment(parms).then(data => {
            if (data.success) {
              that.$message({type: 'success', message: '评论成功', showClose: true})
              that.comment.content = ''
              that.comments.unshift(data.data)
              that.commentCountsIncrement()

            } else {
              that.$message({type: 'error', message: data.msg, showClose: true})
            }
          }).catch(error => {
            if (error !== 'error') {
              that.$message({type: 'error', message: '评论失败', showClose: true})
            }
          })
        } else {
          return false;
        }
      });
    },
    getCommentsByArticle() {
      let that = this
      getCommentsByArticle(that.article.id).then(data => {
        if (data.success) {
          that.comments = data.data
        } else {
          that.$message({type: 'error', message: '评论加载失败', showClose: true})
        }
      }).catch(error => {
        if (error !== 'error') {
          that.$message({type: 'error', message: '评论加载失败', showClose: true})
        }
      })
    },
    commentCountsIncrement() {
      this.article.commentCounts += 1
    },
    preWidthHandler() {
      if (this.screenWidth < 1000) {
        this.preWidth.width = ` width: ${this.screenWidth}px;`
      } else {
        this.preWidth.width = ''
      }
    }
  },
  components: {
    'markdown-editor': MarkdownEditor,
    mavonEditor,
    CommmentItem
  },
  //组件内的守卫 调整body的背景色
  beforeRouteEnter(to, from, next) {
    window.document.body.style.backgroundColor = '#fff';
    next();
  },
  beforeRouteLeave(to, from, next) {
    window.document.body.style.backgroundColor = '#f5f5f5';
    next();
  },

}
</script>

<style scoped>
.me-view-body {
  width: 100%;
}
.bm-view-comment {
  margin-top: 30px;
}

.me-view-container {

}

.me-view-card {
  width: 50%;
  margin: auto;
}

@media screen and (max-width: 1000px) {
  .me-view-card {
    width: 100%;
  }
}


.me-view-title {
  font-size: 26px;
  font-weight: 600;
  line-height: 1.3;
  text-align: center;
}

.me-view-author {
  margin-top: 15px;
  text-align: center;
  margin-bottom: 15px;
}

.me-view-picture {
  width: 40px;
  height: 40px;
  border: 1px solid #ddd;
  border-radius: 50%;
  vertical-align: middle;
  background-color: #5fb878;
}

.me-view-info {
  display: inline-block;
  vertical-align: middle;
  margin-left: 8px;
}

.me-view-content {
}

.me-view-meta {
  font-size: 12px;
  color: #969696;
}


.me-view-tag {
  margin-top: 20px;
  padding-left: 6px;
  border-left: 4px solid #c5cac3;
}

.me-view-tag-item {
  margin: 0 4px;
}

.me-view-comment {
  margin-top: 60px;
}

.me-view-comment-title {
  font-weight: 600;
  border-bottom: 1px solid #f0f0f0;
  padding-bottom: 20px;

}

.me-view-comment-write {
  margin-top: 20px;
}

.me-view-comment-text {
  font-size: 16px;
}

.v-show-content {
  padding: 8px 25px 25px 30px !important;
}

.v-note-wrapper .v-note-panel {
  box-shadow: none !important;
}

.v-note-wrapper .v-note-panel .v-note-show .v-show-content, .v-note-wrapper .v-note-panel .v-note-show .v-show-content-html {
  background: #fff !important;
}

/deep/ .markdown-body pre {
  padding: 1px;
  overflow-x: auto;
}


.el-main {

}

@media screen and (max-width: 1000px) {
  .el-main {
    padding: 0;
  }
}

.bm-editor {

}
</style>
