import request from '@/utils/request'

// 查询物品基础信息列表
export function listItemInfo(query) {
  return request({
    url: '/wms/ItemInfo/list',
    method: 'get',
    params: query
  })
}

// 查询物品基础信息详细
export function getItemInfo(itemCd) {
  return request({
    url: '/wms/ItemInfo/' + itemCd,
    method: 'get'
  })
}

// 新增物品基础信息
export function addItemInfo(data) {
  return request({
    url: '/wms/ItemInfo',
    method: 'post',
    data: data
  })
}

// 修改物品基础信息
export function updateItemInfo(data) {
  return request({
    url: '/wms/ItemInfo',
    method: 'put',
    data: data
  })
}

// 删除物品基础信息
export function delItemInfo(itemCd) {
  return request({
    url: '/wms/ItemInfo/' + itemCd,
    method: 'delete'
  })
}
