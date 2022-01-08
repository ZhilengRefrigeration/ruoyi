import request from '@/utils/request'

// 查询每日一句列表
export function listAword(query) {
  return request({
    url: '/openapi/aword/list',
    method: 'get',
    params: query
  })
}

// 查询每日一句详细
export function getAword(id) {
  return request({
    url: '/openapi/aword/' + id,
    method: 'get'
  })
}


// 删除每日一句
export function delAword(id) {
  return request({
    url: '/openapi/aword/' + id,
    method: 'delete'
  })
}
