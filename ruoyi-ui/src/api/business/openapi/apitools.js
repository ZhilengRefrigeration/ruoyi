import request from '@/utils/request'

//获取节假日信息
export function getHoliday() {
  return request({
    url: '/openapi/apitools/holiday',
    method: 'get',
  })
}


//获取mm图片信息
export function getBeautyPicture() {
  return request({
    url: '/openapi/apitools/beautypicture',
    method: 'get',
  })
}


//获取历史今天信息
export function getHistoryToday() {
  return request({
    url: '/openapi/apitools/historytoday',
    method: 'get',
  })
}
