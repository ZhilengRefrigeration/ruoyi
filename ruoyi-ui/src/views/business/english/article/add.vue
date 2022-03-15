<template>
  <div class="app-container">
    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="12">
          <el-form-item label="标题" prop="titleChinese">
            <el-input v-model="formData.titleChinese" placeholder="请输入标题" :maxlength="100" clearable
                      prefix-icon='el-icon-eleme' :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="内容" prop="contentChinese">
            <el-input v-model="formData.contentChinese" type="textarea" placeholder="请输入内容" :maxlength="1000"
                      :autosize="{minRows: 8, maxRows: 12}" :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="上传封面" prop="pictureUrl" required>
            <el-upload ref="pictureUrl"
                       action="#" :http-request="requestUpload"
                       :limit="1"
                       :before-remove="removeImg"
                       :before-upload="picture_rulBeforeUpload" list-type="picture-card" accept="image/*"
                       name="pictureUrl">
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item size="large">
            <el-button type="primary"
                       v-hasPermi="['english:article:add']"
                       @click="submitForm">提交
            </el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>

<script>
import {addArticle} from "@/api/business/english/article";
import {removeImg, uploadImg} from "@/api/common";

export default {
  name: "ArticleAdd",
  components: {},
  props: [],
  data() {
    return {
      formData: {
        titleChinese: undefined,
        contentChinese: undefined,
        pictureUrl: null,
      },
      rules: {
        titleChinese: [{
          required: true,
          message: '请输入标题',
          trigger: 'blur'
        }],
        contentChinese: [{
          required: true,
          message: '请输入内容',
          trigger: 'blur'
        }],
      },

    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() {
  },
  methods: {

    //添加文章
    addArticle() {

      this.$modal.loading("请稍候...");

      addArticle(this.formData).then(res => {
        this.$modal.notifySuccess("添加成功");
        this.$modal.closeLoading()
        this.$router.push({path: '/business/english/article/articleList'})
      }).catch(err =>{
        this.$modal.closeLoading()
      })

    },

    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return

        this.addArticle()

      })
    },

    resetForm() {
      this.$refs['elForm'].resetFields()
    },

    // 覆盖默认的上传行为
    requestUpload() {
    },

    picture_rulBeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 10
      if (!isRightSize) {
        this.$message.error('文件大小超过 10MB')
      }
      let isAccept = new RegExp('image/*').test(file.type)
      if (!isAccept) {
        this.$message.error('应该选择image/*类型的文件')
      }

      let formData = new FormData();
      formData.append("file", file);
      this.$modal.loading("请稍候...");
      uploadImg(formData).then(res => {
        this.formData.pictureUrl = res.data.url
        this.$modal.closeLoading()
        this.$modal.notifySuccess("上传成功");
      }).catch(err =>{
        this.$modal.closeLoading()
      })

      return isRightSize && isAccept
    },

    //删除图片
    removeImg() {
      if (this.formData.pictureUrl) {
        let pictureUrl = {"url": this.formData.pictureUrl};
        removeImg(pictureUrl).then(res => {})

        this.formData.pictureUrl = null;
      }
    },

  }
}
</script>

<style scoped>
.el-upload__tip {
  line-height: 1.2;
}

</style>
