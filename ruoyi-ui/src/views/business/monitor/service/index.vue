<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <el-tag type="info">jvm版本</el-tag>
          {{serviceMonitorInfo.jvmVersion}}
          <br>
          <el-tag type="info">jre安装路径</el-tag>
          {{serviceMonitorInfo.jreHoneDir}}
          <br>
          <el-tag type="info">jre版本</el-tag>
          {{serviceMonitorInfo.jreVersion}}
          <br>
          <el-tag type="info">jre名称</el-tag>
          {{serviceMonitorInfo.jreName}}
          <br>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <el-tag type="info">系统名称</el-tag>
          {{serviceMonitorInfo.osName}}
          <br>
          <el-tag type="info">系统版本</el-tag>
          {{serviceMonitorInfo.osVersion}}
          <br>
          <el-tag type="info">系统架构</el-tag>
          {{serviceMonitorInfo.osArch}}
          <br>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <el-tag type="info">主机名</el-tag>
          {{serviceMonitorInfo.hostName}}
          <br>
          <el-tag type="info">主机地址</el-tag>
          {{serviceMonitorInfo.hostAddress}}
          <br>
          <el-tag type="info">登录名</el-tag>
          {{serviceMonitorInfo.userName}}
          <br>
          <el-tag type="info">用户路径</el-tag>
          {{serviceMonitorInfo.homeDir}}
          <br>
          <el-tag type="info">当前目录</el-tag>
          {{serviceMonitorInfo.currentDir}}
          <br>
          <el-tag type="info">登录语言</el-tag>
          {{serviceMonitorInfo.userLanguage}}
          <br>
          <el-tag type="info">登录区域</el-tag>
          {{serviceMonitorInfo.userCountry}}
          <br>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <el-tag type="info">最大jvm内存</el-tag>
          {{serviceMonitorInfo.maxMemory/1024/1024+"M"}}
          <br>
          <el-tag type="info">已分配内存</el-tag>
          {{serviceMonitorInfo.totalMemory/1024/1024+"M"}}
          <br>
          <el-tag type="info">剩余内存</el-tag>
          {{parseFloat(Number(serviceMonitorInfo.freeMemory/1024/1024).toFixed(0))+"M"}}
          <br>
          <el-tag type="info">最大可用内存</el-tag>
          {{parseFloat(Number(serviceMonitorInfo.usableMemory/1024/1024).toFixed(0))+"M"}}
          <br>

        </div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
import {getServiceMonitor} from "@/api/business/monitor/service/monitorservice";

export default {
  name: "Online",
  data() {
    return {
      serviceMonitorInfo:{

      }
    }

  },
  created() {
    this.getServiceMonitor()
  },
  methods: {
    getServiceMonitor() {
      getServiceMonitor().then(res =>{
        this.serviceMonitorInfo=res.data
      })
    },
  },
};
</script>

<style scoped>
.el-row {
  margin: 100px;
  padding: 0 20px;
}
.el-col {
  border-radius: 4px;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
  padding: 0 180px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>

