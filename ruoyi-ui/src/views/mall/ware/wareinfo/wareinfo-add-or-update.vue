<template>
  <el-dialog
    width="500px"
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="仓库名" prop="name">
        <el-input v-model="dataForm.name" placeholder="仓库名"></el-input>
      </el-form-item>
      <el-form-item label="仓库地址" prop="address">
        <el-input v-model="dataForm.address" placeholder="仓库地址"></el-input>
      </el-form-item>
      <el-form-item label="区域编码" prop="areacode">
        <el-input v-model="dataForm.areacode" placeholder="区域编码"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import {editWareInfo, getWareInfo, saveWareInfo} from "@/api/mall/ware/ware-info";

export default {
  name: "Ware-info-add-update",
  data() {
    return {
      visible: false,
      dataForm: {
        name: '',
        address: '',
        areacode: ''
      },
      dataRule: {
        name: [
          {required: true, message: '仓库名不能为空', trigger: 'blur'}
        ],
        address: [
          {required: true, message: '仓库地址不能为空', trigger: 'blur'}
        ],
        areacode: [
          {required: true, message: '区域编码不能为空', trigger: 'blur'}
        ]
      }
    }
  },

  methods: {
    init(id) {
      this.dataForm.id = id
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          getWareInfo(this.dataForm.id).then(res => {
            this.dataForm = res.wareInfo
          })
        }
      })
    },

    // 表单提交
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          if (!this.dataForm.id) {
            saveWareInfo(this.dataForm).then(res => {
              this.$modal.notifySuccess("添加成功")
              this.visible = false
              this.$emit('refreshDataList')
            })
          } else {
            editWareInfo(this.dataForm).then(res => {
              this.$modal.notifySuccess("修改成功")
              this.visible = false
              this.$emit('refreshDataList')
            })
          }
        }
      })
    },

  }
}
</script>
