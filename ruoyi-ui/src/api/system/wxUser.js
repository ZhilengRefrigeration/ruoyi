import request from '@/utils/request'

// 查询微信用户列表
export function listWxUser(query) {
  return request({
    url: '/system/wxUser/list',
    method: 'get',
    params: query
  })
}

// 查询微信用户详细
export function getWxUser(id) {
  return request({
    url: '/system/wxUser/' + id,
    method: 'get'
  })
}

// 新增微信用户
export function addWxUser(data) {
  return request({
    url: '/system/wxUser',
    method: 'post',
    data: data
  })
}

// 修改微信用户
export function updateWxUser(data) {
  return request({
    url: '/system/wxUser',
    method: 'put',
    data: data
  })
}

// 删除微信用户
export function delWxUser(id) {
  return request({
    url: '/system/wxUser/' + id,
    method: 'delete'
  })
}
