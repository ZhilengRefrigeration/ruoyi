import request from '@/utils/request'

// 查询约战列表
export function listVs(query) {
  return request({
    url: '/system/vs/list',
    method: 'get',
    params: query
  })
}

// 查询约战详细
export function getVs(id) {
  return request({
    url: '/system/vs/' + id,
    method: 'get'
  })
}

// 新增约战
export function addVs(data) {
  return request({
    url: '/system/vs',
    method: 'post',
    data: data
  })
}

// 修改约战
export function updateVs(data) {
  return request({
    url: '/system/vs',
    method: 'put',
    data: data
  })
}

// 删除约战
export function delVs(id) {
  return request({
    url: '/system/vs/' + id,
    method: 'delete'
  })
}
