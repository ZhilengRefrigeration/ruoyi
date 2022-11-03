import request from '@/utils/request'

// 查询赛会中-赛程结果记录列表
export function listCompetitionResult(query) {
  return request({
    url: '/system/competitionResult/list',
    method: 'get',
    params: query
  })
}

// 查询赛会中-赛程结果记录详细
export function getCompetitionResult(id) {
  return request({
    url: '/system/competitionResult/' + id,
    method: 'get'
  })
}

// 新增赛会中-赛程结果记录
export function addCompetitionResult(data) {
  return request({
    url: '/system/competitionResult',
    method: 'post',
    data: data
  })
}

// 修改赛会中-赛程结果记录
export function updateCompetitionResult(data) {
  return request({
    url: '/system/competitionResult',
    method: 'put',
    data: data
  })
}

// 删除赛会中-赛程结果记录
export function delCompetitionResult(id) {
  return request({
    url: '/system/competitionResult/' + id,
    method: 'delete'
  })
}
