import request from '@/utils/request'

// 查询爬虫微信搜狗搜索列表
export function listWeixinsougou(query) {
  return request({
    url: '/webmagic/weixin_sougou/list',
    method: 'get',
    params: query
  })
}

// 删除爬虫微信搜狗搜索
export function delWeixinsougou(id) {
  return request({
    url: '/webmagic/weixin_sougou/' + id,
    method: 'delete'
  })
}
