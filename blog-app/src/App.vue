<template>
  <div id="app" v-if="display">
    <router-view/>
    <!--    <go-top></go-top>-->
  </div>
</template>

<script>
import GoTop from '@/components/gotop/GoTop'
import user from "@/api/user"
import location from "@/utils/location";
import store from "@/store";
export default {
  name: 'App',
  data() {
    return {
      display: false,

    }
  },
  created() {
    this.issueToken()
  },
  components: {GoTop},
  methods: {
    issueToken() {
      /**
       * 在用户第一次访问时进行token签发
       */
      this.$store.dispatch('issueToken', location.getLocation()).then(() => {
        this.display = true
        this.$store.dispatch('getUserInfo')

      })
    }
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
}

body {
  background-color: #f5f5f5;
  font-weight: 400;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  line-height: 1.5;
}

a {
  cursor: pointer;
  text-decoration: none;
  transition: none 86ms ease-out;
}

a:hover {
  color: #5FB878;
}

.me-area {
  background-color: #ffffff;
  /*opacity:0.90*/
}

html {
  height: 100%;
}

body {
  min-height: 100%;
}

body {
  position: relative;
}

.me-pull-right {
  float: right;
}

#app {
  /*background-image: url('/static/image/image1.jpg')*/
}
</style>
