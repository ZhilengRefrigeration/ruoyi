import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listAttachment(query) {
  return request({
    url: '/system/attachment/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getAttachment(id) {
  return request({
    url: '/system/attachment/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addAttachment(data) {
  return request({
    url: '/system/attachment',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateAttachment(data) {
  return request({
    url: '/system/attachment',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delAttachment(id) {
  return request({
    url: '/system/attachment/' + id,
    method: 'delete'
  })
}
