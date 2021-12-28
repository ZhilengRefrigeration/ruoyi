import request from '@/utils/request'

// 翻译
export function translation(query) {
  return request({
    url: '/english/translation',
    method: 'post',
    data: query
  })
}

//获取文案
export function getCopyWriting(query) {
  return request({
    url: '/english/copyWriting',
    method: 'get',
    data: query
  })
}
