import request from '@/utils/request'

// 查询日志列表
export function listLog(query) {
  return request({
    url: '/english/log/list',
    method: 'get',
    params: query
  })
}

// 查询日志详细
export function getLog(id) {
  return request({
    url: '/english/log/' + id,
    method: 'get'
  })
}

// 删除日志
export function delLog(id) {
  return request({
    url: '/english/log/' + id,
    method: 'delete'
  })
}
