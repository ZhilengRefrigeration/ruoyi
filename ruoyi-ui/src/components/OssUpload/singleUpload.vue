<template>
  <div>
    <el-upload class="el-upload-list__item"
      action="https://xjs-cloud.oss-cn-hangzhou.aliyuncs.com"
      :data="dataObj"
      :multiple="false"
      :show-file-list="showFileList"
      accept="image/*"
      :file-list="fileList"
      :limit=2
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :before-upload="picture_rulBeforeUpload"
      :before-remove="removeImg"
      list-type="picture-card"
      :on-preview="handlePreview">
      <el-button size="small" type="text">+</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过10MB</div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="fileList[0].url" alt="">
    </el-dialog>
  </div>
</template>

<script>
import {policy, removeImg, uploadImg} from "@/api/common";
import {getUUID} from '@/utils'

export default {
  name: 'SingleUpload',
  props: {
    value: String
  },
  computed: {
    imageUrl() {
      return this.value;
    },
    imageName() {
      if (this.value != null && this.value !== '') {
        return this.value.substr(this.value.lastIndexOf("/") + 1);
      } else {
        return null;
      }
    },
    fileList() {
      return [{
        name: this.imageName,
        url: this.imageUrl
      }]
    },
    showFileList: {
      get: function () {
        return this.value !== null && this.value !== '' && this.value !== undefined;
      },
      set: function (newValue) {
      }
    }
  },
  data() {
    return {
      dataObj: {
        policy: '',
        signature: '',
        key: '',
        ossaccessKeyId: '',
        dir: '',
        host: '',
      },
      dialogVisible: false
    };
  },

  created() {
    this.init()
  },

  methods: {
    emitInput(val) {
      this.$emit('input', val)
    },
    handleRemove(file, fileList) {
      this.emitInput('');
    },
    handlePreview(file) {
      this.dialogVisible = true;
    },

    init() {
      policy().then(response => {
        this.dataObj.policy = response.data.policy;
        this.dataObj.signature = response.data.signature;
        this.dataObj.ossaccessKeyId = response.data.accessid;
        this.dataObj.key = response.data.dir + '/' + getUUID() + '_${filename}'
        this.dataObj.dir = response.data.dir;
        this.dataObj.host = response.data.host;
      })
    },


    handleUploadSuccess(res, file) {
      this.showFileList = true;
      this.fileList.pop();
      this.fileList.push({
        name: file.name,
        url: this.dataObj.host + '/' + this.dataObj.key.replace("${filename}", file.name)
      });
      this.emitInput(this.fileList[0].url);
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

      return isRightSize && isAccept;
    },

    //删除图片
    removeImg() {
      if (this.fileList[0].url) {
        let pictureUrl = {"url": this.fileList[0].url};
        removeImg(pictureUrl).then(res => {
        })
        this.emitInput('');
      }
    },

  }
}
</script>
<style scoped>
.el-upload-list__item {
  transition: none !important;
}
</style>


