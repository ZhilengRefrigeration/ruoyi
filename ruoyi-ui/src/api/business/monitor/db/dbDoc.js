import request from '@/utils/request'

export function exportHtml(key) {
  return request({
    url: '/monitor/db-doc/export-html',
    method: 'get',
    responseType: 'blob',
    params:key
  })
}

export function exportWord(key) {
  return request({
    url: '/monitor/db-doc/export-word',
    method: 'get',
    responseType: 'blob',
    params:key
  })
}

export function exportMarkdown(key) {
  return request({
    url: '/monitor/db-doc/export-markdown',
    method: 'get',
    responseType: 'blob',
    params:key
  })
}

export function getDataSource() {
  return request({
    url: '/monitor/db-doc/getDataSource',
    method: 'get',
  })
}
