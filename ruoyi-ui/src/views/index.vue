<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="12">
        <div>
          <el-card shadow="hover" class="card" ref="renderersChart">

          </el-card>
        </div>
      </el-col>
      <el-col :span="12">
        <div>
          <el-card shadow="hover" class="card">

          </el-card>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="12">
        <div>
          <el-card shadow="hover" class="card">

          </el-card>
        </div>
      </el-col>
      <el-col :span="12">
        <div>
          <el-card shadow="hover" class="card">

          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>

// 引入echarts
var echarts = require('echarts/lib/echarts');
require('echarts/lib/component/tooltip');
require('echarts/lib/chart/gauge');
require('echarts/lib/component/title');

export default {
  name: "Index",
  data() {
    return {};
  },

  created() {

  },

  mounted() {
    this.initRenderers();
    },


  methods: {
    initRenderers() {
      var myDate = new Date();
      var s = myDate.getSeconds();
      let time = myDate.toLocaleTimeString();
      let renderersChart = echarts.init(this.$refs.renderersChart.$el)
      renderersChart.setOption({
        tooltip: {
          formatter: '单位：{a} <br/>当前 : {c}s'
        },
        title: {
          text: time,
          textStyle:{
            color: '#541264',
            fontWeight:'1000',
            align:'center',
          },
          left:"center",
        },
        series: [
          {
            name: '秒',
            type: 'gauge',
            progress: {
              show: true
            },
            detail: {
              valueAnimation: true,
              formatter: '{value}'
            },
            data: [
              {
                value: s,
                name: '单位：秒',
              }
            ],
            min: 0,
            max: 60,
            splitNumber: 6,
          }
        ]
      })

      //定时获取秒数定时执行
      setInterval(function () {
        let myDate = new Date();
        let s = myDate.getSeconds();
        let time = myDate.toLocaleTimeString();
        renderersChart.setOption({
          title: {
            text: time,
          },
          series: [
            {
              data: [
                {
                  value: s,
                  name: '单位：秒',
                }
              ]
            },
          ]
        });
      }, 1000);

    },

  },




};
</script>

<style scoped lang="scss">
.card {
  height: 410px;
  margin: 0 auto;
}


</style>

