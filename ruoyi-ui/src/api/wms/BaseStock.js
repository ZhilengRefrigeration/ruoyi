import request from '@/utils/request'

// 查询基本库存列表
export function listBaseStock(query) {
  return request({
    url: '/wms/BaseStock/list',
    method: 'get',
    params: query
  })
}

// 询基本库存详细
export function getBaseStock(data) {
  return request({
    url: '/wms/BaseStock/getInfo',
    method: 'post',
    data: data
  })
}
