import request from '@/utils/request'

// 查询客户-订车列表
export function listCustomerOrder(query) {
  return request({
    url: '/system/customerOrder/list',
    method: 'get',
    params: query
  })
}
// 查询客户-订车列表
export function getCustomerOrderPage(query) {
  return request({
    url: '/system/customerOrder/getCustomerOrderPage',
    method: 'get',
    params: query
  })
}
export function confirmToOut(data){
  return request({
    url: '/system/customerOrder',
    method: 'put',
    data: data
  })
}
// 查询客户-订车详细
export function getCustomerOrder(id) {
  return request({
    url: '/system/customerOrder/' + id,
    method: 'get'
  })
}

// 新增客户-订车
export function addCustomerOrder(data) {
  return request({
    url: '/system/customerOrder',
    method: 'post',
    data: data
  })
}

// 修改客户-订车
export function updateCustomerOrder(data) {
  return request({
    url: '/system/customerOrder',
    method: 'put',
    data: data
  })
}

// 删除客户-订车
export function delCustomerOrder(id) {
  return request({
    url: '/system/customerOrder/' + id,
    method: 'delete'
  })
}
