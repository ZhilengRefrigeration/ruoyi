import request from '@/utils/request'

// 查询计划列表
export function listPlan(query) {
  return request({
    url: '/potenza/plan/list',
    method: 'get',
    params: query
  })
}

// 查询计划详细
export function getPlan(planId) {
  return request({
    url: '/potenza/plan/planById' + planId,
    method: 'get'
  })
}

// 新增计划
export function addPlan(data) {
  return request({
    url: '/potenza/plan/planInsert',
    method: 'post',
    data: data
  })
}

// 修改计划
export function updatePlan(data) {
  return request({
    url: '/potenza/plan/planUpdate',
    method: 'put',
    data: data
  })
}

// 删除计划
export function delPlan(planId) {
  return request({
    url: '/potenza/plan/planIds/' + planId,
    method: 'delete'
  })
}
