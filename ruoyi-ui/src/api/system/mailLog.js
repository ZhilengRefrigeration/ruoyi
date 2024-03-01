import request from '@/utils/request'

// 查询邮件发送日志列表
export function listMailLog(query) {
  return request({
    url: '/system/mailLog/list',
    method: 'get',
    params: query
  })
}

// 查询邮件发送日志详细
export function getMailLog(mailLogId) {
  return request({
    url: '/system/mailLog/' + mailLogId,
    method: 'get'
  })
}

// TODO 临时邮件发送
export function sendTemporality(data) {
  return request({
    url: '/system/mailLog/sendTemporality',
    method: 'post',
    data: data
  })
}

// 删除邮件发送日志
export function delMailLog(mailLogId) {
  return request({
    url: '/system/mailLog/' + mailLogId,
    method: 'delete'
  })
}
