import request from '@/utils/request'

// 获取微信accessToken
export function getWxApplesAccessToken(query) {
  return request({
    url: '/system/wxApplesCode/getWxApplesAccessToken',
    method: 'get',
    params: query
  })
}

// 查询约战详细
export function genWxApplesAqrCode(data) {
  return request({
    url: '/system/wxApplesCode/genWxApplesAqrCode',
    method: 'post',
    data: data
  })
}

