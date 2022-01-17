import request from '@/utils/request'

//获取实时天气
export function getNowWeather() {
  return request({
    url: '/openapi/weather/now',
    method: 'get'
  })
}


//获取预报天气
export function getForecastWeather() {
  return request({
    url: '/openapi/weather/forecast',
    method: 'get'
  })
}
