//首页 api

import request from '@/utils/request'

export function showData() {
  return request({
    url: '/openapi/index/showData',
    method: 'get'
  })
}



