<template>
  <div class="bm-tag" v-title data-title="标签分类">
 <el-container>
  <el-main>
     <div class="bm-title"><span>Tags</span></div>
    <div class="mb-title-info"><span>目前共计{{tags.length}}个</span></div>
    <div class="bm-tags"  >
          <el-link @click="view(tag)" :class="classHandler(tag.count)" v-for="tag in tags" :key="tag.id">{{tag.tagName}}</el-link>
    </div>
  </el-main>
 </el-container>
  </div>
</template>

<script>
  import {getTagCount} from '@/api/tag'
    export default {
        name: "BlogTag",
      data(){
          return{
            tags:[],
          }
      },
      created() {
          this.getTags();
      },
      methods:{
        getTags(){
          getTagCount().then(result =>{
          this.tags = result.data
          })
        },
        classHandler(val){
          if(val>10){
            return 'tag-level-1'
          }if(val>=4 && val<=10){
            return 'tag-level-2'
          }if(val<4){
            return 'tag-level-3'
          }
        },
        view(tag){
          this.$router.push({path:`/archives/tag/${tag.id}/${tag.tagName}`})
        }
      }
    }
</script>

<style scoped>
.bm-title{
  font-weight: 400;
  margin-top: 10px;
text-align: center;
  font-size: 26px;
  color: #222222;
}
  .mb-title-info{
    font-weight: 400;
    margin-top: 20px;
    text-align: center;
    font-size: 18px;
    color: #222222;
  }
  .bm-tags{
    margin-top: 20px;
    padding:15px

  }
  .tag-level-1{
    padding: 0px 15px 0px 15px;
    font-size: 30px;
    font-weight: 700;
    color: black;
  }
.tag-level-2{
  padding: 0px 15px 0px 15px;
  font-size: 20px;
  color: #5E5E5E;
}
 .tag-level-3{
   padding: 0px 15px 0px 15px;
  font-size: 15px;
  color: #b0afaf;
}
</style>
