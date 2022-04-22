import request from '@/utils/request'

// 查询task列表
export function listTask(query) {
  return request({
    url: '/workflow/task/list',
    method: 'get',
    params: query
  })
}

// 查询历史task列表
export function listHistoryTask(query) {
  return request({
    url: '/workflow/task/historyList',
    method: 'get',
    params: query
  })
}

// 查询表单
export function formDataShow(taskID) {
  return request({
    url: '/workflow/task/formDataShow/'+taskID,
    method: 'get',
  })
}

// 查询表单
export function formDataSave(taskID,data) {
  return request({
    url: '/workflow/task/formDataSave/'+taskID,
    method: 'post',
    data:data
  })
}




