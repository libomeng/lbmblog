<template>
  <div id="home">
    <el-container>
      <el-aside class="bm-music-player" style="width: 265px" >
        <transition name="el-zoom-in-bottom">
          <div @mouseenter="simple=false" @mouseout="aplayerOut">
        <Aplayer v-if="musicInfo.show" :music="musicInfo.music" :mini="simple" theme="#2C3E50" />
          </div>
        </transition>
        </el-aside>
    	<base-header :activeIndex="activeIndex" :screenWidth="screenWidth"></base-header>
		  <router-view class="me-container" :screenWidth ="screenWidth" v-bind.sync="musicInfo"/>
      <transition name="el-zoom-in-bottom">
        <base-footer style="z-index: 1"></base-footer>
      </transition>
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
      musicInfo: {
        show:false,
        music:{
          title:'222'
        },
      },
      simple:true
    }
  },
  watch:{
    'musicInfo.show'(){
      this.simple=false
      setTimeout(()=>{
        this.simple=true
      },5000)
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

    aplayerOut(){
     setTimeout(()=>{
       this.simple=true
     },5000)
    }
  }

}
</script>

<style>

.me-container{
  margin: 100px auto 140px;
}
.bm-music-player{
  width: 250px;
  z-index: 999;
  position: fixed;
  bottom:0px;
  left: 0;


}
.transition-box {
  margin-bottom: 10px;
  width: 200px;
  height: 100px;
  border-radius: 4px;
  background-color: #409EFF;
  text-align: center;
  color: #fff;
  padding: 40px 20px;
  box-sizing: border-box;
  margin-right: 20px;
}
</style>
