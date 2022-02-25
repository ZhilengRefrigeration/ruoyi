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
            <!--logo -->
            <div style="width: 500px;margin: 0 auto;margin-top: 20px">
              <el-image
                style="width: 272px; height: 72px;margin-left: 120px"
                :src="baiduLogo"
              ></el-image>
            </div>

            <!--输入框 -->
            <div style="margin-top: 30px">
              <el-autocomplete
                style="width: 92%"
                v-model="searchContent"
                @input="getAssociation()"
                @select="handleSelect"
                :fetch-suggestions="querySearchAsync"
                placeholder="请输入你想要搜索的内容"></el-autocomplete>
              <el-button
                style="width: 8%"

                type="primary" icon="el-icon-search" @click="toRescue"></el-button>
            </div>


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

import baiduLogo from "@/assets/images/baidu_logo.png"
import {getAssociation} from "@/api/business/openapi/ai";


export default {
  name: "Index",
  data() {
    return {
      baiduLogo,

      //百度输入框内容
      searchContent: null,

      //联想词汇
      associationList: [],

    };
  },

  created() {

  },

  mounted() {
    this.initRenderers();
  },


  methods: {
    //获取echarts图
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
          textStyle: {
            color: '#541264',
            fontWeight: '1000',
            align: 'center',
          },
          left: "center",
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

    //获取联想词汇
    getAssociation() {
      if (this.searchContent === '' || this.searchContent === null || this.searchContent === undefined) {
        return
      }
      let data = {
        content: this.searchContent,
      };
      getAssociation(data).then(res => {
        this.associationList = res.data
      })
    },

    querySearchAsync(queryString, cb) {

      let list = this.handleAssociationList(this.associationList);

      cb(list);

    },

    /**
     * 处理返回的list
     * @param restaurants
     */
    handleAssociationList(restaurants) {

      let list = []

      restaurants.forEach(s => {
        let obj = {};
        let key = "value"
        var value = s
        obj[key] = value
        list.push(obj)
      })

      return list;
    },

    //跳转到外部链接
    toRescue() {
      // window.location.href = 'https://www.baidu.com/s?wd=' + this.searchContent
      window.open('https://www.baidu.com/s?wd='+ this.searchContent)
    },

    handleSelect(item) {
      console.log(item);
    },
  },


};
</script>

<style scoped lang="scss">
.card {
  width: 100%;
  height: 410px;
  margin: 0 auto;
}


</style>

