import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listRel(query) {
  return request({
    url: '/system/rel/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getRel(id) {
  return request({
    url: '/system/rel/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addRel(data) {
  return request({
    url: '/system/rel',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateRel(data) {
  return request({
    url: '/system/rel',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delRel(id) {
  return request({
    url: '/system/rel/' + id,
    method: 'delete'
  })
}
