<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="85px">
      <el-form-item label="入出库类型" prop="invTransType">
        <data-select v-model="queryParams.invTransType" dict-name="wms_inv_trans_type" />
      </el-form-item>
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
      <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
      <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['wms:InvTransHis:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="InvTransHisList" @selection-change="handleSelectionChange" :show-overflow-tooltip="true">
      <el-table-column type="selection" width="30" align="center" />
      <el-table-column label="入出库履历号" align="center" prop="invTransNo" />
      <el-table-column label="入出库类型" align="center" prop="invTransType" >
        <template #default="scope">
          <dict-tag :options="wms_inv_trans_type" :value="scope.row.invTransType"/>
        </template>
      </el-table-column>
      <el-table-column label="存储位置" align="center" >
        <el-table-column label="仓库代码" align="center" prop="whsCd" />
        <el-table-column label="货架号" align="center" prop="stgBinCd" />
      </el-table-column>
      <el-table-column label="物品代码" align="center" prop="itemCd" />
      <el-table-column label="批号" align="center" prop="lotNo" />
      <el-table-column label="子批号" align="center" prop="subLotNo" />
      <el-table-column label="数量" align="center" >
        <el-table-column label="标准单位数量" align="center" prop="stdUnitQty" />
        <el-table-column label="标准单位" align="center" prop="stdUnitName" />
        <el-table-column label="包装单位数量" align="center" prop="pkgUnitQty" />
        <el-table-column label="包装单位" align="center" prop="pkgUnitName" />
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createByUser" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改入出库履历对话框 -->
<!--    <el-dialog :title="title" v-model="open" width="500px" append-to-body>-->
<!--      <el-form ref="InvTransHisRef" :model="form" :rules="rules" label-width="80px">-->
<!--      </el-form>-->
<!--      <template #footer>-->
<!--        <div class="dialog-footer">-->
<!--          <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--          <el-button @click="cancel">取 消</el-button>-->
<!--        </div>-->
<!--      </template>-->
<!--    </el-dialog>-->
  </div>
</template>

<script setup name="InvTransHis">
import { listInvTransHis } from "@/api/wms/InvTransHis";
import { listWarehouseInfo } from "@/api/wms/WarehouseInfo";

const { proxy } = getCurrentInstance();
const {wms_inv_trans_type} = proxy.useDict("wms_inv_trans_type");

const InvTransHisList = ref([]);
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
    invTransType: null,
    whsCd: null,
    stgBinCd: null,
    itemCd: null,
    lotNo: null,
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询入出库履历列表 */
function getList() {
  loading.value = true;
  listInvTransHis(queryParams.value).then(response => {
    InvTransHisList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// dialog表单重置(目前无用)
function reset() {
  // form.value = {
  //   deptId: null,
  //   invTransNo: null,
  //   invTransType: null,
  //   whsCd: null,
  //   stgBinCd: null,
  //   palletId: null,
  //   stdUnitQty: null,
  //   pkgUnitQty: null,
  //   transOrderNo: null,
  //   transOrderDetlNo: null,
  //   operator: null,
  //   businessCls: null,
  //   itemCd: null,
  //   lotNo: null,
  //   subLotNo: null,
  //   serialNo: null,
  //   reason: null,
  //   remark1: null,
  //   remark2: null,
  //   remark3: null,
  //   remark4: null,
  //   remark5: null,
  //   updateCount: null,
  //   deleteFlag: null,
  //   createBy: null,
  //   createTime: null,
  //   updateBy: null,
  //   updateTime: null,
  //   remark: null
  // };
  // proxy.resetForm("InvTransHisRef");
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
  ids.value = selection.map(item => item.invTransNo);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('wms/InvTransHis/export', {
    ...queryParams.value
  }, `InvTransHis_${new Date().getTime()}.xlsx`)
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
