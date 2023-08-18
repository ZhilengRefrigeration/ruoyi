import request from '@/utils/request'

// 查询交易申请列表
export function listTrequest(query) {
  return request({
    url: '/system/trequest/list',
    method: 'get',
    params: query
  })
}

// 查询交易申请详细
export function getTrequest(vcTenantId) {
  return request({
    url: '/system/trequest/' + vcTenantId,
    method: 'get'
  })
}

// 新增交易申请
export function addTrequest(data) {
  return request({
    url: '/system/trequest',
    method: 'post',
    data: data
  })
}

// 修改交易申请
export function updateTrequest(data) {
  return request({
    url: '/system/trequest',
    method: 'put',
    data: data
  })
}

// 删除交易申请
export function delTrequest(vcTenantId) {
  return request({
    url: '/system/trequest/' + vcTenantId,
    method: 'delete'
  })
}

// 查询每月金额统计
export function monthEnBalance(query) {
  return request({
    url: '/system/trequest/monthEnBalance',
    method: 'get',
    params: query
  })
}

