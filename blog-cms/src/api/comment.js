import request from '@/utils/request'

export default {
  getComments(page, limit) {
    return request({
      url: `/admin/comment/list/${page}/${limit}`,
      method: 'get'
    })
  }
}
