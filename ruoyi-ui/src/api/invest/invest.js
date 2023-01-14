import request from '@/utils/request'

// 查询投资列表
export function listInvest(query) {
  return request({
    url: '/invest/invest/list',
    method: 'get',
    params: query
  })
}

// 查询投资详细
export function getInvest(investId) {
  return request({
    url: '/invest/invest/' + investId,
    method: 'get'
  })
}

// 新增投资
export function addInvest(data) {
  return request({
    url: '/invest/invest',
    method: 'post',
    data: data
  })
}

// 修改投资
export function updateInvest(data) {
  return request({
    url: '/invest/invest',
    method: 'put',
    data: data
  })
}

// 删除投资
export function delInvest(investId) {
  return request({
    url: '/invest/invest/' + investId,
    method: 'delete'
  })
}
