import request from '@/utils/request'

// 查询基本库存列表
export function listBaseStock(query) {
  return request({
    url: '/wms/BaseStock/list',
    method: 'get',
    params: query
  })
}

// TODO 查询基本库存详细 主键需要注意
export function getBaseStock(whsCd) {
  return request({
    url: '/wms/BaseStock/' + whsCd,
    method: 'get'
  })
}
