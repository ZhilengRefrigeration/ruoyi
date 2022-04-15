<template>
  <div class="app-container">

    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="24">
          <el-form-item label="邮件标题" prop="subject" style="width:800px">
            <el-input v-model="formData.subject" placeholder="请输入标题" :maxlength="100" clearable
                      prefix-icon='el-icon-eleme' :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="收件人" prop="recipient">
            <el-input v-model="formData.recipient" placeholder="请输入收件人邮箱" :maxlength="100" clearable
                      prefix-icon='el-icon-eleme' :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="" prop="recipientSuffix" label-width="0px">
            <el-select
              v-model="formData.recipientSuffix"
              placeholder="邮箱"
              clearable
              size="small"
              style="width: 150px">
              <el-option
                v-for="dict in dict.type.email_service"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"/>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="附件" prop="file">
            <el-upload
              ref="upload"
              multiple
              :limit="3"
              action="#"
              :before-upload="beforeUpload"
              :before-remove="beforeRemove"
              :http-request="addFile"
              :file-list="fileList">
              <el-button size="mini" type="info">添加附件</el-button>
            </el-upload>
          </el-form-item>
        </el-col>

        <!--md内容 -->
        <el-col :span="24">
          <el-form-item label="邮件内容" prop="content">
            <editor v-model="formData.content" :min-height="450"/>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item size="mini">
            <el-button type="primary"
                       v-hasPermi="['sendmail-send']"
                       @click="sendForm">发送
            </el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>

  </div>


</template>

<script>
import {sendMail} from "@/api/business/tools/sendmail";

export default {
  name: "SendMail",

  dicts: ['email_service'],

  data() {
    return {
      formData: {
        subject: "",
        recipient: undefined,
        recipientSuffix: undefined,
        content: undefined,
        file:[]
      },

      rules: {
        subject: [
          {required: true, message: '请输入标题', trigger: 'blur'},
          {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur'}
        ],
        content: [
          {required: true, message: '请输入内容', trigger: 'blur'},
          {min: 1, max: 50000, message: '长度在 1 到 50000 个字符', trigger: 'blur'}
        ],
        recipient: [
          {required: true, message: '请输入收件人', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}

        ],
        recipientSuffix: [
          {required: true, message: '请选择后缀', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ]
      },

      fileList: [],

    }
  },

  watch: {
    'dict.type.email_service'(newVal) {
      if (newVal[0]) {
        this.formData.recipientSuffix = newVal[0].value
      }
    }
  },


  methods: {
    sendForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return

        let formData = new FormData();
        let files = this.formData.file;
        for (let i = 0; i < files.length; i++) {
          formData.append("fileList", files[i]);
        }
        formData.append("subject", this.formData.subject);
        formData.append("content", this.formData.content);
        formData.append("recipient", this.formData.recipient + this.formData.recipientSuffix);

        this.$modal.loading("正在发送，请稍后...")
        sendMail(formData).then(res => {
          this.$modal.notifySuccess("发送成功")
          this.$modal.closeLoading()
          this.formData.subject = ""
          this.formData.recipient = ""
          this.formData.recipientSuffix = ""
        }).catch(err => {
          this.$modal.closeLoading()
        })


      })
    },

    addFile() {

    },

    //上传文件之前
    beforeUpload(file) {
      console.log(file)
      this.formData.file.push(file)
    },

    //删除文件之前
    beforeRemove(file) {
      let fileList =this.formData.file
      for (let i = 0; i < fileList.length; i++) {
        if (fileList[i].uid===file.uid) {
          fileList.splice(i,1)
        }
      }
    },

  }
}
</script>

<style scoped>

</style>
