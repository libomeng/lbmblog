import request from '@/request'

export function getAllCategorys() {
  return request({
    url: '/categorys',
    method: 'get',
  })
}

export function getAllCategorysDetail() {
  return request({
    url: '/categorys/detail',
    method: 'get',
  })
}

export function getCategory(id) {
  return request({
    url: `/categorys/${id}`,
    method: 'get',
  })
}

export function getCategoryDetail(id) {
  return request({
    url: `/categorys/detail/${id}`,
    method: 'get',
  })
}

export function getCategoryCount() {
  return request({
    url: '/categorys/count',
    method: 'get',
  })
}

export function getAllArticleCountForCategory() {
  return request({
    url: '/categorys/allArticleCountForCategory',
    method: 'get',
  })
}
