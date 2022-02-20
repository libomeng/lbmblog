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
  },
  updateState(state) {
    return request({
      url: `/admin/article/updateState`,
      method: 'post',
      data: state
    })
  },
  deleteById(id) {
    return request({
      url: `/admin/article/delete/${id}`,
      method: 'post'
    })
  },
  getDeletedArticle() {
    return request({
      url: `/admin/article/getRemoveArticle`,
      method: 'get'
    })
  },
  recycleArticle(id) {
    return request({
      url: `/admin/article/recycleArticle/${id}`,
      method: 'post'
    })
  },
  deleteArticle(id, bodyId) {
    return request({
      url: `/admin/article/deleteArticle/${id}/${bodyId}`,
      method: 'post'
    })
  }
}
