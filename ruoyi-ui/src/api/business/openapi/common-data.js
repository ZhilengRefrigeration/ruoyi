import request from '@/utils/request'

// 更新所有区域编码
export function rest() {
  return request({
    url: '/openapi/area/rest',
    method: 'get',
  })
}

//获取所有省级区域
export function getProvinceArea() {
  return request({
    url: '/openapi/area/getProvinceArea',
    method: 'get',
  })
}

//根据父ID获取区域
export function getAreaByParentId(id) {
  return request({
    url: '/openapi/area/getAreaByParentId/'+id,
    method: 'get',
  })
}
