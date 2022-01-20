<template>
  <div>
    <el-row>
      <el-col :span="24">
        <div class="top_col">
          <span style="cursor: crosshair">小工具</span>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="table_col">
          <div>
            <el-form label-width="80px" label-position="right">
              <el-form-item label="节假日" label-width="auto">
                <el-popover
                  placement="bottom"
                  width="400"
                  v-model="holidayVisible">

                  <div v-for="data in holidayData" v-loading="loading1">
                    <span>&nbsp;{{ data.holidayName }}&nbsp;</span>
                    <span>&nbsp;------&nbsp;</span>
                    <span>{{ data.returnDate }}</span>
                    <span>&nbsp;------&nbsp;</span>
                    <span>还剩&nbsp;<span style="color: red">{{ data.residueDays }}</span>&nbsp;天</span>
                    <el-divider><i class="el-icon-chat-round"></i></el-divider>
                  </div>
                  <el-button type="primary" icon="el-icon-search" size="mini" @click="getHoliday()" slot="reference">
                    搜索
                  </el-button>
                </el-popover>
              </el-form-item>
            </el-form>
          </div>
          <div>
            <el-form label-width="80px" label-position="right">
              <el-form-item label="MM图片" label-width="auto">

                <el-popover
                  placement="bottom"
                  width="988"
                  v-model="beautyPictureVisible">
                  <el-image v-for="data in BeautyPictureData" v-loading="loading2"
                            style="width: 192px; height: 108px"
                            :src="data.imageUrl"
                            :preview-src-list="pictureList">
                  </el-image>
                  <el-button type="primary" icon="el-icon-search" size="mini" @click="getBeautyPicture()"
                             slot="reference">搜索
                  </el-button>
                </el-popover>

              </el-form-item>
            </el-form>
          </div>

          <div>
            <el-form label-width="80px" label-position="right">
              <el-form-item label="历史今天" label-width="auto">

                <el-popover
                  placement="bottom"
                  width="988"
                  v-model="historyTodayVisible">

                  <el-button type="primary" icon="el-icon-search" @click="getHistoryToday()" size="mini"
                             slot="reference">搜索
                  </el-button>

                </el-popover>

              </el-form-item>
            </el-form>
          </div>
        </div>

        <div class="table_col2">
          <el-form :inline="true" class="">
            <el-form-item label="身份证查询" label-width="100px">
              <el-input placeholder="请输入身份证"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary">搜索</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="top_col">
          <span style="cursor: crosshair">实用工具</span>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="table2_col">

          <div class="table2_col_div">
            <el-form :inline="true" class="">
              <el-form-item label="手机归属地" label-width="100px">
                <el-input placeholder="请输入手机号码"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary">搜索</el-button>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" class="">
              <el-form-item label="实时天气" label-width="100px">
                <el-input placeholder="请输入城市名称"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary">搜索</el-button>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" class="">
              <el-form-item label="预报天气" label-width="100px">
                <el-input placeholder="请输入城市名称"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary">搜索</el-button>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" class="">
              <el-form-item label="垃圾分类" label-width="100px">
                <el-input placeholder="请输入垃圾名称"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary">搜索</el-button>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" class="">
              <el-form-item label="简繁转换" label-width="100px">
                <el-input placeholder="请输入简体中文"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary">搜索</el-button>
              </el-form-item>
            </el-form>
          </div>

          <div class="table2_col_div">
            <el-form :inline="true" class="">
              <el-form-item label="汉语字典" label-width="100px">
                <el-input placeholder="请输入单个中文"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary">搜索</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>

import {getHoliday, getHistoryToday, getBeautyPicture} from "@/api/business/openapi/apitools";

export default {
  name: "Apitools",

  data() {
    return {
      //--------------获取的数据--------------------
      holidayData: [],
      BeautyPictureData: [],
      pictureList: [],   //单独存放图片路径
      historyTodayData: [],


      //------------控制弹出显示隐藏-----------------
      holidayVisible: false,
      beautyPictureVisible: false,
      historyTodayVisible: false,


      //遮罩层
      loading1: false,
      loading2: false,


    }
  },

  created() {

  },

  methods: {
    //获取历史今天数据信息
    getHistoryToday() {
      getHistoryToday().then(res => {
        this.historyTodayData = res.data
      })
    },

    //获取节假日信息
    getHoliday() {
      this.loading1 = true
      getHoliday().then(res => {
        this.loading1 = false
        this.holidayData = res.data
      });
    },

    //获取mm图片信息
    getBeautyPicture() {
      this.loading2 = true
      this.pictureList = []
      getBeautyPicture().then(res => {
        this.loading2 = false
        this.BeautyPictureData = res.data
        //单独把图片放入数组
        res.data.forEach(data => {
          this.pictureList.push(data.imageUrl)
        })
      })
    },

  }

}
</script>

<style scoped>
.top_col {
  height: 40px;
  margin: 10px 10px;
  background-color: #bfcbd9;
  line-height: 40px;
  padding-left: 20px;
  border-radius: 17px;
  font-size: 13px;
}

.table_col {
  height: 120px;
  padding: 20px;
  display: flex;
  align-items: center;
  width: 64.5%;
  float: left;
}

.table_col2 {
  width: 35.5%;
  float: left;
  height: 120px;
  padding: 20px;
  display: flex;
  align-items: center;
}

.table_col div {
  float: left;
  width: 200px;
}


.table2_col {
  height: 135px;
  padding: 20px;
}


.table2_col_div {
  float: left;
  width: 33%;
}


</style>
