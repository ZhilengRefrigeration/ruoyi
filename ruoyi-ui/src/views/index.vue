<template>
  <div class="app-container" style="padding-top: 0">
    <el-row :gutter="5">
      <el-col :span="4">
        <div>
          <el-card shadow="hover" :body-style="{padding:'3px'}">
            <router-link :to="'/business/logs/apilog/'">
              <div class="board">
                <div class="top_board">API数</div>
                <div class="content_board">今日:<span class="num_class">{{ logCount.apiLog.todayNumber }}</span></div>
                <div class="content_board">总计:<span class="num_class">{{ logCount.apiLog.total }}</span></div>
              </div>
            </router-link>
          </el-card>
        </div>
      </el-col>

      <el-col :span="4">
        <div>
          <el-card shadow="hover" :body-style="{padding:'3px'}">
            <router-link :to="'/business/logs/reptileLog/'">
              <div class="board">
                <div class="top_board">爬虫数</div>
                <div class="content_board">今日:<span class="num_class">{{ logCount.webmagicLog.todayNumber }}</span>
                </div>
                <div class="content_board">总计:<span class="num_class">{{ logCount.webmagicLog.total }}</span></div>
              </div>
            </router-link>
          </el-card>
        </div>
      </el-col>

      <el-col :span="4">
        <div>
          <el-card shadow="hover" :body-style="{padding:'3px'}">
            <router-link :to="'/business/logs/taskLog/'">
              <div class="board">
                <div class="top_board">任务数</div>
                <div class="content_board">今日:<span class="num_class">{{ logCount.taskLog.todayNumber }}</span></div>
                <div class="content_board">总计:<span class="num_class">{{ logCount.taskLog.total }}</span></div>
              </div>
            </router-link>
          </el-card>
        </div>
      </el-col>

      <el-col :span="4">
        <div>
          <el-card shadow="hover" :body-style="{padding:'3px'}">
            <router-link :to="'/business/logs/maillog/'">
              <div class="board">
                <div class="top_board">邮件数</div>
                <div class="content_board">今日:<span class="num_class">{{ logCount.mailLog.todayNumber }}</span></div>
                <div class="content_board">总计:<span class="num_class">{{ logCount.mailLog.total }}</span></div>
              </div>
            </router-link>
          </el-card>
        </div>
      </el-col>

      <el-col :span="4">
        <div>
          <el-card shadow="hover" :body-style="{padding:'3px'}">
            <router-link :to="'/user/profile/'">
              <div class="board">
                <div class="top_board">登录数</div>
                <div class="content_board"><span v-html="'&nbsp'"></span></div>
                <div class="content_board">总计:<span class="num_class">{{ loginCount }}</span></div>
              </div>
            </router-link>
          </el-card>
        </div>
      </el-col>
      <el-col :span="4">
        <div>
          <el-card shadow="hover" :body-style="{padding:'3px'}">
              <div class="board">
                <div class="top_board">IP信息</div>
                <div class="content_board">IP:<span class="num_class">{{ ipInfo.ip }}</span></div>
                <div class="content_board">归属地:<span class="num_class">{{ ipInfo.desc }}</span></div>
              </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="5">
      <el-col :span="6">
        <div>
          <el-card class="box-card" :body-style="{padding:'3px'}" shadow="hover">
            <div slot="header" style="font-size: 18px;color: #3A71A8;font-weight: 800;padding: 0">
              <span>最新微博热搜</span>
            </div>
            <div v-for="wb in WbDataList" :key="wb.id" style="color: #8492a6;" class="top_content">
              {{ wb.hotword }}
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="18">
        <div>
          <el-card class="box-card" :body-style="{padding:'3px'}" shadow="hover">
            <div slot="header" style="font-size: 18px;color: #00BCD4;font-weight: 800;padding: 0">
              <span>文案</span>
            </div>
            <div v-for="copyWriting in copyWritingList" :key="copyWriting.id" class="top_content"
                 style="color: #bfcbd9;">
              <el-tooltip effect="light" :content="copyWriting.content+' ——— '+copyWriting.type" placement="top"
                          :enterable="false">
                <span>{{ copyWriting.content }} ——— <span style="color: #bfcbc5">{{ copyWriting.type }}</span></span>
              </el-tooltip>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="5">
      <el-col :span="24">
        <div>
          <el-card class="box-card" :body-style="{padding:'3px'}" shadow="hover">
            <div slot="header" style="font-size: 18px;color: #7a6df0;font-weight: 800;padding: 0">
              <span>网易云热评</span>
            </div>
            <div v-for="yun in yunList" :key="yun.id" class="top_content" style="color: #999999;">
              <el-tooltip effect="light" :content="yun.content+' ——— '+yun.source" placement="top" :enterable="false">
                <span>{{ yun.content }} ——— <span style="color: #bfcbc5">{{ yun.source }}</span></span>
              </el-tooltip>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="5">
      <el-col :span="13">
        <div>
          <el-card class="box-card" :body-style="{padding:'3px'}" shadow="hover">
            <div slot="header" style="font-size: 18px;color: #ffba00;font-weight: 800;padding: 0">
              <span>最新操作日志</span>
            </div>
            <el-table
              :data="operLogTableData"
              style="width: 100%"
              :row-style="{height: '0'}"
              :cell-style="{padding: '0'}"
              border
            >
              <el-table-column prop="title" label="系统模块" align="center" :show-overflow-tooltip="true"/>
              <el-table-column prop="businessType" label="操作类型" align="center" :show-overflow-tooltip="true">
                <template slot-scope="scope">
                  <dict-tag :options="dict.type.sys_oper_type" :value="scope.row.businessType"/>
                </template>
              </el-table-column>
              <el-table-column prop="requestMethod" label="请求方式" align="center" :show-overflow-tooltip="true"/>
              <el-table-column prop="operName" label="操作人员" align="center" :show-overflow-tooltip="true"/>
              <el-table-column label="主机" align="center" prop="operIp" :show-overflow-tooltip="true"/>
            </el-table>
          </el-card>
        </div>
      </el-col>

      <el-col :span="11">
        <el-card class="box-card" :body-style="{padding:'3px'}" shadow="hover">
          <div slot="header" style="font-size: 18px;color: #ff4949;font-weight: 800;padding: 0">
            <span>英语单词</span>
          </div>
          <el-table
            :data="englishWordTableData"
            style="width: 100%"
            :row-style="{height: '0'}"
            :cell-style="{padding: '0'}"
            border
          >
            <el-table-column prop="englishWord" label="英语单词" align="center" :show-overflow-tooltip="true"/>
            <el-table-column prop="chineseWord" label="中文翻译" align="center" :show-overflow-tooltip="true">
              <template slot-scope="scope">
                <el-tag type="success">{{ scope.row.chineseWord }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="lookCount" label="查看次数" align="center" :show-overflow-tooltip="true"/>
            <el-table-column prop="createTime" label="创建时间" align="center" :show-overflow-tooltip="true"/>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="5">
      <el-col :span="12">
        <div>
          <el-card class="box-card" :body-style="{padding:'3px'}" shadow="hover">
            <div slot="header" style="font-size: 18px;color: #BD2828;font-weight: 800;padding: 0">
              <span>最新国内新闻</span>
            </div>
            <div v-for="internalNews in internalNewsList" :key="internalNews.title" class="top_content"
                 style="color: #666666;">
              <span @click="toLookNews(internalNews.url)" style="cursor: pointer">{{ internalNews.title }}</span>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="12">
        <div>
          <el-card class="box-card" :body-style="{padding:'3px'}" shadow="hover">
            <div slot="header" style="font-size: 18px;color: #3d8610;font-weight: 800;padding: 0">
              <span>最新国际新闻</span>
            </div>
            <div v-for="internationalNews in internationalNewsList" :key="internationalNews.title" class="top_content"
                 style="color: #324157;">
              <span @click="toLookNews(internationalNews.url)" style="cursor: pointer">{{
                  internationalNews.title
                }}</span>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="5">
      <el-col :span="8">
        <div>
          <el-card class="box-card" :body-style="{padding:'3px'}" shadow="hover">
            <div slot="header" style="font-size: 18px;color: #1482f0;font-weight: 800;padding: 0">
              <span>美图鉴赏-1</span>
            </div>
            <el-carousel :interval="1500" type="card" height="132px">
              <el-carousel-item v-for="pic in beautyPictureList1" :key="pic">
                <el-image
                  style="width: 234px; height: 132px"
                  :src="pic"
                  :preview-src-list="beautyPictureList1">
                </el-image>
              </el-carousel-item>
            </el-carousel>
          </el-card>
        </div>
      </el-col>

      <el-col :span="8">
        <div>
          <el-card class="box-card" :body-style="{padding:'3px'}" shadow="hover">
            <div slot="header" style="font-size: 18px;color: #1482f0;font-weight: 800;padding: 0">
              <span>美图鉴赏-2</span>
            </div>
            <el-carousel :interval="1500" type="card" height="132px">
              <el-carousel-item v-for="pic in beautyPictureList2" :key="pic">
                <el-image
                  style="width: 234px; height: 132px"
                  :src="pic"
                  :preview-src-list="beautyPictureList2">
                </el-image>
              </el-carousel-item>
            </el-carousel>
          </el-card>
        </div>
      </el-col>

      <el-col :span="8">
        <div>
          <el-card class="box-card" :body-style="{padding:'3px'}" shadow="hover">
            <div slot="header" style="font-size: 18px;color: #1482f0;font-weight: 800;padding: 0">
              <span>美图鉴赏-3</span>
            </div>
            <el-carousel :interval="1500" type="card" height="132px">
              <el-carousel-item v-for="pic in beautyPictureList3" :key="pic">
                <el-image
                  style="width: 234px; height: 132px"
                  :src="pic"
                  :preview-src-list="beautyPictureList3">
                </el-image>
              </el-carousel-item>
            </el-carousel>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import {showData} from "@/api";

export default {
  name: "Index",
  dicts: ['sys_oper_type'],
  data() {
    return {
      WbDataList: [],
      copyWritingList: [],
      yunList: [],
      logCount: {
        apiLog: {
          todayNumber: 0,
          total: 0
        },
        webmagicLog: {
          todayNumber: 0,
          total: 0
        },
        taskLog: {
          todayNumber: 0,
          total: 0
        },
        mailLog: {
          todayNumber: 0,
          total: 0
        }
      },
      loginCount: 0,

      operLogTableData: [],
      englishWordTableData: [],

      internalNewsList: [],
      internationalNewsList: [],
      beautyPictureList1: [],
      beautyPictureList2: [],
      beautyPictureList3: [],

      ipInfo:{},


    };
  },

  created() {
    this.showData()
  },

  mounted() {

  },


  methods: {
    toLookNews(url) {
      window.open(url)
    },

    showData() {
      this.$modal.loading("请稍后...")
      showData().then(res => {
        this.WbDataList = res.data.weiboList
        this.copyWritingList = res.data.networkDTOList
        this.yunList = res.data.yunList
        this.logCount = res.data.logCount
        this.loginCount = res.data.loginCount
        this.operLogTableData = res.data.sysOperLog
        this.englishWordTableData = res.data.englishWord
        this.internalNewsList = res.data.news.internal
        this.internationalNewsList = res.data.news.international
        this.beautyPictureList1 = res.data.beautyPicture.one
        this.beautyPictureList2 = res.data.beautyPicture.two
        this.beautyPictureList3 = res.data.beautyPicture.three
        this.ipInfo = res.data.ipInfo

        this.$modal.closeLoading()
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
  font-weight: 600;
  padding-left: 10px;
}

.top_board {
  font-weight: 800;
  font-size: 20px;
  color: #0072c6;
}

.board {
  width: 100%;
  text-align: center;
  cursor: pointer;
}

.content_board {
  color: #999093;
  font-weight: 600;
}

.num_class {
  color: #7171C6;
}

</style>

