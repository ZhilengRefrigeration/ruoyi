import request from '@/utils/request'

// 查询英语文章列表
export function listArticle(query) {
  return request({
    url: '/english/article/list',
    method: 'get',
    params: query
  })
}

// 查询英语文章详细
export function getArticle(id) {
  return request({
    url: '/english/article/' + id,
    method: 'get'
  })
}

// 新增英语文章
export function addArticle(data) {
  return request({
    url: '/english/article',
    method: 'post',
    data: data
  })
}

//上传图片
export function uploadImg(data){
  return request({
    url: '/file/upload',
    method: 'post',
    data: data
  })
}

//删除图片
export function removeImg(url){
  return request({
    url: '/file/remove',
    method: 'delete',
    params: url
  })
}

// 修改英语文章
export function updateArticle(data) {
  return request({
    url: '/english/article',
    method: 'put',
    data: data
  })
}

// 删除英语文章
export function delArticle(id) {
  return request({
    url: '/english/article/' + id,
    method: 'delete'
  })
}
