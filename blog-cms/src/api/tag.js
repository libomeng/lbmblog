import request from '@/utils/request'

export default {
  getTagList() {
    return request({
      url: `/admin/tag`,
      method: 'get'
    })
  },
  getTagPage(page, limit, query) {
    return request({
      url: `/admin/tag/getConditionList/${page}/${limit}`,
      method: 'post',
      data: query
    })
  },
  update(tag) {
    return request({
      url: `/admin/tag/update`,
      method: 'post',
      data: tag
    })
  },
  remove(id) {
    return request({
      url: `/admin/tag/remove/${id}`,
      method: 'POST'
    })
  }
}
