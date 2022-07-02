import request from '@/utils/request'

const api_name = '/classroom-service-vod/admin/vod/subject'

export default {
    //课程分类列表
    getChildList(id) {
        return request({
        url: `${api_name}/getChildSubject/${id}`,
        method: 'get'
        })
    },

  addSubject(pid, subjectName) {
      return request({
        url: `${api_name}/addSubject/${pid}`,
        method: 'post',
        params:subjectName
      })
  },

  delSubject(id) {
    return request({
      url: `${api_name}/delSubject/${id}`,
      method: 'delete',
    })
  }
}
