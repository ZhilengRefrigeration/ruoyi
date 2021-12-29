import request from '@/utils/request'

// 查询文案api，通过api获取文案信息列表
export function listCopyWriting(query) {
  return request({
    url: '/openapi/copyWriting/list',
    method: 'get',
    params: query
  })
}

// 查询文案api，通过api获取文案信息详细
export function getCopyWriting(id) {
  return request({
    url: '/openapi/copyWriting/' + id,
    method: 'get'
  })
}

// 删除文案api，通过api获取文案信息
export function delCopyWriting(id) {
  return request({
    url: '/openapi/copyWriting/' + id,
    method: 'delete'
  })
}
