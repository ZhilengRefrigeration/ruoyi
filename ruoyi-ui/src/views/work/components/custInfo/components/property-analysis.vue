<template>
  <div class="pro-ana-wrap">
    <p class="word-box">康希诺生物股份公司的整体知识产权共<span>279</span>件，其中专利<span>89</span>件，商标<span>62</span>件，软件著作量<span>32</span>件，作品著作量<span>96</span>件，ICP<span>0</span>件。康希诺生物股份公司的专利中发明公布为<span>50</span>件，发明授权为<span>28</span>件，实用新型为<span>10</span>件，外观设计为<span>1</span>件。</p>
    <el-row>
      <el-col :span="12">
        <h3 class="pro-title">知识产权总申请量</h3>
        <echarts-3d ref="Echarts3d"></echarts-3d>
      </el-col>
      <el-col :span="12">
        <h3 class="pro-title">专利类型分析</h3>
        <div ref="secondChart" class="second-chart"></div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div ref="thirdChart" class="second-chart"></div>
      </el-col>
      <el-col :span="12">
        <div ref="forthChart" class="second-chart"></div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import Echarts3d from '../../../../components/echarts-3d.vue'
import echarts from 'echarts'
export default {
  name: 'propertyAnalysis',
  components: {
    Echarts3d
  },
  mounted () {
    this.initSecond()
    this.initThird()
    this.initForth()
  },
  methods: {
    initSecond () {
      let secondOption = {
        color: ['#73C4F7', '#7BDDD0', '#FAC858', '#5DA6FC', '#CCCCCC'],
        tooltip: {
          trigger: 'axis',
        },
        legend: {
          data: ['发明公布', '发明授权', '实用新型', '外观设计'],
          icon: "circle",
          itemHeight: 8
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true,
          borderColor: '#DDD'
        },
        xAxis: {
          type: 'value',
          boundaryGap: [0, 0.01],
          axisLine: {
            lineStyle: {
              color: 'transparent'
            }
          },
          axisLabel: {
            color: '#555'
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#f5f5f5',//网格线的颜色
              width: 1,
              type: 'dashed'
            }
          },
          axisTick: {
            alignWithLabel: true
          }
        },
        yAxis: {
          type: 'category',
          data: ['发明公布', '发明授权', '实用新型', '外观设计'],
          axisLine: {
            lineStyle: {
              color: '#ddd'
            }
          },
          axisLabel: {
            color: '#222'
          },
          axisTick: {
            alignWithLabel: true
          }
        },
        series: [
          {
            name: '发明公布',
            data: [12, 0, 0, 0],
            type: 'bar',
            barWidth: 16,
            stack: 'total',
            label: {
              show: true,
              position: 'right'
            },
            itemStyle: {
              barBorderRadius: [0, 3, 3, 0],
            }
          },
          {
            name: '发明授权',
            data: [0, 20, 0, 0],
            type: 'bar',
            barWidth: 16,
            stack: 'total',
            label: {
              show: true,
              position: 'right'
            },
            itemStyle: {
              barBorderRadius: [0, 3, 3, 0],
            }
          },
          {
            name: '实用新型',
            data: [0, 0, 15, 0],
            type: 'bar',
            barWidth: 16,
            stack: 'total',
            label: {
              show: true,
              position: 'right'
            },
            itemStyle: {
              barBorderRadius: [0, 3, 3, 0],
            }
          },
          {
            name: '外观设计',
            data: [0, 0, 0, 80],
            type: 'bar',
            barWidth: 16,
            stack: 'total',
            label: {
              show: true,
              position: 'right'
            },
            itemStyle: {
              barBorderRadius: [0, 3, 3, 0],
            }
          }
        ]
      };
      let chart2 = echarts.init(this.$refs.secondChart)
      chart2.setOption(secondOption)
      window.addEventListener('resize', () => {
        if (chart2) {
          chart2.resize()
        }
      })
    },
    initThird () {
      let thirdOption = {
        color: ['#5DA6FC'],
        tooltip: {
          trigger: 'axis',
          // axisPointer: {
          //   type: 'shadow',
          //   shadowStyle: {
          //     color: 'rgba(123,221,208,0.05)',
          //   }
          // }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true,
          borderColor: '#eee'
        },
        yAxis: {
          type: 'value',
          boundaryGap: [0, 0.01],
          axisLine: {
            lineStyle: {
              color: 'transparent'
            }
          },
          axisLabel: {
            color: '#999'
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#eee',//网格线的颜色
              width: 1,
              type: 'dashed'
            }
          },
          axisTick: {
            alignWithLabel: true
          }
        },
        xAxis: [{
          type: 'category',
          data: ['法人风险实体', '股东风险实体', '子公司风险实体', '关联人','法人风险实体', '股东风险实体', '子公司风险实体', '关联人'],
          axisLine: {
            lineStyle: {
              color: '#ddd'
            }
          },
          axisLabel: {
            color: '#999',
            rotate: 45
          },
          axisTick: {
            alignWithLabel: true
          }
        }],
        series: [
          {
            data: [12, 23, 34, 45,12, 23, 34, 45],
            type: 'bar',
            barWidth: 24,
            itemStyle: {
              barBorderRadius: [4, 4, 0, 0],
            }
          },
        ]
      };
      let chart3 = echarts.init(this.$refs.thirdChart)
      chart3.setOption(thirdOption)
      window.addEventListener('resize', () => {
        if (chart3) {
          chart3.resize()
        }
      })
    },
    initForth () {
      let forthOption = {
        color: ['#5DA6FC'],
        tooltip: {
          trigger: 'axis',
          // axisPointer: {
          //   type: 'shadow',
          //   shadowStyle: {
          //     color: 'rgba(123,221,208,0.05)',
          //   }
          // }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true,
          borderColor: '#eee'
        },
        yAxis: {
          type: 'value',
          boundaryGap: [0, 0.01],
          axisLine: {
            lineStyle: {
              color: 'transparent'
            }
          },
          axisLabel: {
            color: '#999'
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#eee',//网格线的颜色
              width: 1,
              type: 'dashed'
            }
          },
          axisTick: {
            alignWithLabel: true
          }
        },
        xAxis: [{
          type: 'category',
          data: ['法人风险实体', '股东风险实体', '子公司风险实体', '关联人','法人风险实体', '股东风险实体', '子公司风险实体', '关联人', '子公司风险实体', '关联人'],
          axisLine: {
            lineStyle: {
              color: '#ddd'
            }
          },
          axisLabel: {
            color: '#999',
            rotate: 45
          },
          axisTick: {
            alignWithLabel: true
          }
        }],
        series: [
          {
            data: [12, 23, 34, 45,12, 23, 34, 45, 34, 45],
            type: 'bar',
            barWidth: 12,
            itemStyle: {
              barBorderRadius: [13, 13, 0, 0],
            }
          },
        ]
      };
      let chart4 = echarts.init(this.$refs.forthChart)
      chart4.setOption(forthOption)
      window.addEventListener('resize', () => {
        if (chart4) {
          chart4.resize()
        }
      })
    },
  }
}
</script>
<style lang="scss">
.pro-ana-wrap {
  background-image: url(./bg.png);
  background-size: cover;
  font-family: SourceHanSansCN-Regular, SourceHanSansCN;
  .word-box{
    color:#888;
    margin-top:30px;
    margin-bottom:68px;
    line-height: 22px;
    span{
      color:#222;
    }
  }
  .pro-title {
    font-weight: bold;
    &::before{
      width: 4px;
      height: 14px;
      display: inline-block;
      background: #096DD9;
      border-radius: 2px;
      margin-right:10px;
      vertical-align: top;
      content: ''
    }
  }
  .second-chart{
    width:auto;
    height:300px;
  }
}
</style>