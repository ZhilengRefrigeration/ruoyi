import request from '@/utils/request'

// 查询序列号生成规则列表
export function listSeqRule(query) {
  return request({
    url: '/system/SeqRule/list',
    method: 'get',
    params: query
  })
}

// 查询序列号生成规则详细
export function getSeqRule(ruleId) {
  return request({
    url: '/system/SeqRule/' + ruleId,
    method: 'get'
  })
}

// 新增序列号生成规则
export function addSeqRule(data) {
  return request({
    url: '/system/SeqRule',
    method: 'post',
    data: data
  })
}

// 修改序列号生成规则
export function updateSeqRule(data) {
  return request({
    url: '/system/SeqRule',
    method: 'put',
    data: data
  })
}

// 删除序列号生成规则
export function delSeqRule(ruleId) {
  return request({
    url: '/system/SeqRule/' + ruleId,
    method: 'delete'
  })
}

// 规则启用状态修改
export function changeRuleEnableFlag(ruleId, enableFlag) {
  const data = {
    ruleId,
    enableFlag,
  }
  return request({
    url: '/system/SeqRule/changeRuleEnableFlag',
    method: 'put',
    data: data
  })
}
