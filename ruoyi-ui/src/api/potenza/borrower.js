import request from '@/utils/request'

// 查询贷款列表
export function listBorrower(query) {
  return request({
    url: '/potenza/borrower/list',
    method: 'get',
    params: query
  })
}

// 查询贷款详细
export function getBorrower(borrowerId) {
  return request({
    url: '/potenza/borrower/borrowerById/' + borrowerId,
    method: 'get'
  })
}

// 新增贷款
export function addBorrower(data) {
  return request({
    url: '/potenza/borrower/borrowerInsert',
    method: 'post',
    data: data
  })
}

// 修改贷款
export function updateBorrower(data) {
  return request({
    url: '/potenza/borrower/borrowerUpdate',
    method: 'put',
    data: data
  })
}



