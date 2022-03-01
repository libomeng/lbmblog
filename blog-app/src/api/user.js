import request from '@/request'

export default {
  issueToken(user) {
    return request({
      url: 'guestUser/issue/token',
      method: 'post',
      data: user
    })
  },
  getUserInfo() {
    return request({
      url: '/guestUser/info',
      method: 'post'
    })
  }
}
