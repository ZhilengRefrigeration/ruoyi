import axios from 'axios'
const global = {
  // 表单必输项
  requiredRule: {
    required: true,
    message: '该项为必输项！',
    trigger: ['blur', 'change'],
  },

  // 文件上传接口
  uploadURL: process.env.VUE_APP_BASE_API + 'sys/file/upload',

  //影像平台接口上传接口文件
  uploadToIcon: process.env.VUE_APP_BASE_API + '/sys/file/uploadToIcon',

  //文件下载
  downLoadUrl: process.env.VUE_APP_BASE_API + 'sys/file/download?fileId=',

  // 公告文件上传接口
  uploadToIconSf: process.env.VUE_APP_BASE_API + '/sys/file/upload',
  // 图片
  // getPicUrl:'/get/{value}/mg.do'
  getPicUrl: process.env.VUE_APP_BASE_API + '/sys/file/get/{value}/mg.do',

  // 码值转换
  getNameByCode: (codeInfo, codeValue, isMult = false) => {
    if (!codeInfo) {
      return codeValue
    }
    if (!codeValue) {
      return ''
    }
    if (isMult) codeValue = isMult ? codeValue.split(',') : codeValue
    let codeName = '',
      codeValues = []
    for (let i = 0; i < codeInfo.length; i++) {
      if (Array.isArray(codeValue)) {
        for (let j = 0; j < codeValue.length; j++) {
          if (codeValue[j] == codeInfo[i].value) {
            codeValues.push(codeInfo[i].content)
          }
        }
      } else {
        if (codeValue == codeInfo[i].value) {
          codeName = codeInfo[i].content
          break
        }
      }
      codeName = codeValues.join(',')
    }
    return codeName == '' ? codeValue : codeName
  },

  // 分转元
  regFenToYuan: val => {
    let num = Number(val)
    if (!isNaN(num)) {
      num = num * 0.01 + ''
      let reg = num.indexOf('.') > -1 ? /(\d{1,3})(?=(?:\d{3})+\.)/g : /(\d{1,3})(?=(?:\d{3})+$)/g //千分符的正则
      return num.replace(reg, '$1,')
    } else {
      return null
    }
  },
  // 判断字符串是否为空
  isEmptyStr(str) {
    if (str == '') return true
    const regu = '^[ ]+$'
    const re = new RegExp(regu)
    return re.test(str)
  },
  //文件下载/表格导出
  downloadFiles(_url, _params, _this, requestFn = 'download') {

    _this.$http[requestFn](_url, _params).then(response => {
      if (_url === _this.$api.cmApi.exporAllCstInfo) {
        console.log(response, 'response')
        // if (response.isOK) {
        //   _this.$message.successs(response.msg)
        // } else {
        //   _this.$message.error(response.msg)
        // }
      } else if (!response.data.code) {
        let contentDisposition = response.headers['content-disposition']
        const blob = new Blob([response.data])

        if ('download' in document.createElement('a')) {
          const elink = document.createElement('a')
          let fileName = decodeURI(contentDisposition.substring(contentDisposition.indexOf('filename=') + 9, contentDisposition.length));
          fileName = fileName.replace((/"/g), "");
          elink.download = fileName
          elink.style.display = 'none'
          elink.href = URL.createObjectURL(blob)
          document.body.appendChild(elink)
          elink.click()
          URL.revokeObjectURL(elink.href)
          document.body.removeChild(elink)
        } else {
          navigator.msSaveBlob(blob, fileName)
        }
      } else {
        _this.$message.error(response.msg)
      }
    })
  },

  /* 文件下载/表格导出
   * 支持影像平台下载
   */
  downloadImage(_url, _params, _this, requestFn = 'download') {
    _this.$http[requestFn](_url, _params).then(response => {
      if (!response.data.code) {
        let fileName = response.headers['content-disposition']
        const blob = new Blob([response.data])
        if ('download' in document.createElement('a')) {
          const elink = document.createElement('a')
          // 公共方法不支持截取，去掉fileName两边的""
          fileName = fileName.substr(0, fileName.length - 1)
          let spcStr = (fileName.split('=')[1]).substr(1)
          elink.download = fileName && decodeURI(spcStr)
          elink.style.display = 'none'
          elink.href = URL.createObjectURL(blob)
          document.body.appendChild(elink)
          elink.click()
          URL.revokeObjectURL(elink.href)
          document.body.removeChild(elink)
        } else {
          navigator.msSaveBlob(blob, fileName)
        }
      } else {
        _this.$message.error(response.msg)
      }
    })
  },
  /**
   * 每隔几个字符进行添加对应标记
   * @param {*} str 需要添加标记的字符串
   * @param {*} vkey 标记
   * @param {*} vnum 字符个数
   */
  strReplace: (str, vnum = 2, vkey = '-') => {
    if (!str) return ''
    let result = ''
    for (var i = 0, len = str.length; i < len; i++) {
      result += str[i]
      if (i % vnum == 1) result += vkey
    }
    if (result.slice(-1) == vkey) result = result.slice(0, -1)
    return result
  },
  /**
   * 判断是否是数组
   */
  isArrayFn: value => {
    if (typeof Array.isArray === 'function') {
      return Array.isArray(value)
    } else {
      return Object.prototype.toString.call(value) === '[object Array]'
    }
  },
  /**
   * isEmpty 判空
   */
  isEmpty: str => {
    let flag = true
    if (str != null && str != undefined)
      flag =
      str
      .replace(/(^\s*)|(\s*$)/g, '')
      .replace(/<\/?.+?>/g, '')
      .replace(/[\r\n]/g, '').length > 0 ?
      false :
      true
    return flag
  },
  /**
   * 时间格式化
   * @param {*} time 传入的时间
   */
  timeFamat(time) {
    var newTime = new Date(time)
    var year = newTime.getFullYear() //年
    var month = newTime.getMonth() + 1 //月
    var day = newTime.getDate() //日

    var clock = year + '-'

    if (month < 10) clock += '0'

    clock += month + '-'

    if (day < 10) clock += '0'

    clock += day

    return clock
  },
  /**
   * 创建当前时间
   * @param {*} isTrue 精确到年、月、天标识
   */
  createTime: isTrue => {
    var now = new Date()

    var year = now.getFullYear() //年
    var month = now.getMonth() + 1 //月
    var day = now.getDate() //日

    var hh = now.getHours() //时
    var mm = now.getMinutes() //分
    var ss = now.getSeconds() //秒
    if (isTrue == 'year') return year

    var clock = year + '-'

    if (month < 10) clock += '0'

    if (isTrue == 'month') return (clock += month)

    clock += month + '-'

    if (day < 10) clock += '0'

    clock += day

    //精确到天
    if (isTrue == 'day') return clock
    clock += clock + ' '

    if (hh < 10) clock += '0'

    clock += hh + ':'
    if (mm < 10) clock += '0'
    clock += mm + ':'

    if (ss < 10) clock += '0'
    clock += ss
    return clock
  },
  //加法
  NumberAdd: (arg1, arg2, n = 2) => {
    var r1, r2, m
    try {
      r1 = arg1.toString().split('.')[1].length
    } catch (e) {
      r1 = 0
    }
    try {
      r2 = arg2.toString().split('.')[1].length
    } catch (e) {
      r2 = 0
    }
    m = Math.pow(10, Math.max(r1, r2))
    return ((arg1 * m + arg2 * m) / m).toFixed(n)
  },
  //减法
  NumberSub: (arg1, arg2) => {
    var r1, r2, m, n
    try {
      r1 = arg1.toString().split('.')[1].length
    } catch (e) {
      r1 = 0
    }
    try {
      r2 = arg2.toString().split('.')[1].length
    } catch (e) {
      r2 = 0
    }
    m = Math.pow(10, Math.max(r1, r2))
    //动态控制精度长度
    n = r1 >= r2 ? r1 : r2
    return ((arg1 * m - arg2 * m) / m).toFixed(n)
  },
  //乘法
  NumberMul: (arg1, arg2) => {
    var m = 0,
      s1 = arg1.toString(),
      s2 = arg2.toString()
    try {
      m += s1.split('.')[1].length
    } catch (e) {}
    try {
      m += s2.split('.')[1].length
    } catch (e) {}
    return (Number(s1.replace('.', '')) * Number(s2.replace('.', ''))) / Math.pow(10, m)
  },

  //除法
  NumberDiv: (arg1, arg2) => {
    var t1 = 0,
      t2 = 0,
      r1,
      r2
    try {
      t1 = arg1.toString().split('.')[1].length
    } catch (e) {}
    try {
      t2 = arg2.toString().split('.')[1].length
    } catch (e) {}

    r1 = Number(arg1.toString().replace('.', ''))

    r2 = Number(arg2.toString().replace('.', ''))
    return (r1 / r2) * Math.pow(10, t2 - t1)
  },

  /* 
  检查数组是否有重复
  keyName:数组里面的对象的属性名
  */
  isArrayRepeat: (arr, keyName) => {
    let hash = {}
    if (keyName) {
      for (let i in arr) {
        if (hash[arr[i][keyName]]) {
          return true
        } else {
          hash[arr[i][keyName]] = true
        }
      }
    } else {
      for (let i in arr) {
        if (hash[arr[i]]) {
          return true
        } else {
          hash[arr[i]] = true
        }
      }
    }
    return false
  },
  /* 
    异步请求顺序执行
    _arr:方法名
  */
  async getAllFn(_arr, _this) {
    for (let i = 0; i < _arr.length; i++) {
      await this.getReqDataFn(_arr[i], _this)
    }
  },
  getReqDataFn(fn, _this) {
    var p = new Promise(resolve => {
      _this[fn]()
      setTimeout(() => {
        resolve()
      }, 100)
    })
    return p
  },

  /* 
  格式化金额数字, 加千分位并保留两位小数
  params: num(传入的金额)
  */
  formatAmountNum: num => {
    if (num == 0) {
      return num
    }
    if (!num) {
      return
    }
    num = num + ''
    if (!num.includes('.')) {
      num += '.'
    }
    return num
      .replace(/(\d)(?=(\d{3})+\.)/g, function ($0, $1) {
        return $1 + ','
      })
      .replace(/\.$/, '.00')
  },
  /**
   * 时间比较大小
   */
  compareDate: (sDate, eDate, isSame = false) => {
    let st = new Date(sDate)
    let et = new Date(eDate)
    let flag = !st && !et ? false : st.getTime() >= et.getTime() ? false : true
    if (isSame) flag = !st && !et ? false : st.getTime() > et.getTime() ? false : true
    return flag
  },
  /**
   * 手机号码验证
   */
  checkPhoneFn: (mobile) => {
    let reg = /^0?(13[0-9]|14[5-9]|15[012356789]|166|17[0-8]|18[0-9]|19[0-9])[0-9]{8}$/;
    return reg.test(mobile)
  },
  /**
   * 将指定日期区间按月份分割
   * @param {Object} beginDate 开始日期
   * @param {Object} endDate 结束日期
   * @return {Array} 分割好的array数组
   */
  dateCutByMonth(beginDate, endDate) {
    //分割好的数组
    let dateCutList = new Array();
    let b_date = new Date(beginDate);
    let e_date = new Date(endDate);
    //获取各个年份
    let b_year = parseInt(b_date.getFullYear());
    let e_year = parseInt(e_date.getFullYear());
    //获取各个月份
    let b_month = parseInt(b_date.getMonth()) + 1;
    let e_month = parseInt(e_date.getMonth()) + 1;

    //获取日期之间相差的月数
    let month_list = monthList();

    //按月份分割日期
    for (let i = 0; i < month_list.length; i++) {
      //当前月开始日期:第一天
      let i_b_date = new Date(month_list[i]);
      i_b_date.setDate(1);
      //当前月最后一天
      let i_e_date = new Date(month_list[i]);
      i_e_date.setMonth(i_e_date.getMonth() + 1);
      i_e_date.setDate(1);
      i_e_date.setDate(i_e_date.getDate() - 1);

      //第一次循环：开始月份
      if (i == 0) {
        let i_e_ymd = dateToString(i_e_date);
        dateCutList.push([beginDate, i_e_ymd]);

        //除第一次和最后一次循环：中间月份
      } else if (i != 0 && i != month_list.length - 1) {
        let i_b_ymd = dateToString(i_b_date);
        let i_e_ymd = dateToString(i_e_date);
        dateCutList.push([i_b_ymd, i_e_ymd]);
        //最后一次循环：结束月份
      } else if (i == month_list.length - 1) {
        let i_b_ymd = dateToString(i_b_date);
        dateCutList.push([i_b_ymd, endDate]);
      }
    }
    return dateCutList;
    /**
     * 获取日期区间的月份集合
     */
    function monthList() {
      //相差的月份总数
      let result = new Array();

      let b = new Date(b_year, b_month - 1, 1);
      let e = new Date(e_year, e_month - 1, 1);
      while (b < e) {
        result.push(b.getFullYear() + "-" + (b.getMonth() + 1));
        b.setMonth(b.getMonth() + 1);
      }
      result.push(e_year + "-" + e_month);
      return result;
    }
    /**
     * 将日期转换为指定格式的字符串
     * @param {Date} date 要转换的日期
     */
    function dateToString(date) {
      let month = (date.getMonth() + 1);
      let day = date.getDate();
      if (month < 10) {
        month = '0' + month
      }
      if (day < 10) {
        day = '0' + day
      }
      return date.getFullYear() + "-" + (month) + "-" + day;
    }

  },
}

export default global