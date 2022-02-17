import request from '@/utils/request'

// 查询爬虫日志列表
export function listWebmagicLog(query) {
  return request({
    url: '/log/reptileLog/list',
    method: 'get',
    params: query
  })
}

// 删除爬虫日志
export function delWebmagicLog(id) {
  return request({
    url: '/log/reptileLog/' + id,
    method: 'delete'
  })
}
