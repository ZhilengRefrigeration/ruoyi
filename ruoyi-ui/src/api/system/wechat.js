import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listWechat(query) {
  return request({
    url: '/system/wechat/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getWechat(id) {
  return request({
    url: '/system/wechat/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addWechat(data) {
  return request({
    url: '/system/wechat',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateWechat(data) {
  return request({
    url: '/system/wechat',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delWechat(id) {
  return request({
    url: '/system/wechat/' + id,
    method: 'delete'
  })
}
