import request from '@/utils/request'

// 查询球队管理列表
export function listBasketBallTeam(query) {
  return request({
    url: '/system/basketBallTeam/list',
    method: 'get',
    params: query
  })
}

// 查询球队管理详细
export function getBasketBallTeam(id) {
  return request({
    url: '/system/basketBallTeam/' + id,
    method: 'get'
  })
}

// 新增球队管理
export function addBasketBallTeam(data) {
  return request({
    url: '/system/basketBallTeam',
    method: 'post',
    data: data
  })
}

// 修改球队管理
export function updateBasketBallTeam(data) {
  return request({
    url: '/system/basketBallTeam',
    method: 'put',
    data: data
  })
}

// 删除球队管理
export function delBasketBallTeam(id) {
  return request({
    url: '/system/basketBallTeam/' + id,
    method: 'delete'
  })
}
