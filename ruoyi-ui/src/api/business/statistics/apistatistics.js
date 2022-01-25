import request from '@/utils/request'


//查询API历史记录统计
export function getStatisticsHistoryApi() {
  return request({
    url: '/statistics/apistatistics/history',
    method: 'get',
  })
}

//查询API当天记录统计
export function getStatisticsTodayApi() {
  return request({
    url: '/statistics/apistatistics/today',
    method: 'get',
  })
}
