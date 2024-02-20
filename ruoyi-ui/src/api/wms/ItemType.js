import request from '@/utils/request'

// 查询物品类型列表
export function listItemType(query) {
  return request({
    url: '/wms/ItemType/list',
    method: 'get',
    params: query
  })
}

// 查询物品类型详细
export function getItemType(itemTypeCd) {
  return request({
    url: '/wms/ItemType/' + itemTypeCd,
    method: 'get'
  })
}

// 新增物品类型
export function addItemType(data) {
  return request({
    url: '/wms/ItemType',
    method: 'post',
    data: data
  })
}

// 修改物品类型
export function updateItemType(data) {
  return request({
    url: '/wms/ItemType',
    method: 'put',
    data: data
  })
}

// 删除物品类型
export function delItemType(itemTypeCd) {
  return request({
    url: '/wms/ItemType/' + itemTypeCd,
    method: 'delete'
  })
}
