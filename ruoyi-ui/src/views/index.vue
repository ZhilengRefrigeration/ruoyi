<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>RYAS后台管理框架</h2>
        <p>
         此后台管理系统基于开源基盘Ruoyi（v{{ version }}）
        </p>
        <p>
          已从原本的Ruoyi全面升级：
        </p>
        <ul>
          <li>Java 8 升级到 Java 21</li>
          <li>SpringBoot 2.7 升级到 SpringBoot 3.1</li>
          <li>SpringCloud 2020.x 升级到 SpringCloud 2023.0.5</li>
        </ul>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>SpringCloud</li>
              <li>Nacos</li>
              <li>Sentinel</li>
              <li>Seata</li>
              <li>Minio</li>
              <li>等等</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Echarts</li>
              <li>Quill</li>
              <li>等等</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>卡片1</span>
            </div>
          </template>
          <div class="body">
            <p>
              这是一段测试文本
            </p>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>卡片2</span>
            </div>
          </template>
          <el-collapse accordion>
            <el-collapse-item title="折叠项1">
              <ol>
                <li>测试子项a</li>
                <li>测试子项b</li>
                <li>测试子项c</li>
                <li>测试子项d</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="折叠项2">
              <ol>
                <li>测试1</li>
                <li>测试2</li>
              </ol>
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>卡片3</span>
            </div>
          </template>
          <div class="body">
            <img
              src="@/assets/images/programmerhumor-01.webp"
              alt="donate"
              style="width:100%"
            />
            <img
                src="@/assets/images/programmerhumor-02.webp"
                alt="donate"
                style="width:100%"
            />
            <span style="display: inline-block; height: 30px; line-height: 30px"
              >以上程序员笑话来自于:<el-link>https://programmerhumor.io/</el-link></span
            >
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup name="Index">
const version = ref('3.6.3')

function goTarget(url) {
  window.open(url, '__blank')
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

