<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="500px"
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="90px"
    >
      <el-form-item label="采购商品id" prop="skuId">
        <el-input v-model="dataForm.skuId" placeholder="采购商品id" maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="采购数量" prop="skuNum">
        <el-input-number v-model.number="dataForm.skuNum" :min="1" :max="9999999" label="采购数量"></el-input-number>
      </el-form-item>
      <el-form-item label="仓库" prop="wareId">
        <el-select v-model="dataForm.wareId" placeholder="请选择仓库" clearable>
          <el-option :label="w.name" :value="w.id" v-for="w in wareList" :key="w.id"></el-option>
        </el-select>
      </el-form-item>

    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import {getWareInfoList} from "@/api/mall/ware/ware-info";
import {
  editWarePurchaseDetail,
  getWarePurchaseDetail,
  saveWarePurchaseDetail
} from "@/api/mall/ware/ware-purchase-detail";

export default {
  data() {
    return {
      visible: false,
      wareList: [],
      dataForm: {
        id: 0,
        purchaseId: "",
        skuId: "",
        skuNum: "",
        skuPrice: "",
        wareId: "",
        status: 0
      },
      dataRule: {
        skuId: [
          {required: true, message: "采购商品id不能为空", trigger: "blur"}
        ],
        skuNum: [
          {required: true, message: "采购数量不能为空", trigger: "blur"}
        ],
        wareId: [{required: true, message: "仓库id不能为空", trigger: "blur"}]
      }
    };
  },
  created() {
    this.getWares();
  },
  methods: {
    //获取仓库信息
    getWares() {
      let params = {
        page: 1,
        limit: 500
      }
      getWareInfoList(params).then(res => {
        this.wareList = res.page.list;
      })
    },

    init(id) {
      this.dataForm.id = id
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.id) {
          getWarePurchaseDetail(this.dataForm.id).then(res => {
            this.dataForm = res.purchaseDetail
          })
        }
      });
    },

    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          if (!this.dataForm.id) {
            saveWarePurchaseDetail(this.dataForm).then(res => {
              this.$modal.notifySuccess("保存成功")
              this.visible = false;
              this.$emit("refreshDataList");
            })
          } else {
            editWarePurchaseDetail(this.dataForm).then(res => {
              this.$modal.notifySuccess("修改成功")
              this.visible = false;
              this.$emit("refreshDataList");
            })
          }
        }
      });
    }
  }
};
</script>
