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


//获取身份证信息
export function getIdCardQuery(idCard) {
  return request({
    url: '/openapi/apitools/idcardquery/'+idCard,
    method: 'get',
  })
}


//获取手机归属地信息
export function getMobileBelong(mobile) {
  return request({
    url: '/openapi/apitools/mobilebelong/'+mobile,
    method: 'get',
  })
}


//获取实时天气信息
export function getNowWeather(city) {
  return request({
    url: '/openapi/apitools/nowweather/'+city,
    method: 'get',
  })
}


//获取预报天气信息
export function getForecastWeather(city) {
  return request({
    url: '/openapi/apitools/forecastweather/'+city,
    method: 'get',
  })
}

//获取垃圾分类信息
export function getGarbageSorting(name) {
  return request({
    url: '/openapi/apitools/garbagesorting/'+name,
    method: 'get',
  })
}

//获取简繁转换信息
export function getSimpleComplex(content) {
  return request({
    url: '/openapi/apitools/simplecomplex/'+content,
    method: 'get',
  })
}

//获取汉语字典信息
export function getChineseDict(content) {
  return request({
    url: '/openapi/apitools/chinesedict/'+content,
    method: 'get',
  })
}

//获取ip信息
export function getIpInfo(ip) {
  return request({
    url: '/openapi/apitools/ipinfo/'+ip,
    method: 'get',
  })
}







