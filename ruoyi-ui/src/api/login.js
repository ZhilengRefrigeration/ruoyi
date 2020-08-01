import request from '@/utils/request'

const client_id = 'web'
const client_secret = '123456'
let grant_type = 'password'
const scope = 'server'

// 刷新方法
export function refreshToken( refresh_token ) {
  grant_type = `refresh_token`
  return request({
    url: '/auth/oauth/token',
    method: 'post',
    params: { client_id, client_secret, grant_type, scope, refresh_token }
  })
}

// 登录方法
export function login(username, password, code, uuid) {
  grant_type = 'password'
  return request({
    url: '/auth/oauth/token',
    method: 'post',
    params: { username, password, code, uuid, client_id, client_secret, grant_type, scope }
  })
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/system/user/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/auth/token/logout',
    method: 'delete'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/code',
    method: 'get'
  })
}
