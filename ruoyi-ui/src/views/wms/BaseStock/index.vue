<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="仓库" prop="whsCd">
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleInstock"
          v-hasPermi="['wms:BaseStock:instock']"
        >入库</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleOutstock"
          v-hasPermi="['wms:BaseStock:outstock']"
        >出库</el-button>
      </el-col>
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
<!--      <el-table-column label="托盘ID" align="center" prop="palletId" />-->
<!--      <el-table-column label="父托盘ID" align="center" prop="parentPalletId" />-->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 入出库对话框 -->
    <el-dialog :title="title" v-model="open" width="650px" append-to-body>
      <el-form ref="BaseStockRef" :model="form" :rules="rules" label-width="90px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="仓库代码" prop="whsCd">
              <data-select v-model="form.whsCd" :fetch-data="fetchWarehouseData" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="货架号" prop="stgBinCd">
              <el-input v-model="form.stgBinCd" placeholder="请输入货架号"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="物品代码" prop="itemCd">
              <el-input v-model="form.itemCd" placeholder="请输入物品代码" @blur="handleItemInfoBlur"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物品名称" prop="itemName">
              <el-input v-model="form.itemName" disabled/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="批号" prop="lotNo">
              <el-input v-model="form.lotNo" placeholder="请输入批号"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="子批号" prop="itemName">
              <el-input v-model="form.subLotNo" placeholder="请输入子批号"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="数量" prop="stdUnitQty">
              <el-input-number v-model="form.stdUnitQty" placeholder="标准单位数量" style="width: 100%;" :precision="6" :min="0"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标准单位" prop="stdUnitName">
              <el-input v-model="form.stdUnitName" disabled/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="数量" prop="pkgUnitQty">
              <el-input-number v-model="form.pkgUnitQty" placeholder="包装单位数量" style="width: 100%;" :precision="6" :min="0"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="包装单位" prop="pkgUnitName">
              <el-input v-model="form.pkgUnitName" disabled/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item :label="reasonTitle" prop="reason">
              <el-input v-model="form.reason" placeholder="请输入理由"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="业务区分" prop="businessCls">
              <el-input v-model="form.businessCls" placeholder="请输入业务区分"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="序列号" prop="serialNo">
              <el-input v-model="form.serialNo" placeholder="请输入序列号"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="交易单号" prop="transOrderNo">
              <el-input v-model="form.transOrderNo" placeholder="请输入交易单号"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="交易明细号" prop="transOrderDetlNo">
              <el-input v-model="form.transOrderDetlNo" placeholder="请输入交易明细号"/>
            </el-form-item>
          </el-col>
        </el-row>
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
import { listBaseStock, getBaseStock, instock, outstock } from "@/api/wms/BaseStock"
import { listWarehouseInfo } from "@/api/wms/WarehouseInfo";
import { getItemInfo } from "@/api/wms/ItemInfo";
import DataSelect from "@/components/DataSelect/index.vue";
import {ElLoading} from "element-plus";

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
const reasonTitle = ref("");

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
    whsCd: [
      {required: true, message: "仓库代码不能为空", trigger: "blur"}
    ],
    stgBinCd: [
      {required: true, message: "货架号不能为空", trigger: "blur"}
    ],
    itemCd: [
      {required: true, message: "物品代码不能为空", trigger: "blur"}
    ],
    stdUnitQty: [
      {required: true, message: "标准单位数量不能为空", trigger: "blur"},
    ],
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
    stockType: null,
    whsCd: null,
    stgBinCd: null,
    itemCd: null,
    lotNo: '',
    subLotNo: '',
    stdUnitQty: null,
    pkgUnitQty: null,
    serialNo: null,
    palletId: null,
    operator: null,
    businessCls: null,
    reason: null,
    transOrderNo: null,
    transOrderDetlNo: null,
    itemName: null,
    stdUnitName: null,
    pkgUnitName: null,
    itemNotExists: false,
    lastItemCd: null, //避免反复查询未改变的物品代码
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
  form.value.stockType = 1;
  title.value = "入库";
  reasonTitle.value = "入库理由";
  open.value = true;
}

/** 出库按钮操作 */
function handleOutstock() {
  reset();
  let primaryKey = ids.value[0];
  getBaseStock(primaryKey).then(response => {
    form.value = response.data;
    form.value.stockType = 2;
    form.value.stdUnitQty = 0;
    form.value.pkgUnitQty = 0;
    title.value = "出库";
    reasonTitle.value = "出库理由";
    open.value = true;
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["BaseStockRef"].validate(valid => {
    if (valid) {
      if (form.value.itemNotExists) {
        proxy.$modal.msgError("此物品代码不存在");
        return;
      }
      if (form.value.stockType === 1) {
        // 入库
        instock(form.value).then(response => {
          proxy.$modal.msgSuccess("入库成功");
          open.value = false;
          getList();
        });
      } else {
        // 出库
        outstock(form.value).then(response => {
          proxy.$modal.msgSuccess("出库成功");
          open.value = false;
          getList();
        });
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

/** 获取仓库数据 */
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

/** 查询物品信息 */
function handleItemInfoBlur() {
  if (form.value.itemCd && form.value.itemCd !== ' ' && form.value.lastItemCd !== form.value.itemCd) {
    const loadingObj = ElLoading.service({text: "正在查询物品信息 ... ...", background: "rgba(0, 0, 0, 0.7)",})
    getItemInfo(form.value.itemCd).then(response => {
      if (response.data) {
        form.value.itemName = response.data.itemName
        form.value.stdUnitName = response.data.stdUnitName
        form.value.pkgUnitName = response.data.pkgUnitName
        form.value.itemNotExists = false
      } else {
        form.value.itemName = '无此物品信息'
        form.value.stdUnitName = ''
        form.value.pkgUnitName = ''
        form.value.itemNotExists = true
      }
    }).finally(() => {
      loadingObj.close()
      form.value.lastItemCd = form.value.itemCd
    })
  }
}

//页面打开时查询
//getList();
</script>
