import request from '@/utils/request'

// 查询机构列表
export function listSysOrg(query) {
  return request({
    url: '/system/org/list',
    method: 'get',
    params: query
  })
}

// 查询机构列表（排除节点）
export function listSysOrgExcludeChild(orgId) {
  return request({
    url: '/system/org/list/exclude/' + orgId,
    method: 'get'
  })
}

// 查询机构详细
export function getSysOrg(orgId) {
  return request({
    url: '/system/org/' + orgId,
    method: 'get'
  })
}

// 新增机构
export function addSysOrg(data) {
  return request({
    url: '/system/org',
    method: 'post',
    data: data
  })
}

// 修改机构
export function updateSysOrg(data) {
  return request({
    url: '/system/org',
    method: 'put',
    data: data
  })
}

// 删除机构
export function delSysOrg(orgId) {
  return request({
    url: '/system/org/' + orgId,
    method: 'delete'
  })
}
