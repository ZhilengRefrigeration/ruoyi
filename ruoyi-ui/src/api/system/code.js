import request from '@/utils/request'

// 查询微信用户小程序二维码列表
export function listCode(query) {
  return request({
    url: '/system/code/list',
    method: 'get',
    params: query
  })
}

// 查询微信用户小程序二维码详细
export function getCode(id) {
  return request({
    url: '/system/code/' + id,
    method: 'get'
  })
}

// 新增微信用户小程序二维码
export function addCode(data) {
  return request({
    url: '/system/code',
    method: 'post',
    data: data
  })
}

// 修改微信用户小程序二维码
export function updateCode(data) {
  return request({
    url: '/system/code',
    method: 'put',
    data: data
  })
}
// 新增微信用户小程序二维码
export function genAqrCode(data) {
  return request({
    url: '/system/code/genAqrCode',
    method: 'post',
    data: data
  })
}
// 删除微信用户小程序二维码
export function delCode(id) {
  return request({
    url: '/system/code/' + id,
    method: 'delete'
  })
}
