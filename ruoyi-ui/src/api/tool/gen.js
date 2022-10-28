import request from '@/utils/request'

// 查询生成表数据
export function listTable(query) {
  return request({
    url: '/code/gen/list',
    method: 'get',
    params: query
  })
}

export function listSchema() {
  return request({
    url: '/code/gen/schema/list',
    method: 'get',
  })
}

// 查询db数据库列表
export function listDbTable(query) {
  return request({
    url: '/code/gen/db/list',
    method: 'get',
    params: query
  })
}

// 查询表详细信息
export function getGenTable(tableId) {
  return request({
    url: '/code/gen/' + tableId,
    method: 'get'
  })
}

// 修改代码生成信息
export function updateGenTable(data) {
  return request({
    url: '/code/gen',
    method: 'put',
    data: data
  })
}

// 导入表
export function importTable(data, schemaName) {
  return request({
    url: `/code/gen/importTable/${schemaName}`,
    method: 'post',
    params: data
  })
}

// 预览生成代码
export function previewTable(tableId) {
  return request({
    url: '/code/gen/preview/' + tableId,
    method: 'get'
  })
}

// 删除表数据
export function delTable(tableId) {
  return request({
    url: '/code/gen/' + tableId,
    method: 'delete'
  })
}

// 生成代码（自定义路径）
export function genCode(tableName) {
  return request({
    url: '/code/gen/genCode/' + tableName,
    method: 'get'
  })
}

// 同步数据库
export function synchDb(schemaName, tableName) {
  return request({
    url: `/code/gen/synchDb/${schemaName}/${tableName}`,
    method: 'get'
  })
}
