import request from '@/utils/request'

// 查询爬虫中关村笔记本搜索列表
export function listZolNotebook(query) {
  return request({
    url: '/webmagic/zol-notebook/list',
    method: 'get',
    params: query
  })
}
