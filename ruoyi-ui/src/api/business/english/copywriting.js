import request from '@/utils/request'

// 查询文案api，通过api获取文案信息列表
export function listCopywriting(query) {
  return request({
    url: '/english/copywriting/list',
    method: 'get',
    params: query
  })
}

// 查询文案api，通过api获取文案信息详细
export function getCopywriting(id) {
  return request({
    url: '/english/copywriting/' + id,
    method: 'get'
  })
}

// 新增文案api，通过api获取文案信息
export function addCopywriting(data) {
  return request({
    url: '/english/copywriting',
    method: 'post',
    data: data
  })
}

// 修改文案api，通过api获取文案信息
export function updateCopywriting(data) {
  return request({
    url: '/english/copywriting',
    method: 'put',
    data: data
  })
}

// 删除文案api，通过api获取文案信息
export function delCopywriting(id) {
  return request({
    url: '/english/copywriting/' + id,
    method: 'delete'
  })
}
