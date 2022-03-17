import request from '@/utils/request'


//添加品牌关联
export function addCategoryBrandRelation(data) {
  return request({
    url: '/mall-product/product/categorybrandrelation/save',
    method: 'post',
    data:data
  })
}


//删除品牌关联
export function delCategoryBrandRelation(ids) {
  return request({
    url: '/mall-product/product/categorybrandrelation/delete',
    method: 'delete',
    data:ids
  })
}


//获取品牌关联
export function categoryBrandRelationList(data) {
  return request({
    url: '/mall-product/product/categorybrandrelation/catelog/list',
    method: 'get',
    params:data
  })
}

