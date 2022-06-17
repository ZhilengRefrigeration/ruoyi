import request from '@/utils/request'

// 查询日志列表
export function listLog(query) {
  return request({
    url: '/log/apilog/list',
    method: 'get',
    params: query
  })
}

// 根据id查询日志
export function getInfo(id) {
  return request({
    url: '/log/apilog/'+id,
    method: 'get'
  })
}

// 查询日志详细
export function getLog(id) {
  return request({
    url: '/log/apilog/' + id,
    method: 'get'
  })
}

// 删除日志
export function delLog(id) {
  return request({
    url: '/log/apilog/' + id,
    method: 'delete'
  })
}

//获取所有api名称
export function getApiName() {
  return request({
    url: '/log/apilog/getApiName' ,
    method: 'get'
  })
}
