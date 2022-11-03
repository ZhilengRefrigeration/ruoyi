import request from '@/utils/request'

// 查询赛会中-分组列表
export function listCompetitionTeamGroup(query) {
  return request({
    url: '/system/competitionTeamGroup/list',
    method: 'get',
    params: query
  })
}

// 查询赛会中-分组详细
export function getCompetitionTeamGroup(id) {
  return request({
    url: '/system/competitionTeamGroup/' + id,
    method: 'get'
  })
}

// 新增赛会中-分组
export function addCompetitionTeamGroup(data) {
  return request({
    url: '/system/competitionTeamGroup',
    method: 'post',
    data: data
  })
}

// 修改赛会中-分组
export function updateCompetitionTeamGroup(data) {
  return request({
    url: '/system/competitionTeamGroup',
    method: 'put',
    data: data
  })
}

// 删除赛会中-分组
export function delCompetitionTeamGroup(id) {
  return request({
    url: '/system/competitionTeamGroup/' + id,
    method: 'delete'
  })
}
