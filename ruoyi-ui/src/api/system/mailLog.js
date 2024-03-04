import request, { upload } from '@/utils/request'

// 查询邮件发送日志列表
export function listMailLog(query) {
    return request({
        url: '/system/mailLog/list',
        method: 'get',
        params: query
    })
}

// 查询邮件发送日志详细
export function getMailLog(mailLogId) {
    return request({
        url: '/system/mailLog/' + mailLogId,
        method: 'get'
    })
}

// 删除邮件发送日志
export function delMailLog(mailLogId) {
    return request({
        url: '/system/mailLog/' + mailLogId,
        method: 'delete'
    })
}

export function getMailSenderInfo() {
    return request({
        url: '/system/mailLog/getMailSenderInfo',
        method: 'get'
    })
}

// 临时邮件发送
export function sendTemporality(data, files) {
    return upload('/system/mailLog/sendTemporality', files, data)
}
