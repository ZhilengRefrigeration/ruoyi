import request from '@/utils/request'

//获取仓库信息列表
export function getWareInfoList(parms) {
  return request({
    url: '/mall-ware/ware/wareinfo/list',
    method: 'get',
    params: parms
  })
}

//删除仓库信息
export function delWareInfo(ids) {
  return request({
    url: '/mall-ware/ware/wareinfo/delete',
    method: 'delete',
    data: ids
  })
}

//获取仓库信息详情
export function getWareInfo(id) {
  return request({
    url: `/mall-ware/ware/wareinfo/info/${id}`,
    method: 'get',
  })
}

//保存仓库信息
export function saveWareInfo(data) {
  return request({
    url: `/mall-ware/ware/wareinfo/save`,
    method: 'post',
    data: data,
  })
}

//修改仓库信息
export function editWareInfo(data) {
  return request({
    url: `/mall-ware/ware/wareinfo/update`,
    method: 'put',
    data: data,
  })
}

// 获取所有省级区域
export function getProvinceArea(data) {
  return request({
    url: `/mall-ware/ware/wareinfo/getProvinceArea`,
    method: 'get',
  })
}

// 根据父ID获取区域
export function getAreaByParentId(pid) {
  return request({
    url: `/mall-ware/ware/wareinfo/getAreaByParentId/${pid}`,
    method: 'get',
  })
}


