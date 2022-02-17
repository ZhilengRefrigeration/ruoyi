import request from '@/utils/request'

// 查询新浪新闻列表
export function listSinaNews(query) {
  return request({
    url: '/webmagic/sinaNews/list',
    method: 'get',
    params: query
  })
}

// 删除新浪新闻
export function delSinaNews(id) {
  return request({
    url: '/webmagic/sinaNews/' + id,
    method: 'delete'
  })
}

// 获取类型
export function getType() {
  return request({
    url: '/webmagic/sinaNews/getType',
    method: 'get',
  })
}
