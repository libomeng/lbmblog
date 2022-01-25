import request from '@/utils/request'

export default {
  getArticleList(page,limit){
   return request({
      url:`/admin/article/list/${page}/${limit}`,
      method:'get'
    })
  }
}
