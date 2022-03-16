import request from '@/utils/request'

// 获取品牌分组分页数据
export function getAttrGroupList(data) {
  return request({
    url: '/mall-product/product/attrgroup/list/'+data.catelogId,
    method: 'get',
    params:data
  })
}

//获取具体品牌分组数据
export function getAttrGroup(data) {
  return request({
    url: `/mall-product/product/attrgroup/info/${data}`,
    method: 'get',
  })
}

// 保存品牌分组数据
export function addAttrGroup(data) {
  return request({
    url: `/mall-product/product/attrgroup/save`,
    method: 'post',
    data:data
  })
}

// 修改品牌分组数据
export function editAttrGroup(data) {
  return request({
    url: `/mall-product/product/attrgroup/update`,
    method: 'put',
    data:data
  })
}

// 删除品牌分组数据
export function delAttrGroup(ids) {
  return request({
    url: '/mall-product/product/attrgroup/delete',
    method: 'delete',
    data:ids
  })
}
