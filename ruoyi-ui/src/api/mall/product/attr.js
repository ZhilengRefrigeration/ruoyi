import request from '@/utils/request'

// 保存规格参数
export function addAttr(data) {
  return request({
    url: `/mall-product/product/attr/save`,
    method: 'post',
    data:data
  })
}

// 修改规格参数
export function editAttr(data) {
  return request({
    url: `/mall-product/product/attr/update`,
    method: 'put',
    data:data
  })
}

// 分页获取规格参数
export function getBaseAttrList(parms,catelogId) {
  return request({
    url: `/mall-product/product/attr/base/list/${catelogId}`,
    method: 'get',
    params:parms
  })
}

// 删除规格参数
export function delAttr(ids) {
  return request({
    url: `/mall-product/product/attr/delete`,
    method: 'delete',
    data:ids
  })
}

// 获取规格参数info信息
export function getAttr(attrId) {
  return request({
    url: `/mall-product/product/attr/info/${attrId}`,
    method: 'get',
  })
}
