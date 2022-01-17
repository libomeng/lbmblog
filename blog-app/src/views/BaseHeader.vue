<template>
  <el-header class="me-area" style="background-color: #2C3E50;" height="60px">
    <el-row class="me-header" >
      <el-col v-if="screenWidth>=1100"  :offset="4" :span="4" class="me-header-left"><!--        <router-link to="/" class="me-title">-->
        <h3 style="color: #fff;margin-top: 16px">李搏猛的博客</h3>
      </el-col>
      <el-col v-if="screenWidth>=1100"  :span="16" >
        <el-menu  :router=true  class="el-menu-demo" menu-trigger="click" active-text-color="#fff"  background-color="#2C3E50"  text-color="#fff" :default-active="activeIndex"
                 mode="horizontal">
          <el-menu-item index="/" ><i class="el-icon-s-home"/>首页</el-menu-item>
          <el-menu-item index="/category/all"><i class="el-icon-menu"/>文章分类</el-menu-item>
          <el-menu-item index="/tags/all"> <i class="el-icon-price-tag"/>标签</el-menu-item>
          <el-menu-item index="/archives"><i class="el-icon-s-management"/>文章归档</el-menu-item>
        </el-menu>
        </el-col>
      <el-col v-if="screenWidth<1100" :span="4">
        <el-button  class="bm-header-but" type="primary" icon="el-icon-back" size="medium" @click="back"></el-button>
      </el-col>
        <el-col v-if="screenWidth<1100"   :span="16">
          <h3 style="color: #fff;margin-top: 16px">李搏猛的博客</h3>
        </el-col>
        <el-col v-if="screenWidth<1100"  :span="4">
        <el-menu :router=true  class="el-menu-demo" menu-trigger="click" active-text-color="#fff"  background-color="#2C3E50"  text-color="#fff" :default-active="activeIndex"
                 mode="horizontal">
          <el-submenu index="2" >
            <template slot="title"><i class="el-icon-s-unfold"></i></template>
          <el-menu-item index="/" ><i class="el-icon-s-home"/>首页</el-menu-item>
          <el-menu-item index="/category/all"><i class="el-icon-menu"/>文章分类</el-menu-item>
          <el-menu-item index="/tags/all"> <i class="el-icon-price-tag"/>标签</el-menu-item>
          <el-menu-item index="/archives"><i class="el-icon-s-management"/>文章归档</el-menu-item>
          </el-submenu>
        </el-menu>
        </el-col>
    </el-row>
  </el-header>
</template>

<script>
  export default {
    name: 'BaseHeader',
    props: {
      screenWidth: Number,
      activeIndex: String,
      simple: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {}
    },
    computed: {
      user() {
        let login = this.$store.state.account.length != 0
        let avatar = this.$store.state.avatar
        let name =this.$store.state.name
        return {
          login, avatar,name
        }
      }
    },
    methods: {
      logout() {
        let that = this
        this.$store.dispatch('logout').then(() => {
          this.$router.push({path: '/'})
        }).catch((error) => {
          if (error !== 'error') {
            that.$message({message: error, type: 'error', showClose: true});
          }
        })
      },
      back(){
        this.$router.go(-1)
      }
    }
  }
</script>

<style>

  .el-header {
    position: fixed;
    z-index: 1024;
    min-width: 100%;
    box-shadow: 0 2px 3px hsla(0, 0%, 7%, .1), 0 0 0 1px hsla(0, 0%, 7%, .1);
  }

  .me-title {
    margin-top: 10px;
    font-size: 24px;
  }

  .me-header-left {

  }

  .me-title img {
    max-height: 2.4rem;
    max-width: 100%;
  }

  .me-header-picture {
    width: 36px;
    height: 36px;
    border: 1px solid #ddd;
    border-radius: 50%;
    vertical-align: middle;
    background-color: #5fb878;
  }
  .bm-header-but{
    margin-top: 10px;
  }
</style>
