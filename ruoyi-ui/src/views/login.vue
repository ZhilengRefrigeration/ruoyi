<template>
  <div class="login">
    <el-tabs v-model="activeName" @tab-click="handleTagClick" class="login-form">
      <el-tab-pane name="first" label="账号登录">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules"  >
          <h3 class="title">篮球Zone后台管理系统</h3>
          <el-form-item prop="username">
            <el-input
              v-model="loginForm.username"
              type="text"
              auto-complete="off"
              placeholder="账号"
            >
              <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="loginForm.password"
              type="password"
              auto-complete="off"
              placeholder="密码"
              @keyup.enter.native="handleLogin"
            >
              <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
            </el-input>
          </el-form-item>
          <el-form-item prop="code" v-if="captchaEnabled">
            <el-input
              v-model="loginForm.code"
              auto-complete="off"
              placeholder="验证码"
              style="width: 63%"
              @keyup.enter.native="handleLogin"
            >
              <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
            </el-input>
            <div class="login-code">
              <img :src="codeUrl" @click="getCode" class="login-code-img"/>
            </div>
          </el-form-item>
          <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
          <el-form-item style="width:100%;">
            <el-button
              :loading="loading"
              size="medium"
              type="primary"
              style="width:100%;"
              @click.native.prevent="handleLogin"
            >
              <span v-if="!loading">登 录</span>
              <span v-else>登 录 中...</span>
            </el-button>
            <div style="float: right;" v-if="register">
              <router-link class="link-type" :to="'/register'">立即注册</router-link>
            </div>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane name="second" label="微信扫码登录">
        <div   class="wx-qrcode" >
          <el-image v-if="imageLoaded" :src="qrCodeUrl" class="wx-qrcode-login-img" @load="handleImageLoad" @error="handleImageError" @click="getWxScanQrCode"/>
          <div class="el-icon-loading" v-else></div>
        </div>
      </el-tab-pane>
    </el-tabs>

    <!--  底部  -->
    <div class="el-login-footer">
      <span>Copyright © 2020-2024 lzsport.com All Rights Reserved.</span>
    </div>
  </div>
</template>

<script>
import { getCodeImg, wxScanLoginCheck } from "@/api/login";
import {genWxApplesAqrCodeForPc, genWxApplesAqrCode} from "@/api/system/wxApplesCode";
import Cookies from "js-cookie";
import { encrypt, decrypt } from '@/utils/jsencrypt'

export default {
  name: "Login",
  data() {
    return {
      codeUrl: "",
      loginForm: {
        username: "admin",
        password: "",
        rememberMe: false,
        code: "",
        uuid: ""
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" }
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" }
        ],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }]
      },
      loading: false,
      // 验证码开关
      captchaEnabled: true,
      // 注册开关
      register: false,
      redirect: undefined,
      qrCodeUrl: "",
      activeName: 'first',
      showexpire: false,
      imageLoaded: false,
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    this.getCode();
    this.getCookie();
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer); // 销毁组件前清除定时器
    }
  },
  methods: {
    getCode() {
      getCodeImg().then(res => {
        this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
        if (this.captchaEnabled) {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.loginForm.uuid = res.uuid;
        }
      });
    },
    handleImageLoad(){
      console.log('图片加载完成');
      this.imageLoaded = true;
    },
    handleImageError(){
        console.log('图片加载失败');
      this.$modal.msgWarning("二维码加载失败");
    },
    getWxScanQrCode() {
      const timestamp = Date.now();
      let checkCode = 'wxScanLogin'+timestamp;
      let params ={
        // envVersion: 'develop',
        checkPath: false,
        scene: checkCode,
        page: 'pages/wxScanLogin/wxScanLogin'
      };
      console.log(params)
      genWxApplesAqrCodeForPc(params).then(res => {
          this.qrCodeUrl = "data:image/png;base64," + res.data.base64;
          this.imageLoaded = true;
          this.timer = setInterval(() => {
            let param1 = {
              checkCode: checkCode
            }
            wxScanLoginCheck(param1).then(res1 => {
              console.log(res1)
              if(res1.data){
                console.log("登录成功")
                // 登录成功清除定时器
                clearInterval(this.timer);
                this.$store.dispatch("WxScanLogin", res1.data).then(() => {
                  this.$router.push({ path: this.redirect || "/" }).catch(()=>{});
                }).catch(() => {
                  console.log("登录失败")
                });
              }else{
                console.log("登录失败")
              }
            });
            // 执行需要定时重复执行的任务
          }, 2000); // 每2秒钟执行一次
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, { expires: 30 });
            Cookies.set("password", encrypt(this.loginForm.password), { expires: 30 });
            Cookies.set('rememberMe', this.loginForm.rememberMe, { expires: 30 });
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove('rememberMe');
          }
          this.$store.dispatch("Login", this.loginForm).then(() => {
            this.$router.push({ path: this.redirect || "/" }).catch(()=>{});
          }).catch(() => {
            this.loading = false;
            if (this.captchaEnabled) {
              this.getCode();
            }
          });
        }
      });
    },
    // 切换登录方式
    handleTagClick(tab, event) {
      if (tab.name === 'first') {
        this.getCode();
        this.getCookie();
      } else {
         this.getWxScanQrCode();
      }
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
body {
  margin: 0;
}
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
}
.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 33%;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.login-code-img {
  height: 38px;
}
.wx-qrcode{
  width: 100%;
  height: 320px;
  text-align: center;
}
.wx-qrcode-login-img{
  height: 300px;
  cursor: pointer;
}
</style>
