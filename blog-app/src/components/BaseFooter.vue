<template>
  <el-footer class="me-area" style="background-color: #2C3E50 ">
    <div class="me-footer">
          <el-link style="color: #fff">@2022 libomeng.cn</el-link>
    </div>
  </el-footer>

</template>

<script>
  export default {
    name: 'BaseFooter',
    data() {
      return {
      }
    },
    methods: {},
    mounted() {
    },
  }
</script>

<style scoped>

  .el-footer {
    min-width: 100%;
    box-shadow: 0 -2px 3px hsla(0, 0%, 7%, .1), 0 0 0 1px hsla(0, 0%, 7%, .1);
    position: absolute;
    bottom: 0;
    left: 0;
    z-index: 1024;
  }

  .me-footer {
    text-align: center;
    line-height: 60px;
    font-family: 'Open Sans', sans-serif;
    font-size: 18px;


  }
  .me-area{

  }
  .me-login-design-color {
    color: #5FB878 !important;
  }
</style>
