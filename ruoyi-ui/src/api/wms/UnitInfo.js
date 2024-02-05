import request from '@/utils/request'

// 查询单位信息管理列表
export function listUnitInfo(query) {
  return request({
    url: '/wms/UnitInfo/list',
    method: 'get',
    params: query
  })
}

// 查询单位信息管理详细
export function getUnitInfo(unitCode) {
  return request({
    url: '/wms/UnitInfo/' + unitCode,
    method: 'get'
  })
}

// 新增单位信息管理
export function addUnitInfo(data) {
  return request({
    url: '/wms/UnitInfo',
    method: 'post',
    data: data
  })
}

// 修改单位信息管理
export function updateUnitInfo(data) {
  return request({
    url: '/wms/UnitInfo',
    method: 'put',
    data: data
  })
}

// 删除单位信息管理
export function delUnitInfo(unitCode) {
  return request({
    url: '/wms/UnitInfo/' + unitCode,
    method: 'delete'
  })
}
