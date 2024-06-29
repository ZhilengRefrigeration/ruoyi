import request from '@/utils/request'

// 获取微信accessToken
export function getWxApplesAccessToken(query) {
  return request({
    url: '/system/wxApplesCode/getWxApplesAccessToken',
    method: 'get',
    params: query
  })
}

// 获取微信小程序码
export function genWxApplesAqrCode(data) {
  return request({
    url: '/system/wxApplesCode/genWxApplesAqrCode',
    method: 'post',
    data: data
  })

}

//获取微信小程序码二进制数据
export function genWxApplesAqrCodeForPc(data) {
  return request({
    url: '/system/wxApplesCode/genWxApplesAqrCodeForPc',
    method: 'post',
    data: data
  })
}

