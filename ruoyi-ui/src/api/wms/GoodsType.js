import request from '@/utils/request'

// 查询物品类型管理列表
export function listGoodsType(query) {
  return request({
    url: '/wms/GoodsType/list',
    method: 'get',
    params: query
  })
}

// 查询物品类型管理详细
export function getGoodsType(goodsTypeCd) {
  return request({
    url: '/wms/GoodsType/' + goodsTypeCd,
    method: 'get'
  })
}

// 新增物品类型管理
export function addGoodsType(data) {
  return request({
    url: '/wms/GoodsType',
    method: 'post',
    data: data
  })
}

// 修改物品类型管理
export function updateGoodsType(data) {
  return request({
    url: '/wms/GoodsType',
    method: 'put',
    data: data
  })
}

// 删除物品类型管理
export function delGoodsType(goodsTypeCd) {
  return request({
    url: '/wms/GoodsType/' + goodsTypeCd,
    method: 'delete'
  })
}
