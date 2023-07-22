import request from '@/utils/request'

// 查询赛会-权限分享列表
export function listCompetitionPermissions(query) {
  return request({
    url: '/system/competitionPermissions/list',
    method: 'get',
    params: query
  })
}

// 查询赛会-权限分享详细
export function getCompetitionPermissions(id) {
  return request({
    url: '/system/competitionPermissions/' + id,
    method: 'get'
  })
}

// 新增赛会-权限分享
export function addCompetitionPermissions(data) {
  return request({
    url: '/system/competitionPermissions',
    method: 'post',
    data: data
  })
}

// 修改赛会-权限分享
export function updateCompetitionPermissions(data) {
  return request({
    url: '/system/competitionPermissions',
    method: 'put',
    data: data
  })
}

// 删除赛会-权限分享
export function delCompetitionPermissions(id) {
  return request({
    url: '/system/competitionPermissions/' + id,
    method: 'delete'
  })
}
