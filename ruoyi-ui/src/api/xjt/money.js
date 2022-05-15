import request from '@/utils/request'

// 查询支出或收入详情列表
export function listMoney(query) {
  return request({
    url: '/xjt/money/list',
    method: 'get',
    params: query
  })
}

// 查询支出或收入详情详细
export function getMoney(id) {
  return request({
    url: '/xjt/money/' + id,
    method: 'get'
  })
}

// 新增支出或收入详情
export function addMoney(data) {
  return request({
    url: '/xjt/money',
    method: 'post',
    data: data
  })
}

// 修改支出或收入详情
export function updateMoney(data) {
  return request({
    url: '/xjt/money',
    method: 'put',
    data: data
  })
}

// 删除支出或收入详情
export function delMoney(id) {
  return request({
    url: '/xjt/money/' + id,
    method: 'delete'
  })
}
