<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物品代码" prop="itemCd">
        <el-input
          v-model="queryParams.itemCd"
          placeholder="请输入物品代码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物品名称" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入物品名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['wms:ItemInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['wms:ItemInfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['wms:ItemInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['wms:ItemInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ItemInfoList" @selection-change="handleSelectionChange" show-overflow-tooltip="true">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="物品代码" align="center" prop="itemCd" />
      <el-table-column label="物品名称" align="center" prop="itemName" />
      <el-table-column label="默认库位号" align="center" prop="defaultStgBinCd" />
      <el-table-column label="启用标志" align="center" prop="enableFlg" />
      <el-table-column label="批号管理区分" align="center" prop="lotNoMgmtCls" />
      <el-table-column label="物品类型代码" align="center" prop="itemTypeCd" />
      <el-table-column label="标准单位代码" align="center" prop="stdUnitCd" />
      <el-table-column label="包装单位代码" align="center" prop="pkgUnitCd" />
      <el-table-column label="生产商" align="center" prop="manufacturer" />
      <el-table-column label="供应商" align="center" prop="supplier" />
      <el-table-column label="安全库存量" align="center" prop="safetyStock" />
      <el-table-column label="最大库存量" align="center" prop="maxInvQty" />
      <el-table-column label="购买阈值" align="center" prop="purchLimitQty" />
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template #default="scope">-->
<!--          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['wms:ItemInfo:edit']">修改</el-button>-->
<!--          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['wms:ItemInfo:remove']">删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改物品基础信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="ItemInfoRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物品名称" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入物品名称" />
        </el-form-item>
        <el-form-item label="供应商" prop="supplier">
          <el-input v-model="form.supplier" placeholder="请输入供应商" />
        </el-form-item>
        <el-form-item label="筹备提前期" prop="prepLeadTime">
          <el-input v-model="form.prepLeadTime" placeholder="请输入筹备提前期" />
        </el-form-item>
        <el-form-item label="入库提前期" prop="instockLeadTime">
          <el-input v-model="form.instockLeadTime" placeholder="请输入入库提前期" />
        </el-form-item>
        <el-form-item label="放置期" prop="restingPeriod">
          <el-input v-model="form.restingPeriod" placeholder="请输入放置期" />
        </el-form-item>
        <el-form-item label="出库提前期" prop="outstockLeadTime">
          <el-input v-model="form.outstockLeadTime" placeholder="请输入出库提前期" />
        </el-form-item>
        <el-form-item label="规格1" prop="spec1">
          <el-input v-model="form.spec1" placeholder="请输入规格1" />
        </el-form-item>
        <el-form-item label="规格2" prop="spec2">
          <el-input v-model="form.spec2" placeholder="请输入规格2" />
        </el-form-item>
        <el-form-item label="规格3" prop="spec3">
          <el-input v-model="form.spec3" placeholder="请输入规格3" />
        </el-form-item>
        <el-form-item label="规格4" prop="spec4">
          <el-input v-model="form.spec4" placeholder="请输入规格4" />
        </el-form-item>
        <el-form-item label="规格5" prop="spec5">
          <el-input v-model="form.spec5" placeholder="请输入规格5" />
        </el-form-item>
        <el-form-item label="分类1" prop="cls1">
          <el-input v-model="form.cls1" placeholder="请输入分类1" />
        </el-form-item>
        <el-form-item label="分类2" prop="cls2">
          <el-input v-model="form.cls2" placeholder="请输入分类2" />
        </el-form-item>
        <el-form-item label="分类3" prop="cls3">
          <el-input v-model="form.cls3" placeholder="请输入分类3" />
        </el-form-item>
        <el-form-item label="分类4" prop="cls4">
          <el-input v-model="form.cls4" placeholder="请输入分类4" />
        </el-form-item>
        <el-form-item label="分类5" prop="cls5">
          <el-input v-model="form.cls5" placeholder="请输入分类5" />
        </el-form-item>
        <el-form-item label="供货周期" prop="deliveryPeriod">
          <el-input v-model="form.deliveryPeriod" placeholder="请输入供货周期" />
        </el-form-item>
        <el-form-item label="默认库位号" prop="defaultStgBinCd">
          <el-input v-model="form.defaultStgBinCd" placeholder="请输入默认库位号" />
        </el-form-item>
        <el-form-item label="启用标志" prop="enableFlg">
          <el-input v-model="form.enableFlg" placeholder="请输入启用标志" />
        </el-form-item>
        <el-form-item label="安全库存量" prop="safetyStock">
          <el-input v-model="form.safetyStock" placeholder="请输入安全库存量" />
        </el-form-item>
        <el-form-item label="最大库存量" prop="maxInvQty">
          <el-input v-model="form.maxInvQty" placeholder="请输入最大库存量" />
        </el-form-item>
        <el-form-item label="购买阈值" prop="purchLimitQty">
          <el-input v-model="form.purchLimitQty" placeholder="请输入购买阈值" />
        </el-form-item>
        <el-form-item label="物品区分" prop="goodsCls">
          <el-input v-model="form.goodsCls" placeholder="请输入物品区分" />
        </el-form-item>
        <el-form-item label="批号管理区分(0:不管理, 1:管理)" prop="lotNoMgmtCls">
          <el-input v-model="form.lotNoMgmtCls" placeholder="请输入批号管理区分(0:不管理, 1:管理)" />
        </el-form-item>
        <el-form-item label="物品类型代码" prop="itemTypeCd">
          <el-input v-model="form.itemTypeCd" placeholder="请输入物品类型代码" />
        </el-form-item>
        <el-form-item label="标准单位代码" prop="stdUnitCd">
          <el-input v-model="form.stdUnitCd" placeholder="请输入标准单位代码" />
        </el-form-item>
        <el-form-item label="包装单位代码" prop="pkgUnitCd">
          <el-input v-model="form.pkgUnitCd" placeholder="请输入包装单位代码" />
        </el-form-item>
        <el-form-item label="出库申请最小数量" prop="outstockReqMinQty">
          <el-input v-model="form.outstockReqMinQty" placeholder="请输入出库申请最小数量" />
        </el-form-item>
        <el-form-item label="出库单位区分" prop="outstockUnitCls">
          <el-input v-model="form.outstockUnitCls" placeholder="请输入出库单位区分" />
        </el-form-item>
        <el-form-item label="单位净重" prop="netWeightPerUnit">
          <el-input v-model="form.netWeightPerUnit" placeholder="请输入单位净重" />
        </el-form-item>
        <el-form-item label="自身体积(M3)" prop="ownVolM3">
          <el-input v-model="form.ownVolM3" placeholder="请输入自身体积(M3)" />
        </el-form-item>
        <el-form-item label="尺寸(长)" prop="sizeD">
          <el-input v-model="form.sizeD" placeholder="请输入尺寸(长)" />
        </el-form-item>
        <el-form-item label="尺寸(宽)" prop="sizeW">
          <el-input v-model="form.sizeW" placeholder="请输入尺寸(宽)" />
        </el-form-item>
        <el-form-item label="尺寸(高)" prop="sizeH">
          <el-input v-model="form.sizeH" placeholder="请输入尺寸(高)" />
        </el-form-item>
        <el-form-item label="包装要求描述" prop="pkgRqmtDesc">
          <el-input v-model="form.pkgRqmtDesc" placeholder="请输入包装要求描述" />
        </el-form-item>
        <el-form-item label="码放要求描述" prop="stackingRqmtDesc">
          <el-input v-model="form.stackingRqmtDesc" placeholder="请输入码放要求描述" />
        </el-form-item>
        <el-form-item label="储存要求描述" prop="stgRqmtDesc">
          <el-input v-model="form.stgRqmtDesc" placeholder="请输入储存要求描述" />
        </el-form-item>
        <el-form-item label="生产商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入生产商" />
        </el-form-item>
        <el-form-item label="图片ID" prop="pictureId">
          <el-input v-model="form.pictureId" placeholder="请输入图片ID" />
        </el-form-item>
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

<script setup name="ItemInfo">
import { listItemInfo, getItemInfo, delItemInfo, addItemInfo, updateItemInfo } from "@/api/wms/ItemInfo";

const { proxy } = getCurrentInstance();

const ItemInfoList = ref([]);
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
    pageSize: 10,
    itemCd: null,
    itemName: null,
  },
  rules: {
    itemCd: [
      { required: true, message: "物品代码不能为空", trigger: "blur" }
    ],
    itemName: [
      { required: true, message: "物品名称不能为空", trigger: "blur" }
    ],
    defaultStgBinCd: [
      { required: true, message: "默认库位号不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询物品基础信息列表 */
function getList() {
  loading.value = true;
  listItemInfo(queryParams.value).then(response => {
    ItemInfoList.value = response.rows;
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
    itemCd: null,
    itemName: null,
    supplier: null,
    prepLeadTime: null,
    instockLeadTime: null,
    restingPeriod: null,
    outstockLeadTime: null,
    spec1: null,
    spec2: null,
    spec3: null,
    spec4: null,
    spec5: null,
    cls1: null,
    cls2: null,
    cls3: null,
    cls4: null,
    cls5: null,
    updateCount: null,
    deleteFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null,
    remark1: null,
    remark2: null,
    remark3: null,
    remark4: null,
    remark5: null,
    deliveryPeriod: null,
    defaultStgBinCd: null,
    enableFlg: null,
    safetyStock: null,
    maxInvQty: null,
    purchLimitQty: null,
    goodsCls: null,
    lotNoMgmtCls: null,
    itemTypeCd: null,
    stdUnitCd: null,
    pkgUnitCd: null,
    outstockReqMinQty: null,
    outstockUnitCls: null,
    netWeightPerUnit: null,
    ownVolM3: null,
    sizeD: null,
    sizeW: null,
    sizeH: null,
    pkgRqmtDesc: null,
    stackingRqmtDesc: null,
    stgRqmtDesc: null,
    manufacturer: null,
    pictureId: null,
    pictureUrl: null
  };
  proxy.resetForm("ItemInfoRef");
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
  ids.value = selection.map(item => item.itemCd);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加物品基础信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _itemCd = row.itemCd || ids.value
  getItemInfo(_itemCd).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改物品基础信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["ItemInfoRef"].validate(valid => {
    if (valid) {
      if (form.value.itemCd != null) {
        updateItemInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addItemInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _itemCds = row.itemCd || ids.value;
  proxy.$modal.confirm('是否确认删除物品基础信息编号为"' + _itemCds + '"的数据项？').then(function() {
    return delItemInfo(_itemCds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('wms/ItemInfo/export', {
    ...queryParams.value
  }, `ItemInfo_${new Date().getTime()}.xlsx`)
}

//页面打开时查询
//getList();
</script>
