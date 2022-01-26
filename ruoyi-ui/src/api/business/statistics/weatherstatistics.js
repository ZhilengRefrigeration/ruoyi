import request from '@/utils/request'

//查询天气历史记录统计
export function getHistoryWeather(params) {
  return request({
    url: '/statistics/weatherstatistics/history',
    method: 'get',
    params: params,
  })
}
