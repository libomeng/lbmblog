import request from '@/utils/request'

export default {
  getArticleList(page, limit) {
    return request({
      url: `/admin/article/list/${page}/${limit}`,
      method: 'get'
    })
  },
  getArticleInfo(id) {
    return request({
      url: `/admin/article/info/${id}`,
      method: 'get'
    })
  },
  createArticle(article) {
    return request({
      url: `/admin/article/create`,
      method: 'post',
      data: article
    })
  }
}
