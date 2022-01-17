<template>
  <div class="me-allct-body" v-title data-title="标签分类" >
    <el-container class="me-allct-container">
      <el-main>
              <div>
              <ul class="me-allct-items">
              <li v-for="c in categorys" @click="view(c.id)" :key="c.id" class="me-allct-item">
                <div class="me-allct-content">
                  <a class="me-allct-info">
                    <img class="me-allct-img" :src="c.avatar?c.avatar:defaultAvatar"/>
                    <h4 class="me-allct-name">{{c.categoryName}}</h4>
                    <p class="me-allct-description">{{c.description}}</p>
                  </a>
                  <div class="me-allct-meta">
                    <span>{{c.articles}} 文章</span>
                  </div>
                </div>
              </li>
            </ul>
              </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import defaultAvatar from '@/assets/img/logo.png'
  import {getAllCategorysDetail} from '@/api/category'
  import {getAllTagsDetail} from '@/api/tag'

  export default {
    name: 'BlogAllCategoryTag',
    created() {
      this.getCategorys()
    },
    data() {
      return {
        defaultAvatar:defaultAvatar,
        categorys: [],
      }
    },
    methods: {
      view(id) {
        this.$router.push({path: `/category/${id}`})
      },
      getCategorys() {
        let that = this
        getAllCategorysDetail().then(data => {
          that.categorys = data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '文章分类加载失败', showClose: true})
          }
        })
      },
    },
    //组件内的守卫 调整body的背景色
    beforeRouteEnter(to, from, next) {
      window.document.body.style.backgroundColor = '#fff';
      next();
    },
    beforeRouteLeave(to, from, next) {
      window.document.body.style.backgroundColor = '#f5f5f5';
      next();
    }
  }
</script>

<style>
  .me-allct-body {
    margin: 60px auto 140px;
  }

  .me-allct-container {

  }

  .me-allct-items {
    padding-top: 2rem;
    margin-left: 26px
  }

  .me-allct-item {
    width: 200px;
    display: inline-block;
    margin-bottom: 2.4rem;
    padding: 0 .7rem;
    box-sizing: border-box;
  }

  .me-allct-content {
    display: inline-block;
    width: 100%;
    background-color: #fff;
    border: 1px solid #f1f1f1;
    transition: border-color .3s;
    text-align: center;
    padding: 1.5rem 0;
  }

  .me-allct-info {
    cursor: pointer;
  }

  .me-allct-img {
    margin: -40px 0 10px;
    width: 60px;
    height: 60px;
    vertical-align: middle;

  }

  .me-allct-name {
    font-size: 21px;
    font-weight: 150;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin-top: 4px;
  }

  .me-allct-description {
    min-height: 50px;
    font-size: 13px;
    line-height: 25px;
  }

  .me-allct-meta {
    font-size: 12px;
    color: #969696;
  }
</style>
