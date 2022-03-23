import request from '@/utils/request'

//获取仓库库存列表
export function getWareSkuList(parms) {
  return request({
    url: '/mall-ware/ware/waresku/list',
    method: 'get',
    params: parms
  })
}

//删除仓库库存
export function delWareSku(ids) {
  return request({
    url: '/mall-ware/ware/waresku/delete',
    method: 'delete',
    data: ids
  })
}

//获取仓库库存详情
export function getWareSku(id) {
  return request({
    url: `/mall-ware/ware/waresku/info/${id}`,
    method: 'get',
  })
}

//保存仓库库存
export function saveWareSku(data) {
  return request({
    url: `/mall-ware/ware/waresku/save`,
    method: 'post',
    data: data,
  })
}

//修改仓库库存
export function editWareSku(data) {
  return request({
    url: `/mall-ware/ware/waresku/update`,
    method: 'put',
    data: data,
  })
}
