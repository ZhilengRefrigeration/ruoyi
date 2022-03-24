import request from '@/utils/request'

//获取仓库采购单列表
export function getWarePurchaseList(parms) {
  return request({
    url: '/mall-ware/ware/purchase/list',
    method: 'get',
    params: parms
  })
}

//删除仓库采购单
export function delWarePurchase(ids) {
  return request({
    url: '/mall-ware/ware/purchase/delete',
    method: 'delete',
    data: ids
  })
}

//获取仓库采购单详情
export function getWarePurchase(id) {
  return request({
    url: `/mall-ware/ware/purchase/info/${id}`,
    method: 'get',
  })
}

//保存仓库采购单
export function saveWarePurchase(data) {
  return request({
    url: `/mall-ware/ware/purchase/save`,
    method: 'post',
    data: data,
  })
}

//修改仓库采购单
export function editWarePurchase(data) {
  return request({
    url: `/mall-ware/ware/purchase/update`,
    method: 'put',
    data: data,
  })
}

//未领取采购单列表
export function unreceiveList(parms) {
  return request({
    url: '/mall-ware/ware/purchase/unreceive/list',
    method: 'get',
    params: parms
  })
}

//合并采购单
export function mergePurchase(data) {
  return request({
    url: '/mall-ware/ware/purchase/merge',
    method: 'post',
    data: data
  })
}
