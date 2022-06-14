//首页 api

import request from '@/utils/request'

export function showWbSearch() {
  return request({
    url: '/openapi/index/showWbSearch',
    method: 'get'
  })
}

export function showCopyWriting() {
  return request({
    url: '/openapi/index/showCopyWriting',
    method: 'get'
  })
}


