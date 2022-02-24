<template>
  <div>
    <el-row>
      <el-col :span="24">
        <div class="top_col">
          <span style="cursor: pointer;font-size: 14px">参数配置</span>
        </div>
      </el-col>
    </el-row>


    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="3">
          <el-form-item label="下载图片" prop="downloadImg" required>
            <el-switch v-model="formData.downloadImg" active-color="#3292CD" inactive-color="#D74747">
            </el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-form-item label="初始化爬取" prop="init" required>
            <el-switch v-model="formData.init" active-color="#3292CD" inactive-color="#D74747"></el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="路径" prop="path">
            <el-input v-model="formData.path" placeholder="请输入下载图片路径" :maxlength="200" clearable
                      prefix-icon='el-icon-s-tools' :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item>
            <el-button
              size="mini"
              type="primary"
              @click="submitForm"
              v-hasPermi="['webmagic:_36wallpaper:update']"
            >提交
            </el-button>
            <el-button size="mini" @click="resetForm">重置</el-button>
            <el-button
              size="mini"
              type="info"
              icon="el-icon-refresh"
              @click="resetSettings"
              v-hasPermi="['webmagic:_36wallpaper:update']"
            >恢复默认</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>

  </div>
</template>

<script>

import {getSettings, updateSettings, resetSettings} from '@/api/business/webmagic/_36wallpaper/wallpaper36'

export default {
  name: "Wallpaper_36Settings",
  data() {
    return {

      //参数信息
      formData: {
        downloadImg: false,
        init: false,
        path: undefined,
      },


      rules: {
        path: [{
          required: true,
          message: '请输入下载图片路径路径',
          trigger: 'blur'
        }],
      },
    }
  },

  created() {
    this.getSettings()
  },


  methods: {
    //获取参数信息
    getSettings() {
      const loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: "#666666"
      });

      getSettings().then(res => {
        loading.close();
        this.formData = res.data
      })



    },

    //恢复默认（重置）
    resetSettings() {
      resetSettings().then(res => {
        this.$modal.msgSuccess("重置成功");
        this.getSettings()
      })
    },

    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        let json = {
          json: null
        }
        json.json = JSON.stringify(this.formData);
        updateSettings(json).then(res => {
          this.$modal.msgSuccess("修改成功");
        })
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
  }
}
</script>

<style scoped>
.top_col {
  height: 40px;
  margin: 10px 10px;
  background-color: #13C2C2;
  line-height: 40px;
  padding-left: 20px;
  border-radius: 17px;
  font-size: 13px;
}
</style>
