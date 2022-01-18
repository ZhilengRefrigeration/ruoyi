import request from '@/utils/request'

// 查询性能监控信息
export function getPerformance() {
  return request({
    url: '/monitor/performancemonitor',
    method: 'get'
  })
}
