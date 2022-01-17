<template>
  <div class="navbar">

    <hamburger id="hamburger-container" :is-active="sidebar.opened" class="hamburger-container"
               @toggleClick="toggleSideBar"/>

    <breadcrumb id="breadcrumb-container" class="breadcrumb-container" v-if="!topNav"/>
    <top-nav id="topmenu-container" class="topmenu-container" v-if="topNav"/>

    <div class="right-menu">

      <!--天气-->
      <el-popover
        placement="top"
        width="400"
        title="❉预报天气❉"
        v-model="weatherVisible">
        <table style="text-align: center" v-loading="loading">
          <td v-for="(cast,index) in forecastWeatherData.casts" width="100px">
            <tr v-if="index===0">
              今天
            </tr>
            <tr v-if="index===1">
              明天
            </tr>
            <tr v-if="index===2">
              后天
            </tr>
            <tr v-if="index===3">
              大后天
            </tr>
            <tr>{{cast.dayweather}}</tr>
            <tr>
              {{cast.nighttemp+"℃~"+cast.daytemp+"℃"}}
            </tr>
            <tr v-if="cast.week==='1'">
              星期一
            </tr>
            <tr v-if="cast.week==='2'">
              星期二
            </tr>
            <tr v-if="cast.week==='3'">
              星期三
            </tr>
            <tr v-if="cast.week==='4'">
              星期四
            </tr>
            <tr v-if="cast.week==='5'">
              星期五
            </tr>
            <tr v-if="cast.week==='6'">
              星期六
            </tr>
            <tr v-if="cast.week==='7'">
              星期日
            </tr>
          </td>
        </table>

      <div class="right-menu-item weather" @click="getForecastWeather()" slot="reference">
        <img :src="weather" class="img">
        <span class="span1">
          {{ nowWeatherData.temperature + "℃" }}
        </span>
        <span class="span2">
          {{ nowWeatherData.weather }}
        </span>
      </div>
      </el-popover>


      <!--预警-->
      <el-badge :value="warnData.count" class=" hover-effect share-button" v-hasPermi="['warning:warning:handle']">
        <el-popover
          placement="bottom"
          width="220"
          v-model="visible">
          <p>{{ append() }}</p>
          <div style="text-align: right; margin: 0">
            <el-button type="primary" size="mini" @click="haveRead">已读</el-button>
          </div>
          <el-button type="info" icon="el-icon-check"
                     circle style="max-width: 22px;max-height: 22px;"
                     @click=""
                     slot="reference"
          ></el-button>
        </el-popover>
      </el-badge>

      <template v-if="device!=='mobile'">
        <search id="header-search" class="right-menu-item"/>

        <screenfull id="screenfull" class="right-menu-item hover-effect"/>

        <el-tooltip content="布局大小" effect="dark" placement="bottom">
          <size-select id="size-select" class="right-menu-item hover-effect"/>
        </el-tooltip>

      </template>

      <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar" class="user-avatar">
          <i class="el-icon-caret-bottom"/>
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/user/profile">
            <el-dropdown-item>个人中心</el-dropdown-item>
          </router-link>
          <el-dropdown-item @click.native="setting = true">
            <span>布局设置</span>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span>退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import TopNav from '@/components/TopNav'
import Hamburger from '@/components/Hamburger'
import Screenfull from '@/components/Screenfull'
import SizeSelect from '@/components/SizeSelect'
import Search from '@/components/HeaderSearch'
import RuoYiGit from '@/components/RuoYi/Git'
import RuoYiDoc from '@/components/RuoYi/Doc'
import {handleWarning} from "@/api/business/warning/apiwarning";
import {getNowWeather, getForecastWeather} from "@/api/business/openapi/weather";

import weather from "@/assets/icons/weather/天气.png"


export default {
  components: {
    Breadcrumb,
    TopNav,
    Hamburger,
    Screenfull,
    SizeSelect,
    Search,
    RuoYiGit,
    RuoYiDoc
  },

  data() {
    return {
      // 遮罩层
      loading: false,

      //预警数据
      warnData: {},

      //实时天气数据
      nowWeatherData: {},
      //预报天气数据
      forecastWeatherData: {},

      visible: false,
      weatherVisible:false,

      weather,
    }
  },

  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'device',
      "$socket",
    ]),
    setting: {
      get() {
        return this.$store.state.settings.showSettings
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'showSettings',
          value: val
        })
      }
    },
    topNav: {
      get() {
        return this.$store.state.settings.topNav
      }
    }
  },

  mounted() {
    this.$socket.registerCallBack(
      "apiWarning",
      this.getData
    );

    this.$bus.$on('clearCount', this.clearCount)
  },

  created() {
    this.getNowWeather()
  },

  methods: {
    //获取预报天气
    getForecastWeather() {
      this.loading = true;
      getForecastWeather().then(res => {
        this.forecastWeatherData = res.data
        this.loading = false;
      })

    },

    //获取实时天气
    getNowWeather() {
      getNowWeather().then(res => {
        this.nowWeatherData = res.data
      })
    },

    //小红点清零
    clearCount(data) {
      if (data) {
        this.warnData.count = 0
      }
    },

    //已读操作
    haveRead() {
      this.visible = false
      if (this.warnData) {
        let str = this.warnData.data;
        if (str) {
          var json = eval("(" + str + ")");
          if (json.id !== undefined) {
            handleWarning(json.id).then(res => {
              this.$modal.msgSuccess("处理成功");
              this.warnData.data = "{}"
              this.visible = false
              this.warnData.count = this.warnData.count - 1
            });
          }
        }
      }
    },

    append() {
      let str = this.warnData.data;
      if (str != null) {
        var json = eval("(" + str + ")");
        if (json.warningMessage !== undefined) {
          // this.visible = true
          return json.warningMessage
        }
      }
    },

    getData(data) {
      if (data) {
        this.warnData = data
      }
    },

    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          location.href = '/index';
        })
      }).catch(() => {
      });
    }
  },

  beforeDestroy() {
    // 在组件销毁的时候, 进行回调函数的取消
    this.$socket.unRegisterCallBack();
  },
}
</script>

<style lang="scss" scoped>
.weather {
  width: 120px;
  margin-right: 10px;
  cursor: pointer;
}

.weather img {
  margin-top: 10px;
  margin-left: 5px;
  width: 30px;
  height: 30px;
  float: left;
}

.span1 {
  float: left;
  margin-left: 5px;
  font-size: 14px;
}

.span2 {
  float: left;
  margin-left: 10px;
  font-size: 14px;
}


.share-button {
  margin-right: 5px;
  color: #5a5e66;
  padding-bottom: 23px;

  &.hover-effect {
    cursor: pointer;
    transition: background .3s;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

}

.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, .08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .topmenu-container {
    position: absolute;
    left: 50px;
  }

  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
