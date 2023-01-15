import request from '@/utils/request'

// 查询贷款周期列表
export function listPeriods(query) {
  return request({
    url: '/potenza/periods/list',
    method: 'get',
    params: query
  })
}

// 查询贷款周期详细
export function getPeriods(periodsId) {
  return request({
    url: '/potenza/periods/periodsById/' + periodsId,
    method: 'get'
  })
}

// 新增贷款周期
export function addPeriods(data) {
  return request({
    url: '/potenza/periods/periodsInsert',
    method: 'post',
    data: data
  })
}

// 修改贷款周期
export function updatePeriods(data) {
  return request({
    url: '/potenza/periods/periodsUpdate',
    method: 'put',
    data: data
  })
}

// 删除贷款周期
export function delPeriods(periodsId) {
  return request({
    url: '/potenza/periods/' + periodsId,
    method: 'delete'
  })
}


export function detail(data) {
  return request({
    url: '/potenza/periods/detail',
    method: 'post',
    data: data
  })
}
