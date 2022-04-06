<template>
  <div v-loading="loading"
       element-loading-text="拼命加载中"
       element-loading-spinner="el-icon-loading"
       element-loading-background="rgba(0, 0, 0, 0.8)">

    <el-form :model="historyApiLogParams" ref="historyApiLogParams"
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


    <div ref="historyChart" style="height: 372px;width: 100%;margin-top: 25px">

    </div>
    <div ref="todayChart" style="height: 372px;width: 100%">

    </div>

  </div>


</template>

<script>

import {
  getStatisticsHistoryApi,
  getStatisticsTodayApi,
  statisticsByDate
} from "@/api/business/statistics/apistatistics";


import * as echarts from 'echarts/core';
import {GridComponent} from 'echarts/components';
import {LineChart} from 'echarts/charts';
import {UniversalTransition} from 'echarts/features';
import {CanvasRenderer} from 'echarts/renderers';
import {TitleComponent} from 'echarts/components';
import {BarChart} from 'echarts/charts';
import { TooltipComponent } from 'echarts/components';
import {pickerOptions} from "@/layout/mixin/PickerOptions";
echarts.use([GridComponent, LineChart, CanvasRenderer, UniversalTransition, TitleComponent, BarChart,TooltipComponent]);

export default {
  name: "ApiStatistics",

  mixins: [pickerOptions],

  data() {
    return {
      historyApiData: {},
      todayApiData: {},

      //遮罩层
      loading: false,

      historyApiLogParams: {
        startDate: null,
        endDate: null,
      },
      //检查查询范围
      daterangeCreateTime: [],
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
          textStyle: {
            color: '#541264',
            fontWeight: '1000',
            align: 'center',
          },
          left: "center",
        },
        tooltip: {},
        xAxis: {
          data: this.historyApiData.apiNames
        },
        yAxis: {
          splitNumber: 10,
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
          textStyle: {
            color: '#541264',
            fontWeight: '1000',
            align: 'center',
          },
          left: "center",
        },
        tooltip: {},
        xAxis: {
          data: this.todayApiData.apiNames
        },
        yAxis: {
          splitNumber: 10,
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
      this.loading = true
      getStatisticsTodayApi().then(res => {
        this.loading = false
        this.todayApiData = res.data
        this.initToday()
      }).catch(err => {
        this.loading = false
      })
    },

    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.historyApiLogParams.startDate = null
      this.historyApiLogParams.endDate = null
      this.handleQuery();
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getStatisticsTodayApi();
    },

    dateQuery() {
      //清空时间参数
      this.historyApiLogParams.startDate=null
      this.historyApiLogParams.endDate=null

      this.statisticsByDate();
    },

    statisticsByDate() {
      this.loading = true
      if (null != this.daterangeCreateTime && '' !== this.daterangeCreateTime) {
        this.historyApiLogParams.startDate = this.daterangeCreateTime[0];
        this.historyApiLogParams.endDate = this.daterangeCreateTime[1];
      }
      statisticsByDate(this.historyApiLogParams).then(res =>{
        this.loading = false
        this.todayApiData=res.data
        this.initToday()
      }).catch(err =>{
        this.loading = false
      })
    }
  },

}
</script>

<style scoped>

</style>
