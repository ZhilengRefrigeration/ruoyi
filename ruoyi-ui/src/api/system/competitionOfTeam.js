import request from '@/utils/request'

// 查询赛会中-参赛队伍列表
export function listCompetitionOfTeam(query) {
  return request({
    url: '/system/competitionOfTeam/list',
    method: 'get',
    params: query
  })
}

// 查询赛会中-参赛队伍详细
export function getCompetitionOfTeam(id) {
  return request({
    url: '/system/competitionOfTeam/' + id,
    method: 'get'
  })
}

// 新增赛会中-参赛队伍
export function addCompetitionOfTeam(data) {
  return request({
    url: '/system/competitionOfTeam',
    method: 'post',
    data: data
  })
}

// 修改赛会中-参赛队伍
export function updateCompetitionOfTeam(data) {
  return request({
    url: '/system/competitionOfTeam',
    method: 'put',
    data: data
  })
}
//赛会中-参赛队伍批量修改
export function batchEditById(data) {
  return request({
    url: '/system/competitionOfTeam/batchEditById',
    method: 'put',
    data: data
  })
}
//赛会中-参赛队伍移入分组
export function intoTeamGroup(data,competitionGroup) {
  return request({
    url: '/system/competitionOfTeam/intoTeamGroup/'+competitionGroup,
    method: 'post',
    data: data
  })
}
//赛会中-参赛队伍移除分组
export function removeTeamGroup(ids,data) {
  return request({
    url: '/system/competitionOfTeam/removeTeamGroup/'+ids,
    method: 'put',
    data: data
  })
}
// 删除赛会中-参赛队伍
export function delCompetitionOfTeam(id) {
  return request({
    url: '/system/competitionOfTeam/' + id,
    method: 'delete'
  })
}
