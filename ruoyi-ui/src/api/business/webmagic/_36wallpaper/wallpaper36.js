import request from '@/utils/request'

// 获取参数配置
export function getSettings() {
  return request({
    url: '/webmagic/_36wallpaper/getSettings',
    method: 'get',
  })
}


// 修改参数配置
export function updateSettings(json) {
  return request({
    url: '/webmagic/_36wallpaper/updateSettings',
    method: 'put',
    params: json
  })
}


// 重置参数配置
export function resetSettings() {
  return request({
    url: '/webmagic/_36wallpaper/reset',
    method: 'put',
  })
}
