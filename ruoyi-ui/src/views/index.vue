<template>
  <div class="app-container">
    <el-row :gutter="5">
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card class="box-card" :body-style="{padding:'3px'}" shadow="hover">
            <div slot="header" style="font-size: 18px;color: #3A71A8;font-weight: 800;padding: 0">
              <span>最新微博热搜</span>
            </div>
            <div v-for="wb in WbDataList" :key="wb.id" style="color: #8492a6;">
              {{ wb.hotword }}
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-card class="box-card" :body-style="{padding:'3px'}" shadow="hover">
            <div slot="header" style="font-size: 18px;color: #00BCD4;font-weight: 800;padding: 0">
              <span>文案</span>
            </div>
            <div v-for="copyWriting in copyWritingList" :key="copyWriting.id" class="top_content"
                 style="color: #bfcbd9;">
              <el-tooltip effect="light" :content="copyWriting.content+' ——— '+copyWriting.type" placement="top">
                <span>{{ copyWriting.content }} ——— <span style="color: #bfcbc5">{{ copyWriting.type }}</span></span>
              </el-tooltip>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="5">
      <el-col :span="24">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
    <el-row :gutter="5">
      <el-col :span="24">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
    <el-row :gutter="5">
      <el-col :span="24">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>


import {showCopyWriting, showWbSearch} from "@/api";

export default {
  name: "Index",
  data() {
    return {
      WbDataList: {},
      copyWritingList: {},
    };
  },

  created() {
    this.showWbSearch()
    this.showCopyWriting()
  },

  mounted() {

  },


  methods: {
    showWbSearch() {
      showWbSearch().then(res => {
        this.WbDataList = res.data
      })
    },

    showCopyWriting() {
      showCopyWriting().then(res => {
        this.copyWritingList = res.data
      })
    },
  },


};
</script>

<style scoped lang="scss">

.top_content {
  white-space: nowrap; /*把文本强制显示在一行*/
  overflow: hidden; /*隐藏超出部分的文字*/
  text-overflow: ellipsis; /*超出显示省略号*/

  font-size: 14px;
  font-weight: 700;
}


.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>

