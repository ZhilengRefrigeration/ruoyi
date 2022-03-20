import request from '@/utils/request'

// 获取品牌分页数据
export function getBrandList(data) {
  return request({
    url: '/mall-product/product/brand/list',
    method: 'get',
    params:data
  })
}

// 新增品牌信息
export function addBrand(data) {
  return request({
    url: '/mall-product/product/brand/save',
    method: 'post',
    data:data
  })
}

// 修改品牌信息
export function editBrand(data) {
  return request({
    url: '/mall-product/product/brand/update',
    method: 'put',
    data:data
  })
}

// 获取品牌信息
export function getBrand(id) {
  return request({
    url: '/mall-product/product/brand/info/'+id,
    method: 'get',
  })
}

//删除品牌信息
export function delBrand(ids) {
  return request({
    url: '/mall-product/product/brand/delete',
    method: 'delete',
    data:ids
  })
}

// 根据分类id找到品牌
export function catelogList(catId) {
  return request({
    url: '/mall-product/product/categorybrandrelation/brands/list',
    method: 'get',
    params:catId
  })
}



