import request from '@/utils/request'

// 获取参数配置
export function getSettings() {
  return request({
    url: '/webmagic/weixin_link/getSettings',
    method: 'get',
  })
}

// 修改参数配置
export function updateSettings(path) {
  return request({
    url: '/webmagic/weixin_link/updateSettings',
    method: 'put',
    params: path
  })
}


// 重置参数配置
export function resetSettings() {
  return request({
    url: '/webmagic/weixin_link/resetSettings',
    method: 'put',
  })
}

// 获取文章图片（执行爬虫）
export function getPicture(link) {
  return request({
    url: '/webmagic/weixin_link/getPicture',
    method: 'get',
    params: link
  })
}

