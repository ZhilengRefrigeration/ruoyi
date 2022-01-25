<template>
  <div>
    <el-row>
      <el-col :span="24">
        <div class="top_col">
          <span style="cursor: pointer">小工具</span>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="table_col">
          <div>
            <el-form label-width="80px" label-position="right">
              <el-form-item label="节假日" label-width="80px">
                <el-popover
                  placement="bottom"
                  width="400"
                  trigger="manual"
                  v-model="holidayVisible">
                  <div v-for="data in holidayData" v-loading="loading1">
                    <span>&nbsp;{{ data.holidayName }}&nbsp;</span>
                    <span>&nbsp;------&nbsp;</span>
                    <span>{{ data.returnDate }}</span>
                    <span>&nbsp;------&nbsp;</span>
                    <span>还剩&nbsp;<span style="color: red">{{ data.residueDays }}</span>&nbsp;天</span>
                    <el-divider><i class="el-icon-chat-round"></i></el-divider>
                  </div>
                  <el-button @click="close" icon="el-icon-close" circle plain size="mini"
                             style="float: right"></el-button>
                  <el-button type="primary"
                             v-loading="loading1"
                             v-hasPermi="['open:apitools:holiday']"
                             icon="el-icon-search"
                             size="mini"
                             @click="getHoliday()"
                             slot="reference">
                    搜索
                  </el-button>
                </el-popover>
              </el-form-item>
            </el-form>
          </div>

          <div>
            <el-form label-width="80px" label-position="right">
              <el-form-item label="MM图片" label-width="80px">

                <el-popover
                  placement="bottom"
                  width="988"
                  trigger="manual"
                  v-model="beautyPictureVisible">
                  <el-image v-for="data in BeautyPictureData" v-loading="loading2"
                            style="width: 192px; height: 108px"
                            :src="data.imageUrl"
                            :preview-src-list="pictureList">
                  </el-image>
                  <el-button @click="close" icon="el-icon-close" circle plain size="mini"
                             style="float: right"></el-button>
                  <el-button v-loading="loading2"
                             type="primary"
                             icon="el-icon-search"
                             size="mini"
                             @click="getBeautyPicture()"
                             v-hasPermi="['open:apitools:beautypicture']"
                             slot="reference">搜索
                  </el-button>
                </el-popover>

              </el-form-item>
            </el-form>
          </div>

          <div>
            <el-form label-width="80px" label-position="right">
              <el-form-item label="历史今天" label-width="80px">

                <el-popover
                  placement="right"
                  width="400"
                  trigger="manual"
                  v-model="historyTodayVisible">

                  <div v-for="data in historyTodayData" v-loading="loading3">
                    <span>
                      {{ data.date }}
                    </span>
                    <br>
                    <span style="font-size: 12px">
                      {{ data.title }}
                    </span>
                    <el-divider><i class="el-icon-chat-round"></i></el-divider>
                  </div>
                  <el-button @click="close" icon="el-icon-close" circle plain size="mini"
                             style="float: right"></el-button>
                  <el-button v-loading="loading3"
                             type="primary"
                             icon="el-icon-search"
                             @click="getHistoryToday()"
                             size="mini"
                             v-hasPermi="['open:apitools:historytoday']"
                             slot="reference">搜索
                  </el-button>

                </el-popover>

              </el-form-item>
            </el-form>
          </div>
        </div>

        <div class="table_col2">
          <el-form :inline="true" :rules="rules" :model="idCardForm" ref="idCardForm">
            <el-form-item label="身份证查询" label-width="100px" prop="idCard">
              <el-input v-model="idCardForm.idCard" placeholder="请输入身份证"></el-input>
            </el-form-item>
            <el-form-item>
              <el-popover
                placement="bottom"
                width="300"
                trigger="manual"
                v-model="idCardVisible">
                <el-card shadow="hover" v-loading="loading4">
                  <div style="font-size: 12px">
                    <span>身份证号：{{ idCardData.idCardNum }}</span> <br>
                    <hr>
                    <span v-html="'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址：'"></span>
                    <span>{{ idCardData.address }}</span>
                    <br>
                    <hr>
                    <span v-html="'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生日：'"></span>
                    <span>{{ idCardData.birthday }}</span> <br>
                    <hr>
                    <span v-html="'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别：'"></span>
                    <span>{{ idCardData.sex }}</span> <br>
                    <hr>
                  </div>
                </el-card>
                <el-button @click="close" icon="el-icon-close" circle plain size="mini"
                           style="float: right"></el-button>
                <el-button v-loading="loading4" type="primary"
                           @click="getIdCardQuery('idCardForm')"
                           v-hasPermi="['open:apitools:idcardquery']"
                           slot="reference">
                  搜索
                </el-button>
              </el-popover>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="top_col">
          <span style="cursor: pointer">实用工具</span>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="table2_col">
          <div class="table2_col_div">
            <el-form :inline="true" :rules="rules" :model="mobileBelongForm" ref="mobileBelongForm">
              <el-form-item label="手机归属地" label-width="100px" prop="mobile">
                <el-input v-model="mobileBelongForm.mobile" placeholder="请输入手机号码"></el-input>
              </el-form-item>
              <el-form-item>
                <el-popover
                  placement="bottom"
                  width="220"
                  trigger="manual"
                  v-model="mobileBelongVisible">
                  <div v-loading="loading5">
                    <el-result icon="info" title="手机归属地" :subTitle="mobileBelongData.carrier">
                    </el-result>
                  </div>
                  <el-button @click="close" icon="el-icon-close" circle plain size="mini"
                             style="float: right"></el-button>
                  <el-button type="primary"
                             slot="reference"
                             v-hasPermi="['open:apitools:mobilebelong']"
                             @click="getMobileBelong('mobileBelongForm')">搜索
                  </el-button>
                </el-popover>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" :rules="rules" :model="nowWeatherForm" ref="nowWeatherForm">
              <el-form-item label="实时天气" label-width="100px" prop="city">
                <el-input v-model="nowWeatherForm.city" placeholder="请输入城市名称"></el-input>
              </el-form-item>
              <el-form-item>
                <el-popover
                  placement="bottom"
                  width="250"
                  trigger="manual"
                  v-model="nowWeatherVisible">
                  <div v-loading="loading6">
                    <span>地理位置：{{ nowWeatherData.address }}</span><br>
                    <span>空气湿度：<span style="color: red">{{ nowWeatherData.humidity }}</span></span><br>
                    <span>实时温度：<span style="color: red">{{ nowWeatherData.temp }}</span></span><br>
                    <span>实时风力：{{ nowWeatherData.winddirection }} {{ nowWeatherData.windpower }}</span><br>
                    <span>
                      <img :src="weather"
                           style="width: 30px;height: 30px;float: left;">
                      <span style="margin-left:40px;float: left;width: 30px;height: 30px; line-height: 30px;color: red">{{
                          nowWeatherData.weather
                        }}</span>
                    </span>
                  </div>
                  <el-button @click="close" icon="el-icon-close" circle plain size="mini"
                             style="float: right"></el-button>
                  <el-button v-loading="loading6"
                             type="primary"
                             slot="reference"
                             v-hasPermi="['open:apitools:nowweather']"
                             @click="getNowWeather('nowWeatherForm')">搜索
                  </el-button>
                </el-popover>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" :rules="rules" :model="forecastWeatherForm" ref="forecastWeatherForm">
              <el-form-item label="预报天气" label-width="100px" prop="city">
                <el-input v-model="forecastWeatherForm.city" placeholder="请输入城市名称"></el-input>
              </el-form-item>
              <el-form-item>
                <el-popover
                  placement="left"
                  width="300"
                  trigger="manual"
                  v-model="forecastWeatherVisible">
                  <div v-loading="loading7">
                    <el-card shadow="hover">
                      <div slot="header" class="clearfix">
                        <span>{{ forecastWeatherData.address }}</span>
                      </div>
                      <div v-for="(data,index) in forecastWeatherData.forecasts">
                        <ul style="margin: 0 0; padding: 0 0 0 10px; list-style-type: none">
                          <li>
                            <span v-if="index===0">
                              今天({{ data.dayOfWeek }})
                            </span>
                            <span v-if="index===1">
                              明天({{ data.dayOfWeek }})
                            </span>
                            <span v-if="index===2">
                              后天({{ data.dayOfWeek }})
                            </span>
                            <span v-if="index===3">
                              大后({{ data.dayOfWeek }})
                            </span>

                            <span style="color: red;margin-left: 10px;margin-right: 10px">
                              {{ data.nighttemp }}~{{ data.daytemp }}
                            </span>
                            <span>
                              {{ data.dayweather }}
                            </span>
                          </li>
                        </ul>
                        <hr>
                      </div>
                    </el-card>
                  </div>
                  <el-button @click="close" icon="el-icon-close" circle plain size="mini"
                             style="float: right"></el-button>
                  <el-button v-loading="loading7"
                             type="primary"
                             slot="reference"
                             v-hasPermi="['open:apitools:forecastweather']"
                             @click="getForecastWeather('forecastWeatherForm')">搜索
                  </el-button>
                </el-popover>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" :rules="rules" :model="garbageSortingForm" ref="garbageSortingForm">
              <el-form-item label="垃圾分类" label-width="100px" prop="name">
                <el-input v-model="garbageSortingForm.name" placeholder="请输入垃圾名称"></el-input>
              </el-form-item>
              <el-form-item>
                <el-popover
                  placement="bottom"
                  width="350"
                  trigger="manual"
                  v-model="garbageSortingVisible">
                  <div v-loading="loading8">
                    <el-card shadow="hover">
                      <span>名称：{{ aim.goodsName }}</span>
                      <el-divider direction="vertical"></el-divider>
                      <span>类型：{{ aim.goodsType }}</span>
                    </el-card>
                    <el-divider content-position="center">推荐</el-divider>
                    <el-card shadow="hover" v-for="data in recommendList">
                      <span>名称：{{ data.goodsName }}</span>
                      <el-divider direction="vertical"></el-divider>
                      <span>类型：{{ data.goodsType }}</span>
                    </el-card>
                  </div>
                  <el-button @click="close" icon="el-icon-close" circle plain size="mini"
                             style="float: right"></el-button>
                  <el-button v-loading="loading8"
                             type="primary"
                             slot="reference"
                             v-hasPermi="['open:apitools:garbagesorting']"
                             @click="getGarbageSorting('garbageSortingForm')">搜索
                  </el-button>
                </el-popover>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" :rules="rules" :model="simpleComplexForm" ref="simpleComplexForm">
              <el-form-item label="简繁转换" label-width="100px" prop="content">
                <el-input v-model="simpleComplexForm.content" placeholder="请输入简体中文"></el-input>
              </el-form-item>
              <el-form-item>
                <el-popover
                  placement="bottom"
                  width="300"
                  trigger="manual"
                  v-model="simpleComplexVisible">
                  <div>
                    <el-card shadow="hover">
                      <span>简体：{{ simpleComplexData.originContent }}</span>
                    </el-card>
                    <el-card shadow="hover">
                      <span>繁体：{{ simpleComplexData.convertContent }}</span>
                    </el-card>
                  </div>
                  <el-button @click="close" icon="el-icon-close" circle plain size="mini"
                             style="float: right"></el-button>
                  <el-button v-loading="loading9"
                             type="primary"
                             slot="reference"
                             v-hasPermi="['open:apitools:simplecomplex']"
                             @click="getSimpleComplex('simpleComplexForm')">搜索
                  </el-button>
                </el-popover>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" :rules="rules" :model="chineseDictForm" ref="chineseDictForm">
              <el-form-item label="汉语字典" label-width="100px" prop="dict">
                <el-input v-model="chineseDictForm.dict" placeholder="请输入单个中文"></el-input>
              </el-form-item>
              <el-form-item>
                <el-popover
                  placement="left"
                  width="560"
                  trigger="manual"
                  v-model="chineseDictVisible">
                  <div>
                    <el-card shadow="hover">
                      <span>原内容：{{ chineseDictData.word }}</span>
                    </el-card>
                    <el-card shadow="hover">
                      <span>繁体：{{ chineseDictData.traditional }}</span>
                    </el-card>
                    <el-card shadow="hover">
                      <span>拼音：{{ chineseDictData.pinyin }}</span>
                    </el-card>
                    <el-card shadow="hover">
                      <span>笔画数：{{ chineseDictData.strokes }}</span>
                    </el-card>
                    <el-card shadow="hover">
                      <span>偏旁部首：{{ chineseDictData.radicals }}</span>
                    </el-card>
                    <el-card shadow="hover">
                      <span>汉字释义：{{ chineseDictData.explanation }}</span>
                    </el-card>
                  </div>

                  <el-button @click="close" icon="el-icon-close" circle plain size="mini"
                             style="float: right"></el-button>
                  <el-button v-loading="loading10"
                             type="primary"
                             slot="reference"
                             v-hasPermi="['open:apitools:chinesedict']"
                             @click="getChineseDict('chineseDictForm')">搜索
                  </el-button>
                </el-popover>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" :rules="rules" :model="ipInfoForm" ref="ipInfoForm">
              <el-form-item label="IP查询" label-width="100px" prop="ip">
                <el-input v-model="ipInfoForm.ip" placeholder="请输入单个中文"></el-input>
              </el-form-item>
              <el-form-item>
                <el-popover
                  placement="bottom"
                  width="360"
                  trigger="manual"
                  v-model="ipInfoVisible">
                  <div>
                    <el-card shadow="hover">
                      <span>城市：{{ ipInfoData.city }}</span>
                    </el-card>
                    <el-card shadow="hover">
                      <span>省份：{{ ipInfoData.province }}</span>
                    </el-card>
                    <el-card shadow="hover">
                      <span>服务商：{{ ipInfoData.isp }}</span>
                    </el-card>
                    <el-card shadow="hover">
                      <span>IP：{{ ipInfoData.ip }}</span>
                    </el-card>
                    <el-card shadow="hover">
                      <span>描述：{{ ipInfoData.desc }}</span>
                    </el-card>
                  </div>
                  <el-button @click="close" icon="el-icon-close" circle plain size="mini"
                             style="float: right"></el-button>
                  <el-button v-loading="loading11"
                             type="primary"
                             slot="reference"
                             v-hasPermi="['open:apitools:ipinfo']"
                             @click="getIpInfo('ipInfoForm')">搜索
                  </el-button>
                </el-popover>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>

import {
  getHoliday,
  getHistoryToday,
  getBeautyPicture,
  getIdCardQuery,
  getMobileBelong,
  getNowWeather,
  getForecastWeather,
  getGarbageSorting,
  getSimpleComplex,
  getChineseDict,
  getIpInfo
} from "@/api/business/openapi/apitools";

import weather from "@/assets/icons/weather/天气.png"

export default {
  name: "Apitools",

  data() {
    return {
      //--------------获取的数据--------------------
      holidayData: [],
      BeautyPictureData: [],
      pictureList: [],   //单独存放图片路径
      historyTodayData: [],
      idCardData: {},
      mobileBelongData: {},
      nowWeatherData: {},
      forecastWeatherData: {},
      aim: {
        goodsName: '-',
        goodsType: '-',
      },
      recommendList: [{
        goodsName: '-',
        goodsType: '-',
      }],
      simpleComplexData: {},
      chineseDictData: {},
      ipInfoData: {},

      //-------------input框数据-------------------
      idCardForm: {
        idCard: ''
      },
      mobileBelongForm: {
        mobile: ''
      },
      nowWeatherForm: {
        city: ''
      },
      forecastWeatherForm: {
        city: ''
      },
      garbageSortingForm: {
        name: ''
      },
      simpleComplexForm: {
        content: ''
      },
      chineseDictForm: {
        content: ''
      },
      ipInfoForm: {
        ip: ''
      },

      //------------控制弹出显示隐藏-----------------
      holidayVisible: false,
      beautyPictureVisible: false,
      historyTodayVisible: false,
      idCardVisible: false,
      mobileBelongVisible: false,
      nowWeatherVisible: false,
      forecastWeatherVisible: false,
      garbageSortingVisible: false,
      simpleComplexVisible: false,
      chineseDictVisible: false,
      ipInfoVisible: false,

      //----------------遮罩层-------------------
      loading1: false,
      loading2: false,
      loading3: false,
      loading4: false,
      loading5: false,
      loading6: false,
      loading7: false,
      loading8: false,
      loading9: false,
      loading10: false,
      loading11: false,

      //---------------校验规则--------------------
      rules: {
        idCard: [
          {required: true, message: '请输入身份证号！！！', trigger: 'blur'},
          {required: true, validator: this.validatorIdNum, trigger: 'blur'},
        ],
        mobile: [
          {required: true, message: '请输入手机号码！！！', trigger: 'blur'},
          {required: true, validator: this.validatorPhone, trigger: 'blur'},
        ],
        city: [
          {required: true, message: '请输入地名！！！', trigger: 'blur'},
        ],
        name: [
          {required: true, message: '请输入垃圾名称！！！', trigger: 'blur'},
        ],
        content: [
          {required: true, message: '请输入垃圾名称！！！', trigger: 'blur'},
        ],
        dict: [
          {required: true, message: '请输入简体中文！！！', trigger: 'blur'},
          {min: 1, max: 1, message: '长度在 1 个字符', trigger: 'blur'}
        ],
        ip: [
          {required: true, message: '请输入IP地址！！！', trigger: 'blur'},
          {required: true, validator: this.validatorIp, trigger: 'blur'},
        ],

      },

      //----------------其他参数-------------------
      weather,
    }
  },

  created() {
  }
  ,

  methods: {
    //获取Ip信息
    getIpInfo(ipInfoForm) {
      this.$refs[ipInfoForm].validate((valid) => {
        this.ipInfoData = {}
        if (valid) {
          this.loading11 = true
          getIpInfo(this.ipInfoForm.ip).then(res => {
            this.loading11 = false
            this.ipInfoVisible = true
            this.ipInfoData = res.data
          }).catch(err => {
            this.loading11 = false
          })
        } else {
          return false
        }
      })
    },

    //获取汉语字典信息
    getChineseDict(chineseDictForm) {
      this.$refs[chineseDictForm].validate((valid) => {
        this.chineseDictData = {}
        if (valid) {
          this.loading10 = true
          getChineseDict(this.chineseDictForm.dict).then(res => {
            this.loading10 = false
            this.chineseDictVisible = true
            this.chineseDictData = res.data
          }).catch(err => {
            this.loading10 = false
          })
        } else {
          return false
        }
      })
    },


    //获取简繁转换信息
    getSimpleComplex(simpleComplexForm) {
      this.$refs[simpleComplexForm].validate((valid) => {
        this.simpleComplexData = {}
        if (valid) {
          this.loading9 = true
          getSimpleComplex(this.simpleComplexForm.content).then(res => {
            this.loading9 = false
            this.simpleComplexVisible = true
            this.simpleComplexData = res.data
          }).catch(err => {
            this.loading9 = false
          })
        } else {
          return false
        }
      })
    }
    ,

    //获取垃圾分类信息
    getGarbageSorting(garbageSortingForm) {
      this.$refs[garbageSortingForm].validate((valid) => {
        this.aim = {}
        this.recommendList = []
        if (valid) {
          this.loading8 = true
          getGarbageSorting(this.garbageSortingForm.name).then(res => {
            this.loading8 = false
            this.garbageSortingVisible = true
            if (res.data.aim !== null) {
              this.aim = res.data.aim;
            }
            if (res.data.recommendList !== null) {
              this.recommendList = res.data.recommendList;
            }
          }).catch(err => {
            this.loading8 = false
          })
        } else {
          return false
        }
      })
    },


    //获取预报天气信息
    getForecastWeather(forecastWeatherForm) {
      this.$refs[forecastWeatherForm].validate((valid) => {
        this.forecastWeatherData = {}
        if (valid) {
          this.loading7 = true
          getForecastWeather(this.forecastWeatherForm.city).then(res => {
            this.loading7 = false
            this.forecastWeatherVisible = true
            this.forecastWeatherData = res.data
          }).catch(err => {
            this.loading7 = false
          })
        } else {
          return false
        }
      })
    },

    //获取实时天气信息
    getNowWeather(nowWeatherForm) {
      this.$refs[nowWeatherForm].validate((valid) => {
        this.nowWeatherData = {}
        if (valid) {
          this.loading6 = true
          getNowWeather(this.nowWeatherForm.city).then(res => {
            this.loading6 = false
            this.nowWeatherVisible = true
            this.nowWeatherData = res.data
          }).catch(err => {
            this.loading6 = false
          })
        } else {
          return false
        }
      })
    },

    //自定义rules校验方法：手机号校验
    validatorPhone(rule, value, callback) {
      if (value === '') {
        callback(new Error('手机号不能为空'))
      } else if (!/^1\d{10}$/.test(value)) {
        callback(new Error('手机号格式错误'))
      } else {
        callback()
      }
    },

    //自定义rules校验方法：身份证校验
    validatorIdNum(rule, value, callback) {
      const reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
      if (!value) {
        return callback(new Error('证件号码不能为空'))
      } else if (!reg.test(value)) {
        return callback(new Error('证件号码不正确'))
      } else {
        callback()
      }
    },

    validatorIp(rule, value, callback) {
      const reg = /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/
      if (!value) {
        return callback(new Error('ip地址不能为空'))
      } else if (!reg.test(value)) {
        return callback(new Error('ip地址不正确'))
      } else {
        callback()
      }
    },

    //关闭窗口
    close() {
      this.mobileBelongVisible = false;
      this.idCardVisible = false;
      this.historyTodayVisible = false;
      this.beautyPictureVisible = false;
      this.holidayVisible = false;
      this.nowWeatherVisible = false;
      this.forecastWeatherVisible = false;
      this.garbageSortingVisible = false;
      this.simpleComplexVisible = false;
      this.chineseDictVisible = false;
      this.ipInfoVisible = false
    },

    //获取手机归属地信息
    getMobileBelong(mobileBelongForm) {
      this.$refs[mobileBelongForm].validate((valid) => {
        this.mobileBelongData = {}
        if (valid) {
          this.mobileBelongVisible = true
          this.loading5 = true
          getMobileBelong(this.mobileBelongForm.mobile).then(res => {
            this.loading5 = false
            this.mobileBelongData = res.data
          }).catch(err => {
            this.loading5 = false
          })
        } else {
          return false;
        }
      });
    },

    //获取身份证信息
    getIdCardQuery(idCardForm) {
      this.$refs[idCardForm].validate((valid) => {
        this.idCardData = {}
        if (valid) {
          this.idCardVisible = true
          this.loading4 = true
          getIdCardQuery(this.idCardForm.idCard).then(res => {
            this.loading4 = false
            this.idCardData = res.data
          }).catch(err => {
            this.loading4 = false
          })
        } else {
          return false;
        }
      });
    },

    //获取历史今天数据信息
    getHistoryToday() {
      this.loading3 = true
      this.historyTodayVisible = true
      getHistoryToday().then(res => {
        this.loading3 = false
        this.historyTodayData = res.data
      }).catch(err => {
        this.loading3 = false
      })
    },

    //获取节假日信息
    getHoliday() {
      this.loading1 = true
      this.holidayVisible = true
      getHoliday().then(res => {
        this.loading1 = false
        this.holidayData = res.data
      }).catch(err => {
        this.loading3 = false
      })
    },

    //获取mm图片信息
    getBeautyPicture() {
      this.loading2 = true
      this.pictureList = []
      this.beautyPictureVisible = true
      getBeautyPicture().then(res => {
        this.loading2 = false
        this.BeautyPictureData = res.data
        //单独把图片放入数组
        res.data.forEach(data => {
          this.pictureList.push(data.imageUrl)
        })
      }).catch(err => {
        this.loading3 = false
      })
    },

  }

}
</script>

<style scoped>
.top_col {
  height: 40px;
  margin: 10px 10px;
  background-color: #bfcbd9;
  line-height: 40px;
  padding-left: 20px;
  border-radius: 17px;
  font-size: 13px;
}

.table_col {
  height: 120px;
  padding: 20px;
  display: flex;
  align-items: center;
  width: 64.5%;
  float: left;
}

.table_col2 {
  width: 35.5%;
  float: left;
  height: 120px;
  padding: 20px;
  display: flex;
  align-items: center;
}

.table_col div {
  float: left;
  width: 200px;
}


.table2_col {
  height: 135px;
  padding: 20px;
}


.table2_col_div {
  float: left;
  width: 33%;
}


</style>
