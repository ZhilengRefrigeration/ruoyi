<template>
  <div class="">
    <el-row>
      <el-col :span="24">
        <div class="top_col">
          <span style="cursor: pointer;font-size: 14px">参数配置</span>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="7">
          <el-form-item label="保存路径" prop="path">
            <el-input v-model="formData.path" placeholder="请输入磁盘地址" :maxlength="200" clearable
                      prefix-icon='el-icon-s-tools' :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item>
            <el-button
              size="mini"
              type="primary"
              @click="submitForm"
              v-hasPermi="['webmagic:weixinlink:update']"
            >提交
            </el-button>
            <el-button size="mini" @click="resetForm">重置</el-button>
            <el-button
              size="mini"
              type="info"
              icon="el-icon-refresh"
              @click="resetSettings"
              v-hasPermi="['webmagic:weixinlink:reset']"
            >恢复默认
            </el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="top_col">
          <span style="cursor: pointer;font-size: 14px">爬虫操作</span>
          <el-tooltip content="请复制微信文章的链接地址" placement="top">
            <i class="el-icon-question"></i>
          </el-tooltip>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="15">
      <el-form size="medium" label-width="100px" :rules="linkRules" ref="linkRules" :model="requestData">
        <el-col :span="12">
          <el-form-item prop="link" label="链接">
            <el-input v-model="requestData.link" placeholder="请复制微信文章链接地址" :maxlength="200" clearable
                      prefix-icon='el-icon-s-tools' :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item>
            <el-button @click="getPicture" size="mini" type="primary" :loading="loading">执行</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>

  </div>
</template>

<script>
import {getSettings, updateSettings, resetSettings, getPicture} from "@/api/business/webmagic/weixinlink/weixinlink";

export default {
  name: "WeiXinCrawler",

  data() {
    return {
      //参数信息
      formData: {
        path: undefined,
      },

      rules: {
        path: [
          {required: true, message: '请复制微信文章链接地址', trigger: 'blur'}
        ],
      },
      linkRules: {
        link: [
          {required: true, message: '请输入下载链接', trigger: 'blur'}
        ],
      },

      loading: false,

      requestData: {
        link: "",
      },
    }
  },

  created() {
    this.getSettings()
  }
  ,

  methods: {
    getPicture() {
      this.$refs['linkRules'].validate(valid => {
        if (!valid) return
        this.loading = true
        getPicture(this.requestData).then(res => {
          this.loading = false
          this.$modal.notifySuccess("执行成功")
        }).catch(err => {
          this.loading = false
        })
      })
    }
    ,

    resetForm() {
      this.$refs['elForm'].resetFields()
    }
    ,

    //获取参数信息
    getSettings() {
      this.$modal.loading("请稍后...")
      getSettings().then(res => {
        this.$modal.closeLoading()
        this.formData.path = res.data
      })
    }
    ,

    //恢复默认（重置）
    resetSettings() {
      resetSettings().then(res => {
        this.$modal.notifySuccess("重置成功");
        this.getSettings()
      })
    }
    ,

    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        updateSettings(this.formData).then(res => {
          this.$modal.notifySuccess("修改成功");
        })
      })
    }
    ,
  }

}
</script>

<style scoped>
.top_col {
  height: 40px;
  margin: 10px 10px;
  background-color: #ffba00;
  line-height: 40px;
  padding-left: 20px;
  border-radius: 17px;
  font-size: 13px;
}
</style>
