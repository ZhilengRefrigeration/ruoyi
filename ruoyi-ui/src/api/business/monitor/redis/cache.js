import request from '@/utils/request'

// 查询redis缓存详细
export function getRedisCache() {
  return request({
    url: '/monitor/redismonitor',
    method: 'get'
  })
}
