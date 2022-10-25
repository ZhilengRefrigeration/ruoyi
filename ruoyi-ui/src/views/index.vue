<template>
  <div class="app-container home-page">
    <PageTabs
      class="alive-tabs"
      :tabdate="tabdate">
    </PageTabs>
    <div class="tabs-content">
        <el-row>
          <el-col :span="5">
            <div style="background-color: #00afff;height: 90%">

            </div>
          </el-col>
          <el-col :span="19">

          </el-col>
        </el-row>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import PageTabs from '@/components/PageTabs'

export default {
  name: 'home',
  components: {
    PageTabs
  },
  data() {
    return {
      tabdate:{
        tabname:['待办事项','已办事项','通知与提醒','统计与分析'],
        activecontent:0
      },
    }
  },
  computed: {
    ...mapGetters({
      codeCombo: 'getCodeCombo',
      permissionList: 'getPermission',
    }),
  },
  activated() {
  },
  created() {
    console.log('用户基本信息', sessionStorage.getItem('userInfo'))
    console.log('缓存', JSON.stringify(sessionStorage.sessionObj))
    let roleType =
      sessionStorage.getItem('userInfo') && JSON.parse(sessionStorage.getItem('userInfo')).roleType
        ? JSON.parse(sessionStorage.getItem('userInfo')).roleType
        : ''
    this.isManager = roleType == '90040005'
    let orgId = sessionStorage.getItem('userInfo') && JSON.parse(sessionStorage.getItem('userInfo')).orgId
  },
  methods: {
    getHomeInfo(orgId) {
      let apiUrl = this.$api.main.getOrgOverViewInfo

      this.$http.post(apiUrl, { orgId }).then(res => {
        if (res.data.code == 200) {
          let data = res.data.data
          let { overViewDets, pieMap } = data
          this.overViewDets = overViewDets
          this.pieMap = pieMap
          this.loadFlag = true
        }
      })
    },
    getSetInfo(orgId) {
      this.$http.post(this.$api.main.getOrgPositionInfo, { orgId }).then(res => {
        if (res.data.code == 200) {
          this.setInfo = res.data.data || {}
          this.loadSetInfo = true
        } else {
        }
      })
    },
  },
}
</script>

<style lang="scss" scoped>
.home-page {
  width: 100%;
  height: calc(100% - 0.3rem);
}
.tabs-content{
  width: 100%;
  height: calc(100vh - 219px);
  background-color: #ffffff;
  box-shadow: 0px 1px 8px 0px rgba(0,0,0,0.1);
  border-radius: 6px;
  padding: 24px;
}

</style>
