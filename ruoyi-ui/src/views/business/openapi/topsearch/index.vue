<template>

  <div style="padding: 10px" v-loading="loading">
    <el-collapse  @change="handleChange">
      <el-collapse-item class="el-collapse-item" title="抖音热搜榜" name="1">
        <el-card shadow="hover">
          <div v-for="douyin in topsearchList.douyinList" :key="douyin.id" class="douyin">
            <span>{{ douyin.word }}</span>
            <el-divider direction="vertical"></el-divider>
            <span style="color: red;margin-left: 25px">{{ douyin.label }}</span>
          </div>
        </el-card>
      </el-collapse-item>
      <el-collapse-item class="el-collapse-item" title="微博热搜榜" name="2">
        <el-card shadow="hover">
          <div v-for="weibo in topsearchList.weiboList" :key="weibo.id" class="douyin">
            <span>{{ weibo.hotword }}</span>
            <el-divider direction="vertical"></el-divider>
            <span style="color: red;margin-left: 25px">{{ weibo.hottag }}</span>
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
            </div>
            <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
          </div>
        </el-card>
      </el-collapse-item>
      <el-collapse-item class="el-collapse-item" title="微信热搜榜" name="5">
        <el-card shadow="hover">
          <div v-for="wechat in topsearchList.wechatList" :key="wechat.id" class="douyin">
            <span>{{ wechat.word }}</span>
          </div>
        </el-card>
      </el-collapse-item>
    </el-collapse>


    <!--  回到顶部-->
    <el-backtop target="" :bottom="100">
    </el-backtop>

  </div>

</template>

<script>

import {getTopsearch} from "@/api/business/openapi/topsearch";

export default {
  name: "Topsearch",
  data() {
    return {
      //遮罩层
      loading: true,

      //各个平台热搜数据
      topsearchList: {},

      //默认打开哪个
      activeNames: ['1']
    };
  },
  created() {
    this.getTopsearch()
  },


  methods: {
    //获取热搜榜
    getTopsearch() {
      this.loading = true
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
  margin-left: 25px;
}
</style>
