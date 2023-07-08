import request from '@/utils/request'

// 查询球馆特征列表
export function listFeatureLabel(query) {
  return request({
    url: '/system/featureLabel/list',
    method: 'get',
    params: query
  })
}

// 查询球馆特征详细
export function getFeatureLabel(id) {
  return request({
    url: '/system/featureLabel/' + id,
    method: 'get'
  })
}

// 新增球馆特征
export function addFeatureLabel(data) {
  return request({
    url: '/system/featureLabel',
    method: 'post',
    data: data
  })
}

// 修改球馆特征
export function updateFeatureLabel(data) {
  return request({
    url: '/system/featureLabel',
    method: 'put',
    data: data
  })
}

// 删除球馆特征
export function delFeatureLabel(id) {
  return request({
    url: '/system/featureLabel/' + id,
    method: 'delete'
  })
}
