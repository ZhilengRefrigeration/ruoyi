<template>
  <div v-loading="loading"
       element-loading-text="拼命加载中"
       element-loading-spinner="el-icon-loading"
       element-loading-background="rgba(0, 0, 0, 0.8)">

    <el-form :model="historyWeatherParams" ref="historyWeatherParams"
             :inline="true"
             label-width="70px"
             style="margin-left: 150px">
      <el-form-item label="">
        <el-date-picker
          v-model="daterangeCreateTime"
          size="small"
          style="width: 320px"
          value-format="yyyy-MM-dd"
          format="yyyy 年 MM 月 dd 日"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :picker-options="pickerOptions"
          @change="dateQuery"
        ></el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <div ref="historyChart" style="height:350px;width: 100%;"></div>

    <div ref="futureChart" style="height: 400px;width: 100%;margin-top: 20px"></div>

  </div>
</template>

<script>

import {getHistoryWeather, getFutureWeather} from "@/api/business/statistics/weatherstatistics";
import {pickerOptions} from "@/layout/mixin/PickerOptions";

// 引入 ECharts 主模块
var echarts = require('echarts/lib/echarts');
// 引入柱状图
require('echarts/lib/chart/line');
// 引入提示框和标题组件
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');

export default {
  name: "WeatherStatistics",
  mixins: [pickerOptions],
  data() {
    return {
      historyWeatherData: {},
      futureWeatherData: {},

      historyWeatherParams: {
        startDate: null,
        endDate: null,
      },
      //检查查询范围
      daterangeCreateTime: [],

      //遮罩层
      loading: false,
    }
  },

  created() {
    this.getHistoryWeather()
    this.getFutureWeather()
  },


  methods: {
    initHistory() {
      let historyChart = echarts.init(this.$refs.historyChart)
      historyChart.setOption({
        title: {
          text: '天气(单位℃)' + '-' + this.historyWeatherData.city[0],
          textStyle: {
            color: '#541264',
            fontWeight: '1000',
            align: 'center',
          },
          left: "center",
        },
        tooltip: {},
        xAxis: {
          type: 'category',
          data: this.historyWeatherData.reportTime,
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value} °C'
          }
        },
        series: [
          {
            data: this.historyWeatherData.temperature,
            type: 'line',
            smooth: true,
          }
        ]
      })

    },

    initFuture() {
      let futureChart = echarts.init(this.$refs.futureChart)
      futureChart.setOption({
        title: {
          text: '预报天气(单位℃)' + '-' + this.futureWeatherData.city[0],
          textStyle: {
            color: '#541264',
            fontWeight: '1000',
            align: 'center',
          },
          left: "center",
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {},

        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: this.futureWeatherData.dateWeek
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value} °C'
          }
        },
        series: [
          {
            name: '最高温度',
            type: 'line',
            data: this.futureWeatherData.maxTemperature,

          },
          {
            name: '最低温度',
            type: 'line',
            data: this.futureWeatherData.minTemperature,

          }
        ]
      })
    },

    //获取未来天气
    getFutureWeather() {
      this.loading = true
      getFutureWeather().then(res => {
        this.loading = false
        this.futureWeatherData = res.data
        this.initFuture()
      }).catch(err => {
        this.loading = false
      })
    },

    //获取历史天气
    getHistoryWeather() {
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.historyWeatherParams.startDate = this.daterangeCreateTime[0];
        this.historyWeatherParams.endDate = this.daterangeCreateTime[1];
      }
      getHistoryWeather(this.historyWeatherParams).then(res => {
        this.historyWeatherData = res.data;
        this.initHistory()
      })
    },

    dateQuery() {
      //清空时间参数
      this.historyWeatherParams.startDate=null
      this.historyWeatherParams.endDate=null

      this.handleQuery();
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.getHistoryWeather();
    },


    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.historyWeatherParams.startDate = null
      this.historyWeatherParams.endDate = null
      this.handleQuery();
    },

  },

}
</script>

<style scoped>

</style>
