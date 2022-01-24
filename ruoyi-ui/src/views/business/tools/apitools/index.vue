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
                  <el-button v-loading="loading2" type="primary" icon="el-icon-search" size="mini"
                             @click="getBeautyPicture()"
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
                  <el-button v-loading="loading3" type="primary" icon="el-icon-search" @click="getHistoryToday()"
                             size="mini"
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
                <el-button v-loading="loading4" type="primary" @click="getIdCardQuery('idCardForm')" slot="reference">
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
                  <el-button type="primary" slot="reference" @click="getMobileBelong('mobileBelongForm')">搜索</el-button>
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
                  <el-button v-loading="loading6" type="primary" slot="reference"
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
                  <el-button v-loading="loading7" type="primary" slot="reference"
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
                  <el-button v-loading="loading8" type="primary" slot="reference"
                             @click="getGarbageSorting('garbageSortingForm')">搜索
                  </el-button>
                </el-popover>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" class="">
              <el-form-item label="简繁转换" label-width="100px">
                <el-input placeholder="请输入简体中文"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary">搜索</el-button>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" class="">
              <el-form-item label="汉语字典" label-width="100px">
                <el-input placeholder="请输入单个中文"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary">搜索</el-button>
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
  getChineseDict
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

      },


      //----------------其他参数-------------------
      weather,
    }
  },

  created() {

  }
  ,

  methods: {
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
