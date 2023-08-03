import request from '@/utils/request'

// 查询客户信息列表
export function listCustomer(query) {
  return request({
    url: '/system/customer/list',
    method: 'get',
    params: query
  })
}
// 查询客户预约信息列表
export function listCustomerMaker(query) {
  return request({
    url: '/system/customer/makerList',
    method: 'get',
    params: query
  })
}
// 查询客户信息列表
export function listCustomerFollow(query) {
  return request({
    url: '/system/up/list',
    method: 'get',
    params: query
  })
}
// 查询客户信息详细
export function getCustomer(id) {
  return request({
    url: '/system/customer/' + id,
    method: 'get'
  })
}

// 新增客户信息
export function addCustomer(data) {
  return request({
    url: '/system/customer',
    method: 'post',
    data: data
  })
}

// 修改客户信息
export function updateCustomer(data) {
  return request({
    url: '/system/customer',
    method: 'put',
    data: data
  })
}
// 新增客户跟进信息
export function addCustomerFollowRecerd(data) {
  return request({
    url: '/system/up',
    method: 'post',
    data: data
  })
}
export function  addCustomerOrderRecerd(data){
  return request({
    url: '/system/customerOrder',
    method: 'post',
    data: data
  })
}
export function updateCustomerFollowRecerd(data) {
  return request({
    url: '/system/up',
    method: 'put',
    data: data
  })
}
//确认到店
export function confirmToStore(data) {
  return request({
    url: '/system/up',
    method: 'put',
    data: data
  })
}
// 删除客户信息
export function delCustomer(id) {
  return request({
    url: '/system/customer/' + id,
    method: 'delete'
  })
}
