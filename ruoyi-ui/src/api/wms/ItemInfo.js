import request, { upload } from '@/utils/request'

// 查询物品基础信息列表
export function listItemInfo(query) {
  return request({
    url: '/wms/ItemInfo/list',
    method: 'get',
    params: query
  })
}

// 查询物品基础信息详细
export function getItemInfo(itemCd) {
  return request({
    url: '/wms/ItemInfo/' + itemCd,
    method: 'get'
  })
}

// 新增物品基础信息
export function addItemInfo(data, withImage = false, imageFiles = []) {
  if (withImage) {
    //连着图片文件一起提交
    const url = '/wms/ItemInfo/addWithImage'
    return upload(url, imageFiles, data)
  } else {
    //只提交json数据
    return request({
      url: '/wms/ItemInfo',
      method: 'post',
      data: data
    })
  }
}

// 修改物品基础信息
export function updateItemInfo(data, withImage = false, imageFiles = []) {
  if (withImage) {
    //连着图片文件一起提交
    const url = '/wms/ItemInfo/editWithImage'
    return upload(url, imageFiles, data, { method: 'put' })
  } else {
    //只提交json数据
    return request({
      url: '/wms/ItemInfo',
      method: 'put',
      data: data
    })
  }
}

// 删除物品基础信息
export function delItemInfo(itemCd) {
  return request({
    url: '/wms/ItemInfo/' + itemCd,
    method: 'delete'
  })
}
