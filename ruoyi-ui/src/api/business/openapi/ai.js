import request from '@/utils/request'

//获取联想词汇
export function getAssociation(content) {
  return request({
    url: '/openapi/association/getAssociation',
    method: 'get',
    params: content
  })
}
