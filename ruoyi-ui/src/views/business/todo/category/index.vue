<template>

  <div class="app-container">
    <at-card style="width: 100%;">
      <h2 slot="title">待办分类</h2>
      <div slot="extra">
        <at-button icon="icon-user-plus" @click="openDialog">添加分类</at-button>
      </div>
      <div v-for="(data,index) in dataList"
           style="width: 100%;height: 50px;border:1px solid #ffba00;margin-bottom: 10px;padding: 10px;border-radius: 6px">
        <div style="float: left;font-size: 16px;font-weight: 800;margin-left: 20px;" :style="data.color">
          {{ data.name }}
        </div>
        <div style="float: right">
          <at-button-group>
            <at-button @click="queryDialog(data)">修改</at-button>
            <at-button type="error" hollow @click="removeTodoCategory(data.id)">删除</at-button>
          </at-button-group>

        </div>
      </div>
    </at-card>

    <el-dialog
      title="添加分类"
      :visible.sync="dialogVisible"
      @close="closeDialog"
      width="20%"
    >
      <el-form :model="form" :rules="rules" ref="ruleForm" label-width="80px">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>

        <el-form-item label="分类排序" prop="sort">
          <el-slider
            v-model.number="form.sort"
            show-input>
          </el-slider>
        </el-form-item>

        <el-form-item label="分类颜色" prop="color">
          <el-color-picker v-model="form.color"></el-color-picker>
        </el-form-item>

      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" :loading=buttonLoading @click="submitForm('ruleForm')">确 定</el-button>
      </span>
    </el-dialog>


  </div>

</template>

<script>
import {addTodoCategory, editTodoCategory, list, removeTodoCategory} from "@/api/business/todo/todoCategory";

export default {
  name: "TodoCategory",

  data() {
    return {
      dialogVisible: false,
      dataList: [],

      form: {
        name: '',
        sort: 0,
        color: ''
      },

      buttonLoading: false,

      rules: {
        name: [
          {required: true, message: '请输入分类名称', trigger: 'blur'},
          {min: 1, max: 5, message: '长度在 1 到 5 个字符', trigger: 'blur'}
        ],
        sort: [
          {required: true, message: '请输入分类排序', trigger: 'blur'},
        ],
        color: [
          {required: true, message: '请输入分类颜色', trigger: 'blur'},
        ],
      }

    }
  },

  created() {
    this.list()
  },

  methods: {
    //打开对话框
    openDialog() {
      this.dialogVisible = true
      this.form = {color: '#789456', sort: 0}
    },

    //修改打开对话框
    queryDialog(data) {
      this.dialogVisible = true
      this.form = data
      //转换颜色
      if (this.form.color) {
        let split = this.form.color.split(':');
        let colorSplit = split[1].split("\"");
        this.form.color = colorSplit[0]
      }
    },

    list() {
      list().then(res => {
        this.dataList = res.data
        //颜色赋值
        this.dataList.forEach(data => {
          data.color = "color:" + data.color + ""
        })
      })
    },


    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.buttonLoading = true

          if (this.form) {
            //修改
            editTodoCategory(this.form).then(res => {
              this.$Message.success('修改成功')
              this.list()
              this.buttonLoading = false
              this.dialogVisible = false
            }).catch(err => {
              this.buttonLoading = false
              this.$Message.error('修改失败')
            });
          } else {
            //添加
            addTodoCategory(this.form).then(res => {
              this.$Message.success('添加成功')
              this.list()
              this.buttonLoading = false
              this.dialogVisible = false
            }).catch(err => {
              this.buttonLoading = false
              this.$Message.error('添加失败')
            });
          }

        } else {
          return false;
        }
      });
    },

    //删除
    removeTodoCategory(id) {
      removeTodoCategory(id).then(res => {
        this.$Message.success('删除成功')
        this.list()
      })
    },

    //关闭对话框
    closeDialog() {
      this.list()
    },
  }


}
</script>

<style scoped>

</style>
