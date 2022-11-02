import request from '@/utils/request'

// 查询比赛信息列表
export function listCompetition(query) {
  return request({
    url: '/system/competition/list',
    method: 'get',
    params: query
  })
}

// 查询比赛信息详细
export function getCompetition(id) {
  return request({
    url: '/system/competition/' + id,
    method: 'get'
  })
}

// 新增比赛信息
export function addCompetition(data) {
  return request({
    url: '/system/competition',
    method: 'post',
    data: data
  })
}

// 修改比赛信息
export function updateCompetition(data) {
  return request({
    url: '/system/competition',
    method: 'put',
    data: data
  })
}

// 删除比赛信息
export function delCompetition(id) {
  return request({
    url: '/system/competition/' + id,
    method: 'delete'
  })
}
