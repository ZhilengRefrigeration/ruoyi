import request from '@/utils/request'

// 登录方法
export function login(username, password, code, uuid) {
  return request({
    url: '/auth/login',
    headers: {
      isToken: false
    },
    method: 'post',
    data: { username, password, code, uuid }
  })
}

// 微信扫码登录状态检查方法
export function wxScanLoginCheck(query) {
  return request({
    url: '/auth/wxScanLoginCheck',
    headers: {
      isToken: false
    },
    method: 'get',
    params: query
  })
}
// 注册方法
export function register(data) {
  return request({
    url: '/auth/register',
    headers: {
      isToken: false
    },
    method: 'post',
    data: data
  })
}

// 刷新方法
export function refreshToken() {
  return request({
    url: '/auth/refresh',
    method: 'post'
  })
}

// 获取用户详细信息
export function getInfo(params) {
  return request({
    url: '/system/user/getInfo',
    method: 'get',
    params: params
  })
}
// 获取微信扫码用户详细信息
export function getWxScanInfo(params) {
  return request({
    url: '/system/user/getWxScanInfo',
    method: 'get',
    params: params
  })
}


// 退出方法
export function logout() {
  return request({
    url: '/auth/logout',
    method: 'delete'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/code',
    headers: {
      isToken: false
    },
    method: 'get',
    timeout: 20000
  })
}
