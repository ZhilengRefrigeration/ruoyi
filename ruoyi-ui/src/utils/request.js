import axios from 'axios'
import { Notification, MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken, getRefreshToken, getExpiresIn,removeToken} from '@/utils/auth'
import errorCode from '@/utils/errorCode'
import { tansParams } from "@/utils/ruoyi";
var refreshCount = 0
// token将在这个时间以后过期 毫秒
// const EXPIRED_IN_THIS_SECONDS = 6000
const EXPIRED_IN_THIS_SECONDS = 100000
const CODE_PATH = `/code`
window.isRefreshing = false

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: process.env.VUE_APP_BASE_API,
  // 超时
  timeout: 10000
})

/**
 * 增加令牌刷新功能
 * qq：8416837
 * author：学生宫布
 */
// request拦截器
service.interceptors.request.use(config => {

  // 令牌维护 - start
  // 获取当前时间戳，与过期时间比对，如果即将过期或已经过期，则调/auth/oauth/token API刷新token
  // isRefreshing 检测是否正在刷新，如果正在刷新，则阻塞，直到获取新token
  const beLogining = config.url === CODE_PATH // 正在登录
  // console.log(`【请求拦截器执行中】`)
  const futureTime = getExpiresIn()
  // console.log(`令牌到期时间(long)`, futureTime)
  // console.log(`令牌到期时间 === `, new Date(parseInt(futureTime)))
  const itsTimeToRrefresh = futureTime != -1 && !window.isRefreshing && ((futureTime - new Date().getTime() ) <= EXPIRED_IN_THIS_SECONDS)
  if (itsTimeToRrefresh) { // 如果expires_in_time eq 0，则很可能是初次登陆，从而勿须刷新令牌 假如设置还差6秒过期
    // 锁 避免多个调用重复刷新
    window.isRefreshing = true

    // console.log(`当前时间 === `, new Date());

    // console.log(`令牌`, (futureTime - new Date().getTime())/1000, `秒后过期，因此现在刷新令牌`);

    // 刷新令牌 将新令牌更新到存储或本地
    return refresh(config);

  }
  // 令牌维护 - end

  else {
    // console.log(`令牌正常或者还未登录【或者正在刷新】，因此暂不刷新它，请求url === `, config.url);

    if(beLogining) { // 如果正在登录，那就清空token相关的cookie
      // console.log(`准备登录，请求url === `, config.url)
      removeToken()
      return config
    }else { // 如果在调业务接口，则授权
      return auth(config); // 给请求添加token
    }
  }

}, error => {
  // console.log(error)
  Promise.reject(error)
})

/**
 * 同步刷新令牌并更新到ajax配置
 * @param config
 * @returns {Promise<*>}
 */
async function refresh(config) {
  const refreshTokenParams = {}
  const refreshToken = getRefreshToken()
  refreshTokenParams.refreshToken = refreshToken
  // 调API刷新令牌
  await store.dispatch("RefreshToken", refreshTokenParams).then(() => {

    auth(config)

  })
    .catch((e) => {
      // console.log(`刷新失败`, e, `,可能refresh_token已过期~`)
    });

  refreshCount ++

  // console.log(`刷新页面之前，当前第几次刷新token === `, refreshCount)

  window.isRefreshing = false
  return config
}

/**
 * 给请求授权
 * @param config
 * @returns {{headers}}
 */
function auth(config) {
  const isToken = (config.headers || {}).isToken === false
  if (getToken() && !isToken) {

    // console.log(`访问`, config.url, `之前，给请求头附加token`)

    config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
  }

  return config
}

// 响应拦截器
service.interceptors.response.use(res => {
    const code = res.data.code || 200 || 0;
    const message = errorCode[code] || res.data.errMsg || res.data.msg || errorCode['default']
    if (code === 401) {
      MessageBox.confirm(
        '登录状态已过期，您可以继续留在该页面，或者重新登录',
        '系统提示',
        {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        store.dispatch('LogOut').then(() => {
          location.reload() // 为了重新实例化vue-router对象 避免bug
        })
      })
    } else if (code === 500) {
      Message({
        message: message,
        type: 'error'
      })
      return Promise.reject(new Error(message))
    } else if (code !== 200) {
      Notification.error({
        title: message
      })
      return Promise.reject('error')
    } else {
      return res.data
    }
  },
  error => {
    // console.log('err' + error)
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

// 通用下载方法
export function download(url, params, filename) {
  return service.post(url, params, {
    transformRequest: [(params) => {
      return tansParams(params)
    }],
    responseType: 'blob'
  }).then((data) => {
    const content = data
    const blob = new Blob([content])
    if ('download' in document.createElement('a')) {
      const elink = document.createElement('a')
      elink.download = filename
      elink.style.display = 'none'
      elink.href = URL.createObjectURL(blob)
      document.body.appendChild(elink)
      elink.click()
      URL.revokeObjectURL(elink.href)
      document.body.removeChild(elink)
    } else {
      navigator.msSaveBlob(blob, filename)
    }
  }).catch((r) => {
    console.error(r)
  })
}

export default service
