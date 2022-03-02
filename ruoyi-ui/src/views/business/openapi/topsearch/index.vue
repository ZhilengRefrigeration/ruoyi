<template>

  <div style="padding: 10px" v-loading="loading">

    <div style="width: 100%;">
      <el-form :inline="true">
        <el-form-item>
          <el-date-picker
            v-model="dateValue"
            align="left"
            type="date"
            :clearable=false
            placeholder="选择日期"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd"
            @change="getHistoryTopSearch"
            :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="" @click="getTopsearch">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div>
      <el-collapse @change="handleChange">
        <el-collapse-item class="el-collapse-item" title="抖音热搜榜" name="1">
          <el-card shadow="hover">
            <div v-for="douyin in topsearchList.douyinList" :key="douyin.id" class="douyin">
              <span>{{ douyin.word }}</span>
              <el-divider direction="vertical"></el-divider>
              <span style="color: red;margin-left: 25px">{{ douyin.label }}</span>
              <span style="font-size: 13px;font-weight: 500;margin-left: 25px;float: right">{{
                  douyin.createTime
                }}</span>
              <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
            </div>
          </el-card>
        </el-collapse-item>
        <el-collapse-item class="el-collapse-item" title="微博热搜榜" name="2">
          <el-card shadow="hover">
            <div v-for="weibo in topsearchList.weiboList" :key="weibo.id" class="douyin">
              <span>{{ weibo.hotword }}</span>
              <el-divider direction="vertical"></el-divider>
              <span style="color: red;margin-left: 25px">{{ weibo.hottag }}</span>
              <span style="font-size: 13px;font-weight: 500;margin-left: 25px;float: right">{{
                  weibo.createTime
                }}</span>
              <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
            </div>
          </el-card>
        </el-collapse-item>
        <el-collapse-item class="el-collapse-item" title="全网热搜榜" name="3">
          <el-card shadow="hover">
            <div v-for="allnetwork in topsearchList.allnetworkList" :key="allnetwork.id" class="allnetwork">
              <div>
                <h3 style="font-weight: 800">{{ allnetwork.title }}</h3>
              </div>
              <div>
                <span>{{ allnetwork.digest }}</span>
                <span style="font-size: 13px;font-weight: 500;margin-left: 25px;float: right">{{
                    allnetwork.createTime
                  }}</span>
              </div>
              <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
            </div>
          </el-card>
        </el-collapse-item>
        <el-collapse-item class="el-collapse-item" title="百度热搜榜" name="4">
          <el-card shadow="hover">
            <div v-for="baidu in topsearchList.baiduList" :key="baidu.id" class="baidu">
              <div>
                <h3 style="font-weight: 800">{{ baidu.title }}</h3>
              </div>
              <div>
                <span>{{ baidu.digest }}</span>
                <el-divider direction="vertical"></el-divider>
                <span style="color: red;margin-left: 25px">{{ baidu.trend }}</span>
                <span style="font-size: 13px;font-weight: 500;margin-left: 25px;float: right">{{
                    baidu.createTime
                  }}</span>
              </div>
              <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
            </div>
          </el-card>
        </el-collapse-item>
        <el-collapse-item class="el-collapse-item" title="微信热搜榜" name="5">
          <el-card shadow="hover">
            <div v-for="wechat in topsearchList.wechatList" :key="wechat.id" class="douyin">
              <span>{{ wechat.word }}</span>
              <span style="font-size: 13px;font-weight: 500;margin-left: 25px;float: right">{{
                  wechat.createTime
                }}</span>\
              <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
            </div>
          </el-card>
        </el-collapse-item>
      </el-collapse>
    </div>


    <!--  回到顶部-->
    <el-backtop target="" :bottom="100">
    </el-backtop>

  </div>

</template>

<script>

import {getTopsearch, getHistoryTopSearch} from "@/api/business/openapi/topsearch";

export default {
  name: "Topsearch",
  data() {
    return {
      //遮罩层
      loading: true,

      //各个平台热搜数据
      topsearchList: {},

      //默认打开哪个
      activeNames: ['1'],


      //日期组件
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      },

      dateValue: '',
    };
  },
  created() {
    this.getTopsearch()
  },


  methods: {
    //获取历史热搜榜
    getHistoryTopSearch() {
      this.topsearchList = {}
      this.loading = true
      getHistoryTopSearch(this.dateValue).then(res => {
        this.loading = false
        this.topsearchList = res.data
      })
    },

    //获取热搜榜
    getTopsearch() {
      this.loading = true
      this.dateValue = null

      getTopsearch().then(res => {
        this.topsearchList = res.data
        this.loading = false
      })
    },


    handleChange(val) {
    }
  },
}
</script>

<style scoped>
.el-collapse-item {
  /*text-align: center;*/
}

.douyin {
  margin-bottom: 20px;
  font-size: 15px;
  font-weight: 800;
}

.allnetwork {
  margin-bottom: 20px;
  font-size: 15px;
  margin-left: 5px;
}
</style>
