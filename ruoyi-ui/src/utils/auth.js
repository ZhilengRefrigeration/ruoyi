import Cookies from 'js-cookie'

const suffix = `ruoyi`

const TokenKey = 'Admin-Token' + suffix
const RefreshTokenKey = 'Admin-Refresh-Token' + suffix
const ExpiresInKey = 'Admin-Expires-In' + suffix

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(v) {
  return Cookies.set(TokenKey, v)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

/**
 * 存储令牌信息 refresh_token expires_in 等等
 * @param token
 * @returns {*}
 */
export function getRefreshToken() {
  // console.log(`从Cookie获取refresh_token`)
  return Cookies.get(RefreshTokenKey) || ``
}

export function setRefreshToken(v) {
  return Cookies.set(RefreshTokenKey, v)
}

export function removeRefreshToken() {
  return Cookies.remove(RefreshTokenKey)
}

/**
 *
 * @returns {*}
 */
export function getExpiresIn() {

  const time = Cookies.get(ExpiresInKey) || -1 // -1说明cookie没有过期时间，用户还没有登录或者准备登录

  // // console.log(`从Cookie获取token过期时间 === `, new Date(parseInt(time)))
  return time
}

export function setExpiresIn(v) {
  return Cookies.set(ExpiresInKey, v)
}

export function removeExpiresIn() {
  return Cookies.remove(ExpiresInKey)
}
