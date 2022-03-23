<template>
  <el-dialog
    width="500px"
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="90px"
    >
      <el-form-item label="sku_id" prop="skuId">
        <el-input v-model="dataForm.skuId" placeholder="请输入商品id"></el-input>
      </el-form-item>
      <el-form-item label="仓库" prop="wareId">
        <el-select v-model="dataForm.wareId" placeholder="请选择仓库" clearable>
          <el-option :label="w.name" :value="w.id" v-for="w in wareList" :key="w.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="库存数" prop="stock">
        <el-input-number v-model.number="dataForm.stock" :min="0" :max="9999999" label="库存数"></el-input-number>
      </el-form-item>
      <el-form-item label="sku_name" prop="skuName">
        <el-input v-model="dataForm.skuName" placeholder="请输入商品名称"></el-input>
      </el-form-item>
      <el-form-item label="锁定库存" prop="stockLocked">
        <el-input-number v-model.number="dataForm.stockLocked" :min="0" :max="9999999" label="锁定库存"></el-input-number>
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
import {editWareSku, getWareSku, saveWareSku} from "@/api/mall/ware/ware-sku";

export default {
  name: "WareSku-add-or-update",
  data() {
    return {
      visible: false,
      wareList: [],
      dataForm: {
        id: 0,
        skuId: "",
        wareId: "",
        stock: 0,
        skuName: "",
        stockLocked: 0
      },
      dataRule: {
        skuId: [{required: true, message: "sku_id不能为空", trigger: "blur"}],
        wareId: [
          {required: true, message: "仓库id不能为空", trigger: "blur"}
        ],
        stock: [{required: true, message: "库存数不能为空", trigger: "blur"}],
        skuName: [
          {required: true, message: "sku_name不能为空", trigger: "blur"}
        ]
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
      this.dataForm.id = id;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.id) {
          getWareSku(this.dataForm.id).then(res => {
            this.dataForm = res.wareSku
          })
        }
      });
    },

    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {

          if (!this.dataForm.id) {
            saveWareSku(this.dataForm).then(res => {
              this.$modal.notifySuccess("保存成功")
              this.visible = false;
              this.$emit("refreshDataList");
            })
          } else {
            editWareSku(this.dataForm).then(res => {
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
