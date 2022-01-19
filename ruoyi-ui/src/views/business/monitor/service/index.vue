<template>
  <div v-loading="loading">
    <el-row :gutter="24">
      <el-col :md="12" :sm="24">
        <el-card header="系统信息" style="margin-bottom: 20px" :bordered="false">
          <table class="sysInfo_table">
            <tr>
              <td class="sysInfo_td">系统名称：</td>
              <td class="sysInfo_td">{{ serviceMonitorInfo.osName }}</td>
            </tr>
            <tr>
              <td class="sysInfo_td">系统架构：</td>
              <td class="sysInfo_td">{{ serviceMonitorInfo.osArch }}</td>
            </tr>
            <tr>
              <td class="sysInfo_td">系统版本：</td>
              <td class="sysInfo_td">{{ serviceMonitorInfo.osVersion }}</td>
            </tr>
            <tr>
              <td class="sysInfo_td">主机名称：</td>
              <td class="sysInfo_td">{{ serviceMonitorInfo.hostName }}</td>
            </tr>
            <tr>
              <td>主机IP地址：</td>
              <td>{{ serviceMonitorInfo.hostAddress }}</td>
            </tr>
          </table>
        </el-card>
      </el-col>
      <el-col :md="12" :sm="24">
        <el-card header="Java信息" style="margin-bottom: 20px">
          <table class="sysInfo_table">
            <tr>
              <td class="sysInfo_td">虚拟机名称：</td>
              <td class="sysInfo_td">{{ serviceMonitorInfo.jreName }}</td>
            </tr>
            <tr>
              <td class="sysInfo_td">虚拟机版本：</td>
              <td class="sysInfo_td">{{ serviceMonitorInfo.jvmVersion }}</td>
            </tr>
            <tr>
              <td class="sysInfo_td">JAVA版本：</td>
              <td class="sysInfo_td">{{ serviceMonitorInfo.jreVersion }}</td>
            </tr>
            <tr>
              <td class="sysInfo_td">JRE安装路径：</td>
              <td class="sysInfo_td">{{ serviceMonitorInfo.jreHoneDir }}</td>
            </tr>
            <tr>
              <td class="sysInfo_td">项目路径：</td>
              <td class="sysInfo_td">{{ serviceMonitorInfo.currentDir }}</td>
            </tr>
          </table>
        </el-card>
      </el-col>
    </el-row>
    <el-card header="JVM内存信息">
      <table class="sysInfo_table">
        <tr>
          <td class="sysInfo_td">可用内存：</td>
          <td class="sysInfo_td">
            {{ parseFloat(Number(serviceMonitorInfo.usableMemory / 1024 / 1024).toFixed(0)) + "M" }}
          </td>
          <td class="sysInfo_td">已使用内存：</td>
          <td class="sysInfo_td">{{ serviceMonitorInfo.totalMemory / 1024 / 1024 + "M" }}</td>
        </tr>
        <tr>
          <td class="sysInfo_td">总内存：</td>
          <td class="sysInfo_td">{{ serviceMonitorInfo.maxMemory / 1024 / 1024 + "M" }}</td>
          <td class="sysInfo_td">空余内存：</td>
          <td class="sysInfo_td">
            {{ parseFloat(Number(serviceMonitorInfo.freeMemory / 1024 / 1024).toFixed(0)) + "M" }}
          </td>
        </tr>
      </table>
    </el-card>
    <el-card header="浏览器信息">
      <table class="sysInfo_table">
        <tr>
          <td class="sysInfo_td">端口号：</td>
          <td class="sysInfo_td">{{ serviceMonitorInfo.serverPort }}</td>
        </tr>
        <tr>
          <td class="sysInfo_td">字符编码：</td>
          <td class="sysInfo_td">{{ serviceMonitorInfo.characterEncoding }}</td>
        </tr>
        <tr>
          <td class="sysInfo_td">浏览器标识：</td>
          <td class="sysInfo_td">{{ serviceMonitorInfo.userAgent }}</td>
        </tr>
      </table>
    </el-card>
  </div>

</template>

<script>
import {getServiceMonitor} from "@/api/business/monitor/service/monitorservice";

export default {
  name: "Service",
  data() {
    return {
      //遮罩层
      loading: true,

      serviceMonitorInfo: {}
    }

  },
  created() {
    this.getServiceMonitor()
  },
  methods: {
    getServiceMonitor() {
      this.loading = true
      getServiceMonitor().then(res => {
        this.serviceMonitorInfo = res.data
        this.loading = false
      })
    },
  },
};
</script>

<style scoped>
.sysInfo_table {
  width: 100%;
  min-height: 45px;
  line-height: 45px;
  text-align: center;
}

.sysInfo_td {
  border-bottom: 1px solid #e8e8e8;
}
</style>

