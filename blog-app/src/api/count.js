import request from '@/request'
export default {
  count(){
    return request({
      url: '/count',
      method:'post'
    })
  }

}
