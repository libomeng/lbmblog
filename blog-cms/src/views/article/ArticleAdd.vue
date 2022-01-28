<template>
  <div class="bm-add-article">
    <el-container>
      <el-main>
        <el-form ref="from" :model="article" label-position="top">
          <el-row>
            <el-col :span="11">
              <el-form-item label="文章标题">
                <el-input v-model="article.title"></el-input>
              </el-form-item>
              <el-form-item label="文章简介">
                <el-input
                  type="textarea"
                  :rows="5"
                  placeholder="请输入文章简介"
                  v-model="article.summary"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :offset="2" :span="11">
              <el-form-item label="文章首图">
                <upload class="bm-article-img" :action="action" :obj="article" name="articleImg"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item label="文章内容">
              <editor :editor="article"></editor>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="分类" style="margin-right: 20px">
                <el-select
                  allow-create
                  filterable
                  v-model="article.category"
                  placeholder="输入可新建分类"
                  style="display: block"
                >
                  <el-option
                    v-for="item in categorys"
                    :key="item.value"
                    :label="item.label"
                    :value="item.label"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="标签" style="margin-left: 20px">
                <el-select
                  v-model="article.tags"
                  multiple
                  filterable
                  allow-create
                  default-first-option
                  placeholder="输入可新建标签"
                  style="display: block"
                >
                  <el-option
                    v-for="item in tags"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="字数" style="margin-right: 20px">
                <el-input v-model="article.words"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="阅读时间(分钟)" style="margin-left: 20px">
                <el-input v-model="article.readTime"/>
              </el-form-item>
            </el-col>
            <div class="bm-bottom-button">
              <el-button @click="save">保存</el-button>
              <el-button type="primary" @click="publish">发布</el-button>
            </div>
          </el-row>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import upload from '@/components/Upload'
import editor from '@/components/Editor'
import tag from '@/api/tag'
import category from '@/api/category'
import article from '@/api/article'
export default {
  name: 'ArticleAdd',
  components: {
    upload,
    editor
  },
  data() {
    return {
      action: `${process.env.VUE_APP_BASE_API}/admin/upload/articleImg`,
      article: {
        title: '',
        summary: '',
        img: '',
        value: '',
        category: '',
        tags: [],
        words: 0,
        readTime: 0
      },
      categorys: [],
      tags: []
    }
  },
  created() {
    if (this.$route.params.id) {
      //TODO 根据ID回显文章信息
    }
    this.getCategoryList()
    this.getTagList()
  },
  methods: {
    getCategoryList() {
      tag.getTagList()
        .then(result => {
          this.tags = result.data
        })
    },
    getTagList() {
      category.getCategoryList()
        .then(result => {
          this.categorys = result.data
        })
    },
    save() {
      article.createArticle(this.article)
        .then(result => {
          this.$message(result.message)
        })
    },
    publish() {
      this.article.isPublish = 1
      this.save()
    }
  }
}
</script>

<style scoped>
.bm-add-article {
  padding: 20px;
}
/deep/ .el-upload {
  display: block;
  height: 250px;
}
.bm-bottom-button {
  margin-top: 20px;
}
</style>
