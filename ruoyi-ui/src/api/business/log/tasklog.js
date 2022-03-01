import request from '@/utils/request'

// 查询任务日志列表
export function listTaskLog(query) {
  return request({
    url: '/log/taskLog/list',
    method: 'get',
    params: query
  })
}


// 删除任务日志
export function delTaskLog(id) {
  return request({
    url: '/log/taskLog/' + id,
    method: 'delete'
  })
}
