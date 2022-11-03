import request from '@/utils/request'

// 查询比赛参与人员列表
export function listCompetitionMembers(query) {
  return request({
    url: '/system/competitionMembers/list',
    method: 'get',
    params: query
  })
}

// 查询比赛参与人员详细
export function getCompetitionMembers(id) {
  return request({
    url: '/system/competitionMembers/' + id,
    method: 'get'
  })
}

// 新增比赛参与人员
export function addCompetitionMembers(data) {
  return request({
    url: '/system/competitionMembers',
    method: 'post',
    data: data
  })
}

// 修改比赛参与人员
export function updateCompetitionMembers(data) {
  return request({
    url: '/system/competitionMembers',
    method: 'put',
    data: data
  })
}

// 删除比赛参与人员
export function delCompetitionMembers(id) {
  return request({
    url: '/system/competitionMembers/' + id,
    method: 'delete'
  })
}
