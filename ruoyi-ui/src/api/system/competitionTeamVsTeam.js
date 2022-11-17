import request from '@/utils/request'

// 查询赛会中-球队VS球队关系列表
export function listCompetitionTeamVsTeam(query) {
  return request({
    url: '/system/competitionTeamVsTeam/list',
    method: 'get',
    params: query
  })
}

// 查询赛会中-球队VS球队关系详细
export function getCompetitionTeamVsTeam(id) {
  return request({
    url: '/system/competitionTeamVsTeam/' + id,
    method: 'get'
  })
}

// 新增赛会中-球队VS球队关系
export function addCompetitionTeamVsTeam(data) {
  return request({
    url: '/system/competitionTeamVsTeam',
    method: 'post',
    data: data
  })
}
export function getCompetitionUnifiedRecord(id) {
  return request({
    url: '/system/competitionTeamVsTeam/competitionUnifiedRecord/' + id,
    method: 'get'
  })
}
export function getCompetitionVsRecordById(id) {
  return request({
    url: '/system/competitionTeamVsTeam/getCompetitionVsRecordById/' + id,
    method: 'get'
  })
}

// 修改赛会中-球队VS球队关系
export function updateCompetitionTeamVsTeam(data) {
  return request({
    url: '/system/competitionTeamVsTeam',
    method: 'put',
    data: data
  })
}

// 删除赛会中-球队VS球队关系
export function delCompetitionTeamVsTeam(id) {
  return request({
    url: '/system/competitionTeamVsTeam/' + id,
    method: 'delete'
  })
}