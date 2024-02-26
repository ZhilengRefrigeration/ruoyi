import request from '@/utils/request'

// 查询入出库履历列表
export function listInvTransHis(query) {
  return request({
    url: '/wms/InvTransHis/list',
    method: 'get',
    params: query
  })
}

// 查询入出库履历详细
export function getInvTransHis(invTransNo) {
  return request({
    url: '/wms/InvTransHis/' + invTransNo,
    method: 'get'
  })
}

// 删除入出库履历
export function delInvTransHis(invTransNo) {
  return request({
    url: '/wms/InvTransHis/' + invTransNo,
    method: 'delete'
  })
}
