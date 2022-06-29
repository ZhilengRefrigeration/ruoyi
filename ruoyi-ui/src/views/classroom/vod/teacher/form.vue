<template>
  <div class="app-container">
    <!-- 输入表单 -->
    <el-form label-width="120px" :model="teacher" :rules="rules" ref="form">
      <el-form-item label="讲师名称" prop="name">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="入驻时间" prop="joinDate">
        <el-date-picker v-model="teacher.joinDate" value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item label="讲师排序" prop="sort">
        <el-input-number v-model="teacher.sort" :min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔" prop="level">
        <el-select v-model="teacher.level">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
            -->
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="0" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师简介" prop="intro">
        <el-input v-model="teacher.intro"/>
      </el-form-item>
      <el-form-item label="讲师资历" prop="career">
        <el-input v-model="teacher.career" :rows="10" type="textarea"/>
      </el-form-item>

      <!-- 讲师头像 -->
      <!-- 讲师头像 -->
      <el-form-item label="讲师头像" prop="avatar">
        <el-upload
          ref="avatar"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :on-error="handleAvatarError"
          :http-request="requestUpload"
          :file-list="fileList"
          action="#"
          :before-remove="removeImg"
          :limit="1"
          name="avatar"
          list-type="picture-card" accept="image/*"
        >
          <i class="el-icon-plus"></i>
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate()">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import teacherApi from '@/api/classroom/vod/teacher'
import {removeImg, uploadImg} from "@/api/common";

export default {
  name: "TeacherForm",

  data() {
    return {
      teacher: {
        sort: 0,
        level: 1,
        avatar: null
      },

      fileList: [],

      rules: {
        name: [{
          required: true,
          message: '请输入讲师名称',
          trigger: 'blur'
        }, {min: 1, max: 10, message: '讲师名称长度在 1 到 10 个字符', trigger: 'blur'}],
        joinDate: [{
          required: true,
          message: '请选择入驻时间',
          trigger: 'blur'
        }],
        sort: [{
          required: true,
          message: '请选择讲师排序',
          trigger: 'blur'
        }],
        level: [{
          required: true,
          message: '请输入讲师头衔',
          trigger: 'blur'
        }],
        intro: [{min: 1, max: 50, message: '简介长度在 1 到 50 个字符', trigger: 'blur'}],
        career: [{min: 1, max: 500, message: '资历长度在 1 到 500 个字符', trigger: 'blur'}],
      },
    }
  },
  created() {
    //获取路径id值，根据id查询得到数据
    if (this.$route.query.id) {
      const id = this.$route.query.id
      this.fetchDataById(id)
    }
  },

  beforeDestroy() {
    this.fileList = []
  },

  methods: {
    // 上传成功回调
    handleAvatarSuccess(res, file) {
      // console.log(res)
      if (res.code === 200) {
        // console.log(res)
        this.teacher.avatar = res.data
        // 强制重新渲染
        this.$forceUpdate()
      } else {
        this.$message.error('上传失败 （非0）')
      }
    },

    // 错误处理
    handleAvatarError() {
      this.$message.error('上传失败（http失败）')
    },
    // 覆盖默认的上传行为
    requestUpload() {
    },
    //删除图片
    removeImg() {
      if (this.teacher.avatar) {
        let avatar = {"url": this.teacher.avatar};
        removeImg(avatar).then(res => {
        })

        this.teacher.avatar = null;
      }
    },


    // 上传校验
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 5

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG或PNG 格式!')
        return false
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 5 MB!')
        return false
      }

      let formData = new FormData();
      formData.append("file", file);
      this.$modal.loading("请稍候...");
      uploadImg(formData).then(res => {
        this.teacher.avatar = res.data.url
        this.$modal.closeLoading()
        this.$modal.notifySuccess("上传成功");
      }).catch(err => {
        this.$modal.closeLoading()
      })

    },
    //根据id查询讲师
    fetchDataById(id) {
      teacherApi.getTeacherById(id)
        .then(response => {
          this.teacher = response.data
          this.fileList = []
          this.fileList.push({
            url: this.teacher.avatar
          })
        })
    },
    //添加
    save() {

      this.$refs["form"].validate(valid => {
        if (valid) {
          //添加
          teacherApi.saveTeacher(this.teacher)
            .then(response => {
              //提示
              this.$message({
                type: 'success',
                message: '添加成功!'
              });
              //跳转列表页面
              this.$router.push({path: '/classroom/classroom-teacher/teacher-list'})
            })
        }
      })


    },
    //修改
    update() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          teacherApi.updateTeacher(this.teacher)
            .then(response => {
              //提示
              this.$message({
                type: 'success',
                message: '修改成功!'
              });
              //跳转列表页面
              this.$router.push({path: '/classroom/classroom-teacher/teacher-list'})
            })
        }
      })


    },
    //添加和修改
    saveOrUpdate() {
      if (!this.teacher.id) { //没有id，添加
        this.save()
      } else { //有id，修改
        this.update()
      }
    }
  }
}
</script>
