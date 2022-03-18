import request from '@/utils/request'

// 获取会员等级列表
export function getMemberLevelList(params) {
  return request({
    url: `/mall-member/member/memberlevel/list`,
    method: 'get',
    params:params
  })
}

// 删除会员等级列表
export function delMemberLevel(ids) {
  return request({
    url: `/mall-member/member/memberlevel/delete`,
    method: 'delete',
    data:ids
  })
}

// 过去会员等级具体信息
export function getMemberLevel(id) {
  return request({
    url: `/mall-member/member/memberlevel/info/${id}`,
    method: 'get',
  })
}

// 保存会员等级信息
export function saveMemberLevel(data) {
  return request({
    url: `/mall-member/member/memberlevel/save`,
    method: 'post',
    data:data
  })
}

// 修改会员等级信息
export function editMemberLevel(data) {
  return request({
    url: `/mall-member/member/memberlevel/update`,
    method: 'put',
    data:data
  })
}



