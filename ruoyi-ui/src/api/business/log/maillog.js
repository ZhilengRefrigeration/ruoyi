import request from '@/utils/request'


// 查询邮件日志列表
export function listMaillog(query) {
  return request({
    url: '/log/maillog/list',
    method: 'get',
    params: query
  })
}

// 查询邮件日志详细
export function getMaillog(id) {
  return request({
    url: '/log/maillog/' + id,
    method: 'get'
  })
}

// 删除邮件日志
export function delMaillog(id) {
  return request({
    url: '/log/maillog/' + id,
    method: 'delete'
  })
}
