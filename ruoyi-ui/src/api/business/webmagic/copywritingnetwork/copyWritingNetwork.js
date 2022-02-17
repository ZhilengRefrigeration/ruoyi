import request from '@/utils/request'

// 查询文案网列表
export function listCopyWritingNetwork(query) {
  return request({
    url: '/webmagic/copyWritingNetwork/list',
    method: 'get',
    params: query
  })
}

// 删除文案网
export function delCopyWritingNetwork(id) {
  return request({
    url: '/webmagic/copyWritingNetwork/' + id,
    method: 'delete'
  })
}
