import request from '@/utils/request'

export function login(userInfo) {
  return request({
    url: '/admin/user/login',
    method: 'post',
    data:userInfo
  })
}

export function getInfo(token) {
  return request({
    url: '/admin/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}
