import request from '@/utils/request'

// 热搜榜接口
export function getTopsearch() {
  return request({
    url: '/openapi/topsearch/getTopsearch',
    method: 'get',
  })
}
