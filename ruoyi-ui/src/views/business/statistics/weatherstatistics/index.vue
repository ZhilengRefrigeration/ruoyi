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
          @change="handleQuery"
        ></el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <div ref="historyChart" style="height: 280px;width: 100%;"></div>

    <div ref="futureChart" style="height: 400px;width: 100%"></div>

  </div>
</template>

<script>

import {getHistoryWeather} from "@/api/business/statistics/weatherstatistics";

// 引入 ECharts 主模块
var echarts = require('echarts/lib/echarts');
// 引入柱状图
require('echarts/lib/chart/line');
// 引入提示框和标题组件
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');

export default {
  name: "WeatherStatistics",
  data() {
    return {
      historyWeatherData: {},

      historyWeatherParams: {
        startDate: null,
        endDate: null,
      },
      //检查查询范围
      daterangeCreateTime: [],

      //遮罩层
      loading: false,

      //日期组件
      pickerOptions: {
        shortcuts: [{
          text: '昨天',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 );
            picker.$emit('pick', [start, end]);
          }
        },{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
    }
  },

  created() {
    this.getHistoryWeather()
  },


  methods: {
    initHistory() {
      let historyChart = echarts.init(this.$refs.historyChart)
      historyChart.setOption({
        title: {
          text: '天气（单位℃）',
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
          type: 'value'
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


    //获取历史天气
    getHistoryWeather() {
      this.loading = true
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.historyWeatherParams.startDate = this.daterangeCreateTime[0];
        this.historyWeatherParams.endDate = this.daterangeCreateTime[1];
      }
      getHistoryWeather(this.historyWeatherParams).then(res => {
        this.loading = false
        this.historyWeatherData = res.data;
        this.initHistory()
      }).catch(err => {
      })
      this.loading = false
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
