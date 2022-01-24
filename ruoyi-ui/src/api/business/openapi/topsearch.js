import request from '@/utils/request'

// 热搜榜接口
export function getTopsearch() {
  return request({
    url: '/openapi/topsearch/getTopsearch',
    method: 'get',
  })
}

//获取历史热搜榜
export function getHistoryTopSearch(value) {
  return request({
    url: '/openapi/topsearch/getHistoryTopSearch',
    method: 'get',
    params: {date:value}
  })
}
