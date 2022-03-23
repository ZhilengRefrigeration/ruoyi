<template>
  <el-dialog
    width="500px"
    @close="closeDialog"
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="仓库名" prop="name">
        <el-input v-model="dataForm.name" placeholder="仓库名"></el-input>
      </el-form-item>
      <el-form-item label="仓库地址" prop="cascaderValue" v-if="!dataForm.id">
        <el-cascader
          ref="cascader"
          style="width: 380px"
          :props="props"
          v-model="dataForm.cascaderValue"
          :options="areaList"
          @change="handleChange"
        ></el-cascader>
      </el-form-item>

      <el-form-item label="详细地址" prop="detailAddress">
        <el-input v-model="dataForm.detailAddress" placeholder="详细地址"></el-input>
      </el-form-item>
      <el-form-item label="区域编码" prop="areacode">
        <el-input v-model.number="dataForm.areacode" :disabled="!dataForm.id" placeholder="区域编码"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>

</template>

<script>
import {editWareInfo, getAreaByParentId, getProvinceArea, getWareInfo, saveWareInfo} from "@/api/mall/ware/ware-info";

export default {
  name: "Ware-info-add-update",
  data() {
    return {
      visible: false,
      dataForm: {
        name: '',
        address: '',
        areacode: null,
        detailAddress: '',
        cascaderValue: [],
      },


      dataRule: {
        name: [
          {required: true, message: '仓库名不能为空', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],
        detailAddress: [
          {required: true, message: '详细地址不能为空', trigger: 'blur'},
          {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur'}
        ],
        cascaderValue:[
          {required: true, message: '仓库地址不能为空', trigger: 'blur'},
        ],
        areacode: [
          {required: true, message: '区域编码不能为空', trigger: 'blur'},
          {type: 'number', min: 100000, max: 999999, message: '区域编码为数字且为6位', trigger: 'blur'}
        ]
      },

      areaList: [],


      props: {
        value: "name",
        label: "name",
        children: "districts",
        lazy: true,
        lazyLoad: this.lazyLoad
      }

    }
  },

  computed: {
    appendAddress: function () {
      let cascaderAddress = ''
      this.dataForm.cascaderValue.forEach(a => {
        if (cascaderAddress === '') {
          cascaderAddress = cascaderAddress + a;
        } else {
          cascaderAddress = cascaderAddress + " " + a;
        }
      })
      cascaderAddress = cascaderAddress + "-" + this.dataForm.detailAddress
      return cascaderAddress
    },
  },

  created() {
    this.getProvinceArea()
  },

  methods: {
    lazyLoad(node, resolve) {
      var that = this

      setTimeout(() => {
        if (node.level === 1 || node.level === 2 || node.level === 3) {
          getAreaByParentId(node.data.id).then(res => {
            if (node.level === 3) {
              res.data.forEach(a => {
                a.leaf = true
              });
              //获取areacode值
              that.dataForm.areacode = parseInt(node.data.adcode)
            }
            resolve(res.data);
          })
        }
      }, 200);
    },


    //获取级联数据
    getProvinceArea() {
      getProvinceArea().then(res => {
        this.areaList = res.data
      })
    },

    init(id) {
      this.dataForm.id = id
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          getWareInfo(this.dataForm.id).then(res => {
            //必须单独赋值，否则下面的赋值无效
            this.dataForm.name = res.wareInfo.name
            this.dataForm.address = res.wareInfo.address
            this.dataForm.areacode = res.wareInfo.areacode

            //回显级联选择器的值
            let split = this.dataForm.address.split("-");
            this.dataForm.detailAddress = this.dataForm.address

            this.dataForm.cascaderValue = split[0].split(" ")
          })
        }
      })
    },

    // 表单提交
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          if (!this.dataForm.id) {
            this.dataForm.address = this.appendAddress
            saveWareInfo(this.dataForm).then(res => {
              this.$modal.notifySuccess("添加成功")
              this.visible = false
              this.$emit('refreshDataList')
            })
          } else {
            this.dataForm.address = this.dataForm.detailAddress
            editWareInfo(this.dataForm).then(res => {
              this.$modal.notifySuccess("修改成功")
              this.visible = false
              this.$emit('refreshDataList')
            })
          }
        }
      })
    },

    //可以不需要，因为最后一级的adcode全是一样的
    handleChange(data) {
      let nodes = this.$refs.cascader.getCheckedNodes()[0];
      this.dataForm.areacode = parseInt(nodes.data.adcode)
    },

    closeDialog() {
      this.dataForm = {
        name: '',
        address: '',
        areacode: null,
        detailAddress: '',
        cascaderValue: [],
      }
    },
  }
}
</script>
