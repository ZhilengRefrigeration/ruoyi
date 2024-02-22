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

// 新增文件存储记录
export function addFileRecord(data) {
  return request({
    url: '/file/FileRecord',
    method: 'post',
    data: data
  })
}

// 修改文件存储记录
export function updateFileRecord(data) {
  return request({
    url: '/file/FileRecord',
    method: 'put',
    data: data
  })
}

// 删除文件存储记录
export function delFileRecord(fileId) {
  return request({
    url: '/file/FileRecord/' + fileId,
    method: 'delete'
  })
}
