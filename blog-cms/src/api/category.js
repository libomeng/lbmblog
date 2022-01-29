import request from '@/utils/request'

export default {
  getCategoryList() {
    return request({
      url: `/admin/category`,
      method: 'get'
    })
  },
  getList() {
    return request({
      url: `/admin/category/getList`,
      method: 'get'
    })
  },
  update(category) {
    return request({
      url: `/admin/category/update`,
      method: 'post',
      data: category
    })
  },
  create(category) {
    return request({
      url: `/admin/category/add`,
      method: 'post',
      data: category
    })
  },
  remove(id) {
    return request({
      url: `/admin/category/remove/${id}`,
      method: 'post'
    })
  }
}
