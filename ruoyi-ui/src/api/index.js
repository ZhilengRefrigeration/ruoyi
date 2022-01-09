//首页 api

import request from '@/utils/request'


// 查询英语单词列表
export function getApiAWord(query) {
  return request({
    url: '/openapi/aword/',
    method: 'get',
    params: query
  })
}
