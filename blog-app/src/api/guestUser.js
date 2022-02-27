import request from '@/request'
export default {
  access(user) {
    return request({
      url: '/guestUser/access',
      method: 'post',
      data: user
    })
  },
}
