import request from '@/utils/request'

//添加待办分类
export function addTodoCategory(data) {
  return request({
    url: '/warning/todo/category/add',
    method: 'post',
    data: data
  })
}

//查询所有待办分类
export function list() {
  return request({
    url: '/warning/todo/category/list',
    method: 'get',
  })
}


//删除待办分类
export function removeTodoCategory(id) {
  return request({
    url: `/warning/todo/category/remove/${id}`,
    method: 'delete',
  })
}

//修改待办分类
export function editTodoCategory(data) {
  return request({
    url: `/warning/todo/category/edit`,
    method: 'put',
    data: data
  })
}

