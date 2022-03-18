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
export function getBaseAttrList(parms,catelogId,attrType) {
  return request({
    url: `/mall-product/product/attr/${attrType}/list/${catelogId}`,
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

// 删除属性及分组关联
export function deleteRelation(ids) {
  return request({
    url: `/mall-product/product/attr/relation/delete`,
    method: 'delete',
    data:ids
  })
}

