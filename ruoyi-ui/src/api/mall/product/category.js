import request from '@/utils/request'

// 获取菜单
export function getMenus() {
  return request({
    url: '/mall-product/product/category/list/tree',
    method: 'get',
  })
}


//删除分类
export function removeMenus(ids) {
  return request({
    url: '/mall-product/product/category/delete',
    method: 'delete',
    data:ids
  })
}

//添加三级分类
export function addCategory(data) {
  return request({
    url: '/mall-product/product/category/save',
    method: 'post',
    data:data
  })
}

//修改三级分类
export function editCategory(data) {
  return request({
    url: '/mall-product/product/category/update',
    method: 'put',
    data:data
  })
}

//获取具体一个三级分类
export function getCategory(data) {
  return request({
    url: `/mall-product/product/category/info/${data.catId}`,
    method: 'get',
  })
}

//批量修改
export function batchSave(data) {
  return request({
    url: '/mall-product/product/category/update/sort',
    method: 'put',
    data:data
  })
}




