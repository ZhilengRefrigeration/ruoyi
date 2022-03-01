import request from '@/utils/request'

// 获取积分列表
export function getList() {
  return request({
    url: '/srb_core//admin/core/integralGrade/list',
    method: 'get',
  })
}

// 删除积分列表
export function removeById(id) {
  return request({
    url: '/srb_core//admin/core/integralGrade/remove/'+id,
    method: 'delete',
  })
}

// 新增积分列表
export function save(integral) {
  return request({
    url: '/srb_core//admin/core/integralGrade/save',
    method: 'post',
    data:integral
  })
}


// 修改积分列表
export function update(integral) {
  return request({
    url: '/srb_core//admin/core/integralGrade/update',
    method: 'put',
    data:integral
  })
}

// 获取积分列表
export function getById(id) {
  return request({
    url: '/srb_core//admin/core/integralGrade/get/'+id,
    method: 'get',
  })
}
