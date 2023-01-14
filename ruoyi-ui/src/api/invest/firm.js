import request from '@/utils/request'

// 查询企业列表
export function listFirm(query) {
  return request({
    url: '/invest/firm/list',
    method: 'get',
    params: query
  })
}

// 查询企业详细
export function getFirm(firmId) {
  return request({
    url: '/invest/firm/' + firmId,
    method: 'get'
  })
}

// 新增企业
export function addFirm(data) {
  return request({
    url: '/invest/firm',
    method: 'post',
    data: data
  })
}

// 修改企业
export function updateFirm(data) {
  return request({
    url: '/invest/firm',
    method: 'put',
    data: data
  })
}

// 删除企业
export function delFirm(firmId) {
  return request({
    url: '/invest/firm/' + firmId,
    method: 'delete'
  })
}
