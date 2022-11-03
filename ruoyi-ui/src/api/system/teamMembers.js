import request from '@/utils/request'

// 查询球队人员列表
export function listTeamMembers(query) {
  return request({
    url: '/system/teamMembers/list',
    method: 'get',
    params: query
  })
}

// 查询球队人员详细
export function getTeamMembers(id) {
  return request({
    url: '/system/teamMembers/' + id,
    method: 'get'
  })
}

// 新增球队人员
export function addTeamMembers(data) {
  return request({
    url: '/system/teamMembers',
    method: 'post',
    data: data
  })
}

// 修改球队人员
export function updateTeamMembers(data) {
  return request({
    url: '/system/teamMembers',
    method: 'put',
    data: data
  })
}

// 删除球队人员
export function delTeamMembers(id) {
  return request({
    url: '/system/teamMembers/' + id,
    method: 'delete'
  })
}
