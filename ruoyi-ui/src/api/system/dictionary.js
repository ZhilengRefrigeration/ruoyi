import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listDictionary(query) {
  return request({
    url: '/system/dictionary/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getDictionary(id) {
  return request({
    url: '/system/dictionary/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addDictionary(data) {
  return request({
    url: '/system/dictionary',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateDictionary(data) {
  return request({
    url: '/system/dictionary',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delDictionary(id) {
  return request({
    url: '/system/dictionary/' + id,
    method: 'delete'
  })
}
