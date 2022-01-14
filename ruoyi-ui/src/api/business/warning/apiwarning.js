import request from '@/utils/request'

// 查询API预警信息列表
export function listApiwarningInfo(query) {
  return request({
    url: '/warning/apiwarning/list',
    method: 'get',
    params: query
  })
}

// 查询API预警详细信息
export function getApiwarningInfo(id) {
  return request({
    url: '/warning/apiwarning/' + id,
    method: 'get'
  })
}

// 修改API预警信息
export function updateApiwarningInfo(data) {
  return request({
    url: '/warning/apiwarning/edit',
    method: 'put',
    data: data
  })
}


// 查询API预警列表
export function listApiwarning(query) {
  return request({
    url: '/warning/apiwarning/apiwarnlist',
    method: 'get',
    params: query
  })
}

// 处理单个预警
export function handleWarning(id) {
  return request({
    url: '/warning/apiwarning/handle/'+id,
    method: 'put',
  })
}
