import request from '@/utils/request'

// 查询爬虫中关村手机搜索列表
export function listZolPhone(query) {
  return request({
    url: '/webmagic/zol-phone/list',
    method: 'get',
    params: query
  })
}
