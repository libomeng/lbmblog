import request from '@/utils/request'

export default {
  getTagList() {
    return request({
      url: `/admin/tag`,
      method: 'get'
    })
  }
}
