import request from '@/utils/request'

//发送邮件
export function sendMail(data) {
  return request({
    url: '/warning/mail/send-mail',
    method: 'post',
    data: data
  })
}
