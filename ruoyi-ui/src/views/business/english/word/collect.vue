<template xmlns="http://www.w3.org/1999/html">
  <div class="sea_main_con test-5" @mouseenter="onMouseover" @mouseleave="onMouseout">
    <div class="infinite-list-wrapper">
      <ul
        class="list"
        v-infinite-scroll="loadMore"
        infinite-scroll-disabled="disabled"
        infinite-scroll-distance="20"
      >
        <el-row>
          <el-col :span="8" v-for="(say, index) in sayLists" :key="index" class="item-img">
            <el-card :body-style="{ padding: '0px' }">
              <div style="padding: 14px;">
                <span>

                  <el-tag type="info"
                          size="medium"
                  >英：{{ say.englishWord }}
                  </el-tag>
                </span>
                <div class="bottom clearfix">
                  <time class="time">
                    <el-tag type="warning"
                            size="medium"
                            color=""
                    >中：{{ say.chineseWord }}
                    </el-tag>
                  </time>
                  <el-button icon="el-icon-search" circle class="button"></el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
        <!--</li>-->
      </ul>
      <div class="load_icon">
        <p v-if="loading">加载中<i class="el-icon-loading"/></p>
        <p v-if="noMore">没有更多了~~~~</p>
      </div>
    </div>
  </div>
</template>

<script>
import {collectWord} from "@/api/business/english/word";

export default {
  data() {
    return {
      loading: false,
      seen: true,
      currentDate: new Date(),
      sayLists: [],
      // 每次请求回来的数据的个数
      everyList: 1,

      queryParams: {
        pageNum: 1,
        pageSize: 21
      },
    }
  },
  computed: {
    noMore() {
      return this.everyList < 1;
    },
    disabled() {
      return this.loading || this.noMore
    }
  },
  methods: {
    loadMore() {
      this.loading = true
      setTimeout(() => {
        // this.count += 2

        collectWord(this.queryParams).then((response) => {
          console.log(response.data)
          this.everyList = response.data.records.length;
          for (var i = 0; i < response.data.records.length; i++) {
            this.sayLists.push(response.data.records[i])
          }
        }, (error) => {

        })
        this.queryParams.pageNum += 1;
        this.loading = false
      }, 150)
    },
    onMouseover() {
      this.seen = true;
    },
    onMouseout() {
      this.seen = false;
    }

  }
}
</script>

<style scoped class="scss">

.item-img {
  margin-left: 60px;
  margin-top: 20px;
}

.sea_main_con {
  width: 1002px;
  height: 800px;
  border: 1px #b8b7b7 solid;
  overflow-y: scroll;
  border-radius: 4px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 2px;
  margin-bottom: 5px;
}

.test-5::-webkit-scrollbar {
  /*滚动条整体样式*/
  width: 10px; /*高宽分别对应横竖滚动条的尺寸*/
  height: 1px;
}

.test-5::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  border-radius: 10px;
  background-color: rgb(103, 194, 58);
  background-image: -webkit-linear-gradient(
    45deg,
    rgba(255, 255, 255, 0.2) 25%,
    transparent 25%,
    transparent 50%,
    rgba(255, 255, 255, 0.2) 50%,
    rgba(255, 255, 255, 0.2) 75%,
    transparent 75%,
    transparent
  );
}

.test-5::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: #ededed;
  border-radius: 10px;
}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.el-col-8 {
  width: 24.3%;
}

.load_icon {
  width: 100%;
  text-align: center;
}
</style>
