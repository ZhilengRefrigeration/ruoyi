import axios from 'axios'
import {ElNotification, ElMessageBox, ElMessage, ElLoading} from 'element-plus'
import {getToken} from '@/utils/auth'
import errorCode from '@/utils/errorCode'
import {tansParams, blobValidate} from '@/utils/ruoyi'
import cache from '@/plugins/cache'
import {saveAs} from 'file-saver'
import useUserStore from '@/store/modules/user'

let downloadLoadingInstance;
// 是否显示重新登录
export let isRelogin = {show: false};

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: import.meta.env.VITE_APP_BASE_API,
    // 超时
    timeout: 60000
})

/**
 * 请求拦截器
 */
service.interceptors.request.use(config => {
    // 是否需要设置 token
    const isToken = (config.headers || {}).isToken === false
    // 是否需要防止数据重复提交
    const isRepeatSubmit = (config.headers || {}).repeatSubmit === false
    if (getToken() && !isToken) {
        config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    // get请求映射params参数
    if (config.method === 'get' && config.params) {
        let url = config.url + '?' + tansParams(config.params);
        url = url.slice(0, -1);
        config.params = {};
        config.url = url;
    }
    if (!isRepeatSubmit && (config.method === 'post' || config.method === 'put')) {
        const requestObj = {
            url: config.url,
            data: typeof config.data === 'object' ? JSON.stringify(config.data) : config.data,
            time: new Date().getTime()
        }
        const requestSize = Object.keys(JSON.stringify(requestObj)).length; // 请求数据大小
        const limitSize = 5 * 1024 * 1024; // 限制存放数据5M
        if (requestSize >= limitSize) {
            console.warn(`[${config.url}]: ` + '请求数据大小超出允许的5M限制，无法进行防重复提交验证。')
            return config;
        }
        const sessionObj = cache.session.getJSON('sessionObj')
        if (sessionObj === undefined || sessionObj === null || sessionObj === '') {
            cache.session.setJSON('sessionObj', requestObj)
        } else {
            const s_url = sessionObj.url;                // 请求地址
            const s_data = sessionObj.data;              // 请求数据
            const s_time = sessionObj.time;              // 请求时间
            const interval = 1000;                       // 间隔时间(ms)，小于此时间视为重复提交
            if (s_data === requestObj.data && requestObj.time - s_time < interval && s_url === requestObj.url) {
                const message = '数据正在处理，请勿重复提交';
                console.warn(`[${s_url}]: ` + message)
                return Promise.reject(new Error(message))
            } else {
                cache.session.setJSON('sessionObj', requestObj)
            }
        }
    }
    return config
}, error => {
    console.log(error)
    Promise.reject(error)
})

/**
 * 响应拦截器
 */
service.interceptors.response.use(res => {
        // 未设置状态码则默认成功状态
        const code = res.data.code || 200;
        // 获取错误信息
        const msg = errorCode[code] || res.data.msg || errorCode['default']
        // 二进制数据则直接返回
        if (res.request.responseType === 'blob' || res.request.responseType === 'arraybuffer') {
            return res.data
        }
        if (code === 401) {
            if (!isRelogin.show) {
                isRelogin.show = true;
                ElMessageBox.confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', {
                    confirmButtonText: '重新登录',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    isRelogin.show = false;
                    useUserStore().logOut().then(() => {
                        location.href = '/index';
                    })
                }).catch(() => {
                    isRelogin.show = false;
                });
            }
            return Promise.reject('无效的会话，或者会话已过期，请重新登录。')
        } else if (code === 500) {
            ElMessage({message: msg, type: 'error'})
            return Promise.reject(new Error(msg))
        } else if (code === 601) {
            ElMessage({message: msg, type: 'warning'})
            return Promise.reject(new Error('[WARNING]' + msg))
        } else if (code !== 200) {
            ElNotification.error({title: msg})
            return Promise.reject('error')
        } else {
            return Promise.resolve(res.data)
        }
    },
    error => {
        console.log('err' + error)
        let {message} = error;
        if (message == "Network Error") {
            message = "后端接口连接异常";
        } else if (message.includes("timeout")) {
            message = "系统接口请求超时";
        } else if (message.includes("Request failed with status code")) {
            message = "系统接口" + message.substr(message.length - 3) + "异常";
        }
        ElMessage({message: message, type: 'error', duration: 5 * 1000})
        return Promise.reject(error)
    }
)

/**
 * 通用下载方法
 *
 * @param url   请求地址
 * @param params   请求参数
 * @param filename 下载文件名
 * @param type  请求类型，可选值：form、json，默认为form
 * @param config  axios配置
 * @returns {Promise<void>}
 */
export function download(url, params, filename, type = 'form', config = {}) {
    let headers, finalSubmitData
    if (type === 'json') {
        //处理json格式
        headers = {'Content-Type': 'application/json'}
        finalSubmitData = JSON.stringify(params)
    } else {
        //处理form格式
        headers = {'Content-Type': 'application/x-www-form-urlencoded'}
        finalSubmitData = params
        if (!config) {
            config = {}
        }
        config.transformRequest = [(params) => {
            return tansParams(params)
        }]
    }
    downloadLoadingInstance = ElLoading.service({text: "正在下载数据，请稍候", background: "rgba(0, 0, 0, 0.7)",})
    return service.post(url, {
        headers: headers,
        data: finalSubmitData,
        responseType: 'blob',
        ...config
    }).then(async (data) => {
        const isBlob = blobValidate(data);
        if (isBlob) {
            const blob = new Blob([data])
            saveAs(blob, filename)
        } else {
            const resText = await data.text();
            const rspObj = JSON.parse(resText);
            const errMsg = errorCode[rspObj.code] || rspObj.msg || errorCode['default']
            ElMessage.error(errMsg);
        }
    }).catch((error) => {
        console.error(error)
        if (!error.includes('[WARNING]')) {
            ElMessage.error('下载文件出现错误，请联系管理员！')
        }
    }).finally(() => {
        downloadLoadingInstance.close()
    })
}

/**
 * 通用上传方法
 *
 * @param url 上传地址
 * @param files 要上传的文件数组，如果数组中的元素可以直接是File对象，此时提交的字段名默认为"files"。否则数组中的元素应该是一个键值对的对象，其中"key"是字段名，"value"是File对象。
 * @param otherSubmitData 其他要提交的数据（一个键值对的对象）
 * @param config axios配置
 * @returns {Promise<void>}
 */
export function upload(url, files, otherSubmitData, config = {}) {
    //组装表单数据
    const formData = new FormData()
    for (let i = 0; i < files.length; i++) {
        const fileObj = files[i];
        if (fileObj instanceof File) {
            formData.append('files', fileObj);
        } else {
            formData.append(fileObj.key, fileObj.value);
        }
    }
    if (otherSubmitData) {
        const keys = Object.keys(otherSubmitData);
        for (let i = 0; i < keys.length; i++) {
            const key = keys[i]
            //只提交非空数据
            if (otherSubmitData[key]) {
                formData.append(key, otherSubmitData[key])
            }
        }
    }
    //上传
    const loadingInstance = ElLoading.service({text: "正在上传数据，请稍候", background: "rgba(0, 0, 0, 0.7)",})
    return service.request({
        url: url,
        method: 'post',
        headers: {'Content-Type': 'multipart/form-data'},
        data: formData,
        ...config
    }).catch((r) => {
        console.error('Upload file error:', r)
        return Promise.reject(r)
    }).finally(() => {
        loadingInstance.close()
    })
}

export default service
