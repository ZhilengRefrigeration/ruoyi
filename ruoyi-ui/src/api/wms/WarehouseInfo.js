import request from '@/utils/request'

// 查询仓库基础信息列表
export function listWarehouseInfo(query) {
  return request({
    url: '/wms/WarehouseInfo/list',
    method: 'get',
    params: query
  })
}

// 查询仓库基础信息详细
export function getWarehouseInfo(whsCd) {
  return request({
    url: '/wms/WarehouseInfo/' + whsCd,
    method: 'get'
  })
}

// 新增仓库基础信息
export function addWarehouseInfo(data) {
  return request({
    url: '/wms/WarehouseInfo',
    method: 'post',
    data: data
  })
}

// 修改仓库基础信息
export function updateWarehouseInfo(data) {
  return request({
    url: '/wms/WarehouseInfo',
    method: 'put',
    data: data
  })
}

// 删除仓库基础信息
export function delWarehouseInfo(whsCd) {
  return request({
    url: '/wms/WarehouseInfo/' + whsCd,
    method: 'delete'
  })
}
