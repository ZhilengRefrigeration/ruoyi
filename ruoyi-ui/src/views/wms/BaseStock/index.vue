<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="仓库" prop="whsCd">
<!--        <el-input-->
<!--          v-model="queryParams.whsCd"-->
<!--          placeholder="请输入仓库代码"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
        <data-select v-model="queryParams.whsCd" :fetch-data="fetchWarehouseData" />
      </el-form-item>
      <el-form-item label="货架号" prop="stgBinCd">
        <el-input
          v-model="queryParams.stgBinCd"
          placeholder="请输入货架号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物品代码" prop="itemCd">
        <el-input
          v-model="queryParams.itemCd"
          placeholder="请输入物品代码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批号" prop="lotNo">
        <el-input
          v-model="queryParams.lotNo"
          placeholder="请输入批号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['wms:BaseStock:export']"
        >导出</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="Plus"-->
<!--          @click="handleInstock"-->
<!--          v-hasPermi="['wms:BaseStock:instock']"-->
<!--        >入库</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="Edit"-->
<!--          :disabled="single"-->
<!--          @click="handleOutstock"-->
<!--          v-hasPermi="['wms:BaseStock:outstock']"-->
<!--        >出库</el-button>-->
<!--      </el-col>-->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="BaseStockList" @selection-change="handleSelectionChange" :show-overflow-tooltip="true">
      <el-table-column type="selection" width="30" align="center" />
      <el-table-column label="存储位置" align="center" >
        <el-table-column label="仓库代码" align="center" prop="whsCd" />
        <el-table-column label="仓库名称" align="center" prop="whsName" />
        <el-table-column label="货架号" align="center" prop="stgBinCd" />
      </el-table-column>
      <el-table-column label="物品代码" align="center" prop="itemCd" />
      <el-table-column label="物品名称" align="center" prop="itemName" />
      <el-table-column label="批号" align="center" prop="lotNo" />
      <el-table-column label="子批号" align="center" prop="subLotNo" />
      <el-table-column label="数量" align="center" >
        <el-table-column label="标准单位数量" align="center" prop="stdUnitQty" />
        <el-table-column label="标准单位" align="center" prop="stdUnitName" />
        <el-table-column label="包装单位数量" align="center" prop="pkgUnitQty" />
        <el-table-column label="包装单位" align="center" prop="pkgUnitName" />
      </el-table-column>
      <el-table-column label="序列号" align="center" prop="serialNo" />
      <el-table-column label="托盘ID" align="center" prop="palletId" />
      <el-table-column label="父托盘ID" align="center" prop="parentPalletId" />
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 入出库对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="BaseStockRef" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="BaseStock">
import { listBaseStock, getBaseStock } from "@/api/wms/BaseStock"
import { listWarehouseInfo } from "@/api/wms/WarehouseInfo";
import DataSelect from "@/components/DataSelect/index.vue";

const { proxy } = getCurrentInstance();

const BaseStockList = ref([]);
const open = ref(false);
const loading = ref(false);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 30,
    whsCd: null,
    stgBinCd: null,
    itemCd: null,
    lotNo: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询基本库存列表 */
function getList() {
  loading.value = true;
  listBaseStock(queryParams.value).then(response => {
    BaseStockList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    deptId: null,
    whsCd: null,
    stgBinCd: null,
    itemCd: null,
    lotNo: null,
    subLotNo: null,
    stdUnitQty: null,
    pkgUnitQty: null,
    serialNo: null,
    palletId: null,
    parentPalletId: null,
    remark1: null,
    remark2: null,
    remark3: null,
    remark4: null,
    remark5: null,
    updateCount: null,
    deleteFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  };
  proxy.resetForm("BaseStockRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => {
    return {
      whsCd: item.whsCd,
      stgBinCd: item.stgBinCd,
      itemCd: item.itemCd,
      lotNo: item.lotNo,
      subLotNo: item.subLotNo,
    };
  });
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 入库按钮操作 */
function handleInstock() {
  reset();
  open.value = true;
  title.value = "入库";
}

/** 出库按钮操作 */
function handleOutstock() {
  reset();
  let primaryKey = ids.value[0]
  getBaseStock(primaryKey).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "出库";
  });
}

/** 提交按钮 */
function submitForm() {
  //TODO 未完成
  proxy.$refs["BaseStockRef"].validate(valid => {
    if (valid) {
      if (form.value.deptId != null) {
        // instock(form.value).then(response => {
        //   proxy.$modal.msgSuccess("出库成功");
        //   open.value = false;
        //   getList();
        // });
      } else {
        // outstock(form.value).then(response => {
        //   proxy.$modal.msgSuccess("入库成功");
        //   open.value = false;
        //   getList();
        // });
      }
    }
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('wms/BaseStock/export', {
    ...queryParams.value
  }, `BaseStock_${new Date().getTime()}.xlsx`)
}

// 获取仓库数据
async function fetchWarehouseData() {
  const response = await listWarehouseInfo({})
  const dataList = []
  response.rows.map(item => {
    dataList.push({
      label: item.whsName,
      value: item.whsCd,
    })
  })
  return dataList
}

//页面打开时查询
//getList();
</script>
