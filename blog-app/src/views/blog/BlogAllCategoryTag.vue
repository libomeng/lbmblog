<template>
  <div class="bm-tag" v-title data-title="标签分类">
    <el-container>
      <el-main>
        <div class="bm-title"><span>Category</span></div>
        <div class="mb-title-info"><span>目前共计{{categorys.length}}个</span></div>
        <div class="bm-cate">
          <el-link @click="view(category)" :class="classHandler(category.count)" v-for="category in categorys" :key="categorys.id">{{category.categoryName}}</el-link>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import {getAllArticleCountForCategory} from '@/api/category'
export default {
  name: "BlogTag",
  data(){
    return{
      categorys:[],
    }
  },
  created() {
    this.getCategory();
  },
  methods:{
    getCategory(){
      getAllArticleCountForCategory().then(result =>{
        this.categorys = result.data
      })
    },
    classHandler(val){
      if(val>4){
        return 'cate-level-1'
      }if(val>1 && val<=3){
        return 'cate-level-2'
      }if(val=1){
        return 'cate-level-3'
      }
    },
    view(category){
      this.$router.push({path:`/archives/category/${category.id}/${category.categoryName}`})
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
.bm-cate{
  margin-top: 20px;
  padding:15px

}
.cate-level-1{
  padding: 0px 15px 0px 15px;
  font-size: 30px;
  font-weight: 700;
  color: black;
}
.cate-level-2{
  padding: 0px 15px 0px 15px;
  font-size: 20px;
  color: #5E5E5E;
}
.cate-level-3{
  padding: 0px 15px 0px 15px;
  font-size: 15px;
  color: #b0afaf;
}
</style>
