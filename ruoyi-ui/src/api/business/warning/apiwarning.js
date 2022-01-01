import request from '@/utils/request'

// 查询API预警列表
export function listApiwarning(query) {
  return request({
    url: '/warning/apiwarning/list',
    method: 'get',
    params: query
  })
}

// 查询API预警详细
export function getApiwarning(id) {
  return request({
    url: '/warning/apiwarning/' + id,
    method: 'get'
  })
}

// 修改API预警
export function updateApiwarning(data) {
  return request({
    url: '/warning/apiwarning/edit',
    method: 'put',
    data: data
  })
}

