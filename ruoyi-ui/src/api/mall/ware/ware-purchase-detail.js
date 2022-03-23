import request from '@/utils/request'

//获取仓库采购需求列表
export function getWarePurchaseDetailList(parms) {
  return request({
    url: '/mall-ware/ware/purchasedetail/list',
    method: 'get',
    params: parms
  })
}

//删除仓库采购需求
export function delWarePurchaseDetail(ids) {
  return request({
    url: '/mall-ware/ware/purchasedetail/delete',
    method: 'delete',
    data: ids
  })
}

//获取仓库采购需求详情
export function getWarePurchaseDetail(id) {
  return request({
    url: `/mall-ware/ware/purchasedetail/info/${id}`,
    method: 'get',
  })
}

//保存仓库采购需求
export function saveWarePurchaseDetail(data) {
  return request({
    url: `/mall-ware/ware/purchasedetail/save`,
    method: 'post',
    data: data,
  })
}

//修改仓库采购需求
export function editWarePurchaseDetail(data) {
  return request({
    url: `/mall-ware/ware/purchasedetail/update`,
    method: 'put',
    data: data,
  })
}


