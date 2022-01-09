<template>
  <div>

    <el-row :gutter="20">
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <!--每日一句-->
          <div class="box-card">
            <div class="aWordImage">
              <img :src=apiAWord.imgurl class="aWordImage">
            </div>
            <div class="aWordAudio">
              <audio :src=apiAWord.tts controls="controls">
                您的浏览器不支持 audio 标签。
              </audio>
            </div>
            <div class="aWordContent">
              <span>{{ apiAWord.content }}</span>
            </div>
            <div class="aWordNote">
              <span>{{ apiAWord.note }}</span>
            </div>
          </div>
        </div>
      </el-col>

      <!--todo 天气预报    -->
      <el-col :span="12">
        <div class="grid-content bg-purple">

        </div>
      </el-col>
    </el-row>

    <el-row :gutter="20">
    </el-row>
  </div>
</template>

<script>

import {getApiAWord} from "@/api/index";

export default {
  name: "Index",
  data() {
    return {
      //响应数据
      apiAWord: {},

      //apiAWord请求参数
      apiAWordParams: {
        rand: 1
      }
    };
  },

  created() {
    this.getApiAWord();
  },

  methods: {
    //随机获取一条每日一句
    getApiAWord() {
      getApiAWord(this.apiAWordParams).then(res => {
        this.apiAWord = res.data
      })
    },
  },

};
</script>

<style scoped lang="scss">

.bg-purple {
  background: #d3dce6;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  height: 141px;
}

.box-card {
  width: 744px;
  background-color: #f2f2f2;
  height: 141px;
}

.box-card div {
  float: left;
}


.aWordImage {
  float: left;
  width: 108px;
  height: 141px;
}

.aWordAudio {
  float: left;
  height: 141px;
  width: 300px;
  /*flex 布局*/
  display: flex;
  /*实现垂直居中*/
  align-items: center;
  /*实现水平居中*/
  justify-content: center;
}

.aWordContent {
  float: left;
  width: 168px;
  height: 141px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  overflow:hidden;
}

.aWordNote {
  width: 168px;
  height: 141px;
  display: flex;
  align-items: center;
  justify-content: center;
  float: left;
  overflow:hidden;
}

</style>

