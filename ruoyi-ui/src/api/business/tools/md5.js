import request from '@/utils/request'

//加密
export function encryption(data) {
  return request({
    url: '/openapi/md5/encryption',
    method: 'get',
    params: data
  })
}

//解密
export function decrypt(data) {
  return request({
    url: '/openapi/md5/decrypt',
    method: 'get',
    params: data
  })
}
