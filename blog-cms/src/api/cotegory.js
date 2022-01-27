import request from '@/utils/request'

export default {
  getCategoryList() {
    return request({
      url: `/admin/category`,
      method: 'get'
    })
  }
}
