<template>
  <div class="header">
    <div class="banner-box">
      <img src="../assets/img/banner/head_bank_logo.png" alt="" class="bank_logo" />
      <div class="header-name">
        <span class="bigcharacter-logo">天津银行公司业务智能作业系统</span>
        <span class="ismallcharacter-logo">BUSINESS INTELLIGENCE OPERATING SYSTEM OF BANK OF TIANJIN</span>
      </div>
      <!-- <div class="model_wrap">
        <div class="banner-model">
          <el-badge :is-dot="getDaRankNewStatus" class="banner-model spc_dot">
            <div @click="toRbRank" class="model-item">
              <img src="../assets/img/banner/red_black.png" class="banner-icon" />
              <p>红黑榜</p>
            </div>
          </el-badge>
        </div>
        <el-badge :is-dot="listNewStatus" class="banner-model">
          <div @click="toNewProd" class="model-item">
            <img src="../assets/img/banner/new_pro.png" class="banner-icon" />
            <p>新品上架</p>
          </div>
        </el-badge>
        <el-badge class="banner-model">
          <div @click="toHotPro" class="model-item">
            <img src="../assets/img/banner/pro_hot.png" class="banner-icon" />
            <p>产品热销</p>
          </div>
        </el-badge>
      </div> -->
      <div class="banner-user">
        <img src="../assets/img/banner/head_avatar.png" />
        <div>
          <p></p>
          <p>数据日期：</p>
        </div>
      </div>
      <el-dropdown @command="handleClickCommand" trigger="click">
        <span>
          {{ currentRoleName }}
          <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item v-for="item in roleList" :key="item.roleId" :command="item.roleId">{{
            item.roleName
          }}</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <!-- 修改密码 -->
      <div class="icon-l"></div>

      <div class="logout">
        <el-tooltip popper-class="func-tootip" placement="bottom" effect="light">
          <div class="logout-text" slot="content">退出登录</div>
          <img src="../assets/logo-imgs/安全退出icon@2x.png" alt="" class="icon-close" @click="logout"  />
        </el-tooltip>
      </div>
      <div class="resetPwd">
        <span class="reset-text" @click="modifyPwd">修改密码</span>
      </div>
    </div>
    <el-dialog
      class="pwd-dialog"
      title="修改密码"
      append-to-body
      :visible.sync="dialogVisible"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :show-close="true"
      width="600px"
      :before-close="cancle"
    >
      <el-form ref="pwdForm" size="small" :model="pwdForm" :rules="pwdRule">
        <el-form-item label="用户名" >
          <el-input v-model="pwdForm.loginName" readonly></el-input>
        </el-form-item>
        <el-form-item label="原密码"  prop="oldPassword">
          <el-input v-model="pwdForm.oldPassword" placeholder="请输入原密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input v-model="pwdForm.newPassword" placeholder="请输入新密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPwd">
          <el-input v-model="pwdForm.confirmPwd" placeholder="请再次输入新密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="密码格式" required>
          <div class="reind-text">8~18个字符，以数字或字母开头，可以包含的特殊字符有：`.~!@#%*()?-_=+。</div>
        </el-form-item>
      </el-form>
      <div class="func-btn">
        <el-button type="default" size="small" @click="cancle">取消</el-button>
        <el-button type="primary" size="small" @click="updatePwd">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  // inject: ['reload'],
  data() {
    const validateNewPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入新密码'))
      } else {
        if (this.pwdForm.confirmPwd !== '') {
          this.$refs.pwdForm.validateField('confirmPwd')
        }
        callback()
      }
    }
    const validateConfirmPwd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入新密码'))
      } else if (value !== this.pwdForm.newPassword) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      roleList: [],
      getState: true,
      getDaRankNewStatus: false,
      listNewStatus: false,
      currentRole: '',
      currentRoleName: '',
      dialogVisible: false,
      pwdForm: {
        loginName: '',
        oldPassword: '',
        newPassword: '',
        confirmPwd: '',
      },
      pwdRule: {
        newPassword: [
          { validator: validateNewPassword, trigger: 'blur' },
          { required: true, message: '请输入新密码', trigger: 'blur' },
          {
            pattern: /^[A-Za-z0-9][A-Za-z0-9`~!@#%*()?\-_=+.]{7,17}$/,
            message: '8~18个字符，以数字或字母开头，可以包含的特殊字符有：`~!@#%*()?-_=+.',
            trigger: 'blur',
          },
        ],
        confirmPwd: [
          { validator: validateConfirmPwd, trigger: 'blur' },
          { required: true, message: '请再次输入新密码', trigger: 'blur' },
        ],
      },
      isNew: true,
      isNewRank: true,
      etDate: '',
    }
  },
  watch: {
    //监控头部顶部新品上架和红黑榜-new图片显示的问题
    changeState: {
      handler(newVal) {
        this.$store.state.user.headerState = newVal
        if (!newVal) {
          this.listNewStatus = false
        }
      },
    },
    changeStateRed: {
      handler(newVal) {
        this.$store.state.user.StateRed = newVal
        if (!newVal) {
          this.getDaRankNewStatus = false
        }
      },
    },
  },
  methods: {
    ...mapMutations({
      // setUserInfo: 'SET_USER_INFO',
      // setRoleList: 'SET_ROLE_LIST',
      // switchMenuLoaded: 'SWITCH_MENU_LOADED',
      // changeState: 'changeState',
      // changeStateRed: 'changeStateRed',
    }),
    ...mapActions({
      logout: 'doLogOut',
    }),
    async logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          location.href = '/index';
        })
      }).catch(() => {});
    },
    // doLogout() {
    //   this.logout()
    //     .then(res => {
    //       this.$message.success('注销成功，即将为您跳转到登录页！')
    //       setTimeout(() => location.reload(), 1500)
    //     })
    //     .catch(err => {
    //       this.$message.success('注销成功，即将为您跳转到登录页！')
    //       setTimeout(() => this.doClean(), 1500)
    //     })
    // },
    modifyPwd() {
      this.pwdForm.loginName = this.userInfo.loginName
      this.dialogVisible = true
    },
    cancle() {
      this.dialogVisible = false
      this.$refs.pwdForm.resetFields()
    },
    updatePwd() {
      this.$refs.pwdForm.validate(valid => {
        if (valid) {
          if (this.pwdForm.confirmPwd !== this.pwdForm.newPassword) {
            this.$message({
              message: '两次输入的密码不一致',
              type: 'error',
            })
            return
          }
          const { newPassword, ...args } = this.pwdForm
          this.$api.sys.user
            .resetPassword({
              ...args,
              password: newPassword,
            })
            .then(res => {
              if (res.data.code == 200) {
                this.$message.success('修改密码成功，请重新登录！')
                this.dialogVisible = false
                setTimeout(() => {
                  this.doClean()
                }, 500)
              } else {
                this.$message({
                  message: res.data.msg,
                  type: 'error',
                })
              }
            })
        } else {
          this.$message.error('请检查输入项！')
        }
      })
    },
    doClean() {
      // this.$store.commit('RESET_USER')
      // this.$store.commit('RESET_APP')
      // this.$auth.removeToken()
      // location.reload()
    },

    //切换角色
    handleClickCommand(command) {
      this.currentRole = command
      let role = this.roleList.find(item => item.roleId === command)
      this.currentRoleName = role.roleName
      this.roleChangeFn(this, role.roleId)
    },
    toNewProd() {
      this.listNewStatus = false
      window.sessionStorage.setItem('headerState', 'false')
      this.$router.push({
        path: '/pm/newProd',
      })
    },
    toHotPro() {
      this.listNewStatus = false
      this.$router.push({
        path: '/pm/hotPro',
      })
    },
    toActive() {
      this.isNew = false
      this.$router.push({
        path: '/mkt/activeQuery',
      })
    },
    toRbRank() {
      this.getDaRankNewStatus = false
      window.sessionStorage.setItem('StateRed', 'false')
      this.$router.push({
        path: '/ws/rbRank',
      })
    },
  },
  computed: {
    ...mapGetters({
    }),
  },
  created() {},
  mounted() {
    // if (this.userInfo.userName === '') {
    //   this.$store.commit('RESET_USER')
    //   this.$store.commit('RESET_APP')
    //   this.$auth.removeToken()
    //   this.$router.replace({ path: '/login' })
    // }
    // const role = this.roleList.find(item => item.roleId === this.userInfo.roleId)
    // ;[this.currentRoleName, this.currentRole] = !role ? ['', ''] : [role.roleName, role.roleId]

    // if (window.sessionStorage.getItem('headerState') !== 'false') {
    //   if (this.$store.state.user.headerState) {
    //     //查询新品上架是是否有数据更新接口
    //     this.$http.post(this.$api.pmApi.listNewStatus, {}).then(res => {
    //       if (res.data.code == 200) {
    //         let newState = res.data.data.isNew
    //         if (newState != 0) {
    //           this.listNewStatus = true
    //         } else {
    //           this.listNewStatus = false
    //         }
    //       } else {
    //         this.$message({
    //           message: res.data.msg,
    //           type: 'error',
    //         })
    //       }
    //     })
    //   } else {
    //     this.listNewStatus = false
    //   }
    // }
    // if (window.sessionStorage.getItem('StateRed') !== 'false') {
    //   if (this.$store.state.user.StateRed) {
    //     // 获取红黑榜是否存在新榜
    //     this.$http.post(this.$api.main.getDaRankNewStatus, {}).then(res => {
    //       if (res.data.code == 200) {
    //         let redState = res.data.data.isNew
    //         if (redState != 0) {
    //           this.getDaRankNewStatus = true
    //         } else {
    //           this.getDaRankNewStatus = false
    //         }
    //       } else {
    //         this.$message({
    //           message: res.data.msg,
    //           type: 'error',
    //         })
    //       }
    //     })
    //   } else {
    //     this.getDaRankNewStatus = false
    //   }
    // }
    // const getLoginNum = sessionStorage.getItem('userLoginNum')
    // if (sessionStorage.getItem('userLoginNum') === '1') {
    //   this.$confirm('当前为首次登陆，是否前往修改密码？', '提示', {
    //     type: 'warning',
    //   })
    //     .then(() => {
    //       sessionStorage.removeItem('userLoginNum')
    //       this.modifyPwd()
    //     })
    //     .catch(() => {
    //       sessionStorage.removeItem('userLoginNum')
    //     })
    // }
    //
    // this.etDate = sessionStorage.getItem('etDate')
    // console.log(this.etDate, 'this.etDate')
  },
}
</script>

<style lang="scss">
.header {
  width: 100%;
  // height: 100%;
  height: 80px;
}
.banner-box {
  // width: 100%;
  display: flex;
  align-items: center;
  height: 100%;
  font-size: 0.16rem;
  .bank_logo {
    width: 100px;
    margin: 0;
    height: auto;
    margin: 0 30px;
    margin-right: 40px;
  }

  .banner-model {
    width: 80px;
    text-align: center;
    cursor: pointer;
    .model-item {
      display: flex;
      align-items: center;
      font-size: 14px;
    }
    .banner-icon {
      width: 0.2rem;
      height: 0.2rem;
      margin-right: 0.02rem;
      background-size: contain;
      background-repeat: no-repeat;
    }
    p {
      line-height: 18px;
      // font-size: 12px;
    }
    .el-badge {
      .el-badge__content.is-fixed.is-dot {
        top: 6px !important;
        right: 12px !important;
      }
      .el-badge__content.is-dot {
        height: 8px !important;
        width: 8px !important;
      }
    }
  }
  .model_wrap {
    display: flex;
    margin-left: auto;
    .banner-model:first-child {
      margin-left: auto;
    }
  }
  .el-divider--vertical {
    margin: 15px 8px !important;
  }
  .banner-user {
    display: flex;
    align-items: center;
    margin-right: 30px;
    margin-left: auto;
    img {
      margin: 0 10px 0 10px;
      height: 38px;
      width: 38px;
      background-size: contain;
    }
    p {
      font-size: 0.14rem;
      line-height: 20px;
    }
  }
  .el-dropdown {
    // max-width: 100px; //角色名称过长不显示注释
    text-align: center;
    // line-height: 44px;
    font-size: 0.16rem;
    font-weight: bold;
    cursor: pointer;
  }
  .logout {
    width: 0.2rem;
    height: 0.2rem;
    color: red;
    // margin: 0 0.4rem 0 0.22rem;
    margin-right: 20px;
    margin-bottom: 10px;
    cursor: pointer;
  }
}
</style>
<style lang="scss" scoped>
::v-deep .el-badge__content.is-fixed.is-dot {
  right: 15px;
  margin-top: 6px;
  margin-right: 17px;
  background: red;
}
::v-deep .spc_dot .el-badge__content.is-fixed.is-dot {
  left: 39px;
  margin-top: 1px !important;
}
.resetPwd {
  cursor: pointer;
  width: 56px;
  height: 14px;
  margin-right: 24px;
}
.icon-l{
  width: 1px;
  height: 14px;
  margin-right: 20px;
  margin-left: 20px;
  background: #B9C8DF;
}
.icon-close{
  width: 15px;
  height: 16px;
}
  .header-name {
  width: 308px;
  height: 40px;
  // margin: 20px 0 0 10px;
  display: flex;
  flex-wrap: wrap;
  }
.bigcharacter-logo{
  width: 308px;
  height: 22px;
  color: rgba(34, 34, 34, 1);
  font-size: 22px;
  font-family: SourceHanSansCN-Heavy, SourceHanSansCN;
  text-align: left;
  font-weight: bolder;
  line-height: 33px;
  margin-bottom: 8px;
}
.ismallcharacter-logo{
  width: 308px;
  height: 9px;
  display: inline-block;
  color: rgba(34, 34, 34, 1);
  font-size: 9px;
  transform: scale(0.75);
  font-family: SourceHanSansCN-Bold, SourceHanSansCN;
  font-weight:600;
  white-space: nowrap;
  line-height: 14px;
  margin-left: -39px;

}
</style>
