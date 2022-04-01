import request from '@/utils/request'

// 查询缴费订单列表
export function listOrder(query) {
  return request({
    url: '/xjt/order/list',
    method: 'get',
    params: query
  })
}

// 查询缴费订单详细
export function getOrder(id) {
  return request({
    url: '/xjt/order/' + id,
    method: 'get'
  })
}

// 新增缴费订单
export function addOrder(data) {
  return request({
    url: '/xjt/order',
    method: 'post',
    data: data
  })
}

// 修改缴费订单
export function updateOrder(data) {
  return request({
    url: '/xjt/order',
    method: 'put',
    data: data
  })
}

// 删除缴费订单
export function delOrder(id) {
  return request({
    url: '/xjt/order/' + id,
    method: 'delete'
  })
}
