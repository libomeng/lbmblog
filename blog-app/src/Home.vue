<template>
  <div id="home">
    <el-container>
      <el-aside class="bm-music-player"><Aplayer :music="music" @oncanplay="aaa"></Aplayer></el-aside>
      <!--TODO 音乐播放器未完成-->
    	<base-header :activeIndex="activeIndex" :screenWidth="screenWidth"></base-header>
		  <router-view class="me-container" :screenWidth ="screenWidth"/>
			<base-footer ></base-footer>
		</el-container>

  </div>

</template>

<script>
import BaseFooter from '@/components/BaseFooter'
import BaseHeader from '@/views/BaseHeader'
import Aplayer from 'vue-aplayer'
export default {
  name: 'Home',
  data (){
  	return {
      activeIndex: '/',
      footerShow: true,
      screenWidth: document.body.clientWidth,
      music:{
        title: '海阔天空',
        artist: 'Silent Siren',
        src: 'http://music.163.com/song/media/outer/url?id=1329665666.mp3',
        pic: 'https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg'
      }
    }
  },
  created() {
    console.log(this)
  },
  mounted() {
    //获取页面宽度
    const that = this
    window.onresize = () => {
      return (() => {
        window.screenWidth = document.body.clientWidth
        that.screenWidth = window.screenWidth
      })()
    }
  },
  components:{
  	'base-header':BaseHeader,
  	'base-footer':BaseFooter,
      Aplayer
  },
  beforeRouteEnter (to, from, next){
  	 next(vm => {
    	vm.activeIndex = to.path
  	})
  },
  beforeRouteUpdate (to, from, next) {
	  if(to.path == '/'){
	  	this.footerShow = true
	  }else{
	  	this.footerShow = false
	  }
	  this.activeIndex = to.path
	  next()
	},
  methods:{
    aaa(){
      console.log("aaaa")
    }
  }

}
</script>

<style>

.me-container{
  margin: 100px auto 140px;
}
.bm-music-player{
  position: fixed;
  bottom: 0;
  z-index:999
}

</style>
