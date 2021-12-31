import request from '@/utils/request'

// 查询英语一言列表
export function listOneenglish(query) {
  return request({
    url: '/openapi/oneenglish/list',
    method: 'get',
    params: query
  })
}

// 查询英语一言详细
export function getOneenglish(id) {
  return request({
    url: '/openapi/oneenglish/' + id,
    method: 'get'
  })
}

// 删除英语一言
export function delOneenglish(id) {
  return request({
    url: '/openapi/oneenglish/' + id,
    method: 'delete'
  })
}
