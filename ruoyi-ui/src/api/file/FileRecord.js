import request from '@/utils/request'

// 查询文件存储记录列表
export function listFileRecord(query) {
  return request({
    url: '/file/FileRecord/list',
    method: 'get',
    params: query
  })
}

// 查询文件存储记录详细
export function getFileRecord(fileId) {
  return request({
    url: '/file/FileRecord/' + fileId,
    method: 'get'
  })
}
