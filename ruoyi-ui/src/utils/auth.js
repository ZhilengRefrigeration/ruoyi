import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'

const ExpiresInKey = 'Admin-Expires-In'

const WxScanUserIdKey = 'Admin-Wx-UserId-In'
export function getWxScanUserId() {
    return Cookies.get(WxScanUserIdKey)
}
export function setWxScanUserId(userId) {
    return Cookies.set(WxScanUserIdKey, userId)
}
export function removeWxScanUserId() {
    return Cookies.remove(WxScanUserIdKey)
}
export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function getExpiresIn() {
  return Cookies.get(ExpiresInKey) || -1
}

export function setExpiresIn(time) {
  return Cookies.set(ExpiresInKey, time)
}

export function removeExpiresIn() {
  return Cookies.remove(ExpiresInKey)
}
