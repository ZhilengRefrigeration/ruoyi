import request from '@/utils/request'

// 获取品牌分组分页数据
export function getAttrGroupList(data) {
  return request({
    url: '/mall-product/product/attrgroup/list/' + data.catelogId,
    method: 'get',
    params: data
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
    data: data
  })
}

// 修改品牌分组数据
export function editAttrGroup(data) {
  return request({
    url: `/mall-product/product/attrgroup/update`,
    method: 'put',
    data: data
  })
}

// 删除品牌分组数据
export function delAttrGroup(ids) {
  return request({
    url: '/mall-product/product/attrgroup/delete',
    method: 'delete',
    data: ids
  })
}

// 获取属性分组关联规格参数
export function attrRelation(attrgroupId) {
  return request({
    url: `/mall-product/product/attrgroup/${attrgroupId}/attr/relation`,
    method: 'get',
  })
}

//获取当前分组没有关联的所有属性
export function attrNoRelation(attrgroupId,parms) {
  return request({
    url: `/mall-product/product/attrgroup/${attrgroupId}/noattr/relation`,
    method: 'get',
    parms:parms
  })
}

//批量保存属性和属性分组关联信息
export function addRelation(ids) {
  return request({
    url: `/mall-product/product/attrgroup/attr/relation`,
    method: 'post',
    data:ids
  })
}






