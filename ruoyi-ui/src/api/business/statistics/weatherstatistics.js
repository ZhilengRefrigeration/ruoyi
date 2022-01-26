import request from '@/utils/request'

//查询天气历史记录统计
export function getHistoryWeather(params) {
  return request({
    url: '/statistics/weatherstatistics/history',
    method: 'get',
    params: params,
  })
}

//查询未来天气统计
export function getFutureWeather() {
  return request({
    url: '/statistics/weatherstatistics/future',
    method: 'get',
  })
}
