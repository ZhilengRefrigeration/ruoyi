import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listLabel(query) {
  return request({
    url: '/system/label/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getLabel(id) {
  return request({
    url: '/system/label/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addLabel(data) {
  return request({
    url: '/system/label',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateLabel(data) {
  return request({
    url: '/system/label',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delLabel(id) {
  return request({
    url: '/system/label/' + id,
    method: 'delete'
  })
}
