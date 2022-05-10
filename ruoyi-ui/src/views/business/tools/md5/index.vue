<template>
  <div class="app-container">
    <el-card shadow="hover">
      <div slot="header" class="clearfix">
        <span>MD5解密说明</span>
      </div>
      <ul style="font-size: 13px;color: #999999">
        <li>MD5再次申明没有解密的方法，最好的反驳就是：数据源是无穷尽的，而 MD5密文是有限的。</li>
        <li>本工具是采用先加密存储，然后再反查询，上千 PB 级别毫秒级响应。</li>
        <li>16位 MD5和32位 MD5区别是取的是8~24位。</li>
        <li>目前 MD5 数据正在拓展，拓展一个量级会同步到工具中来。</li>
      </ul>
    </el-card>

    <el-row :gutter="50" style="margin-top: 20px">
      <el-col :span="12">
        <at-input v-model="SourceValue" placeholder="请输入" size="large" :maxlength="20" append-button>
          <template slot="append">
            <i class="icon icon-search" @click="encryption">加密</i>
          </template>
        </at-input>
      </el-col>

      <el-form :inline="true">

        <el-col :span="6">
          <el-form-item label="32位">
            <at-input v-model="targetValue32"></at-input>
          </el-form-item>

        </el-col>

        <el-col :span="6">
          <el-form-item label="16位">
            <at-input v-model="targetValue16"></at-input>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>

    <el-row :gutter="50" style="margin-top: 20px">
      <el-col :span="24">
        <at-input v-model="md5Value" placeholder="请输入" size="large" :maxlength="32" append-button>
          <template slot="append">
            <i class="icon icon-search" @click="decrypt">解密</i>
          </template>
        </at-input>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {decrypt, encryption} from "@/api/business/tools/md5";

export default {
  name: "index",

  data() {
    return {
      SourceValue: null,
      targetValue16: null,
      targetValue32: null,

      md5Value: '',

      //解密值
      decryptValue: null,
    }
  },

  methods: {
    encryption() {
      let data = {
        str: this.SourceValue
      }
      encryption(data).then(res => {
        this.targetValue16 = res.data.target16
        this.targetValue32 = res.data.target32
        this.$modal.notifySuccess("加密成功")
      })
    },

    decrypt() {
      let data = {
        str: this.md5Value
      }
      decrypt(data).then(res => {
        this.decryptValue = res.msg
        this.$alert(this.decryptValue, '解密成功啦', {
          confirmButtonText: '确定',
        });
      })
    },
  }
}
</script>

<style scoped>

</style>
