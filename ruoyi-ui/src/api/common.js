//通用的api接口

import request from '@/utils/request'

//上传图片
export function uploadImg(data) {
  return request({
    url: '/file/upload',
    method: 'post',
    data: data
  })
}

//删除图片
export function removeImg(url) {
  return request({
    url: '/file/remove',
    method: 'delete',
    params: url
  })
}

//获取oss上传秘钥
export function policy() {
  return request({
    url: '/file/oss/policy',
    method: 'get',
  })
}
