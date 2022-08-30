import request from '@/utils/request'

// 查询球场管理列表
export function listWxBuilding(query) {
  return request({
    url: '/system/WxBuilding/list',
    method: 'get',
    params: query
  })
}

// 查询球场管理详细
export function getWxBuilding(id) {
  return request({
    url: '/system/WxBuilding/' + id,
    method: 'get'
  })
}

// 新增球场管理
export function addWxBuilding(data) {
  return request({
    url: '/system/WxBuilding',
    method: 'post',
    data: data
  })
}

// 修改球场管理
export function updateWxBuilding(data) {
  return request({
    url: '/system/WxBuilding',
    method: 'put',
    data: data
  })
}

// 删除球场管理
export function delWxBuilding(id) {
  return request({
    url: '/system/WxBuilding/' + id,
    method: 'delete'
  })
}
