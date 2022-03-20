import request from '@/utils/request'

// 保存spu关联的所有信息
export function saveSpuInfo(data) {
  return request({
    url: '/mall-product/product/spuinfo/save',
    method: 'post',
    data: data
  })
}

// 获取spu列表分页数据
export function getSpuList(data) {
  return request({
    url: '/mall-product/product/spuinfo/list',
    method: 'get',
    params: data
  })
}

