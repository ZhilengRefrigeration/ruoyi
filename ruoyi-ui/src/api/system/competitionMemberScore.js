import request from '@/utils/request'

// 查询赛会中-赛程-人员得分列表
export function listCompetitionMemberScore(query) {
  return request({
    url: '/system/competitionMemberScore/list',
    method: 'get',
    params: query
  })
}

// 查询赛会中-赛程-人员得分详细
export function getCompetitionMemberScore(id) {
  return request({
    url: '/system/competitionMemberScore/' + id,
    method: 'get'
  })
}

// 新增赛会中-赛程-人员得分
export function addCompetitionMemberScore(data) {
  return request({
    url: '/system/competitionMemberScore',
    method: 'post',
    data: data
  })
}

// 修改赛会中-赛程-人员得分
export function updateCompetitionMemberScore(data) {
  return request({
    url: '/system/competitionMemberScore',
    method: 'put',
    data: data
  })
}

// 删除赛会中-赛程-人员得分
export function delCompetitionMemberScore(id) {
  return request({
    url: '/system/competitionMemberScore/' + id,
    method: 'delete'
  })
}
