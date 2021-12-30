import request from '@/utils/request'

// 查询英语单词列表
export function listWord(query) {
  return request({
    url: '/english/word/list',
    method: 'get',
    params: query
  })
}

// 查询英语单词详细
export function getWord(id) {
  return request({
    url: '/english/word/' + id,
    method: 'get'
  })
}

export function getWordRPC(id) {
  return request({
    url: '/english/word/rpc/' + id,
    method: 'get'
  })
}


// 新增英语单词
export function addWord(data) {
  return request({
    url: '/english/word',
    method: 'post',
    data: data
  })
}

// 修改英语单词
export function updateWord(data) {
  return request({
    url: '/english/word',
    method: 'put',
    data: data
  })
}

// 删除英语单词
export function delWord(id) {
  return request({
    url: '/english/word/' + id,
    method: 'delete'
  })
}
