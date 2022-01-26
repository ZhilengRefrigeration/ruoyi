<template>
  <div v-loading="loading"
       element-loading-text="拼命加载中"
       element-loading-spinner="el-icon-loading"
       element-loading-background="rgba(0, 0, 0, 0.8)">

    <div ref="historyChart" style="height: 400px;width: 100%;margin-top: 25px">

    </div>
    <div ref="todayChart" style="height: 400px;width: 100%">

    </div>

  </div>


</template>

<script>

import {getStatisticsHistoryApi, getStatisticsTodayApi} from "@/api/business/statistics/apistatistics";

// 引入 ECharts 主模块
var echarts = require('echarts/lib/echarts');
// 引入柱状图
require('echarts/lib/chart/bar');
// 引入提示框和标题组件
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');

export default {
  name: "ApiStatistics",


  data() {
    return {
      historyApiData: {},
      todayApiData: {},

      //遮罩层
      loading: false,
    }
  },

  created() {
    this.getStatisticsHistoryApi()
    this.getStatisticsTodayApi()
  },

  mounted() {
  },

  methods: {
    initHistory() {
      let historyChart = echarts.init(this.$refs.historyChart)
      // 绘制图表
      historyChart.setOption({
        title: {
          text: '总计API调用次数',
          textStyle:{
            color: '#541264',
            fontWeight:'1000',
            align:'center',
          },
          left:"center",
        },
        tooltip: {},
        xAxis: {
          data: this.historyApiData.apiNames
        },
        yAxis: {
          splitNumber: 10,
          max:2000,
        },
        series: [{
          name: '次数',
          type: 'bar',
          data: this.historyApiData.count
        }]
      });
    },

    initToday() {
      let todayChart = echarts.init(this.$refs.todayChart)
      // 绘制图表
      todayChart.setOption({
        title: {
          text: '今日API调用次数',
          textStyle:{
            color: '#541264',
            fontWeight:'1000',
            align:'center',
          },
          left:"center",
        },
        tooltip: {},
        xAxis: {
          data: this.todayApiData.apiNames
        },
        yAxis: {
          splitNumber: 10,
          max:60,
        },
        series: [{
          name: '次数',
          type: 'bar',
          data: this.todayApiData.count
        }]
      });
    },


    //查询API历史记录统计
    getStatisticsHistoryApi() {
      getStatisticsHistoryApi().then(res => {
        this.historyApiData = res.data
        this.initHistory()
      })
    },

    //查询API当天记录统计
    getStatisticsTodayApi() {
      this.loading=true
      getStatisticsTodayApi().then(res => {
        this.loading=false
        this.todayApiData = res.data
        this.initToday()
      }).catch(err =>{
        this.loading=false
      })
    },

  },

}
</script>

<style scoped>

</style>
