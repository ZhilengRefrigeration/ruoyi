import request from '@/utils/request'

// 获取分页列表
export function getSkuList(data) {
  return request({
    url: '/mall-product/product/skuinfo/list',
    method: 'get',
    params: data
  })
}
