import request from '@/utils/request'

// 查询日志列表
export function translation(query) {
  return request({
    url: '/english/translation',
    method: 'post',
    data: query
  })
}
