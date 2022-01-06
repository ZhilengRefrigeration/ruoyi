import request from '@/utils/request'

// 登录方法
export function getServiceMonitor() {
  return request({
    url: '/monitor/servicemonitor'
  })
}
