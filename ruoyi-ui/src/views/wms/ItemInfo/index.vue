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
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['wms:ItemInfo:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['wms:ItemInfo:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['wms:ItemInfo:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ItemInfoList" @selection-change="handleSelectionChange"
              :show-overflow-tooltip="true">
      <el-table-column type="selection" width="30" align="center"/>
      <el-table-column label="物品代码" align="center" prop="itemCd"/>
      <el-table-column label="物品名称" width="120" align="center" prop="itemName"/>
      <el-table-column label="启用标志" align="center" prop="enableFlg">
        <template #default="scope">
          <dict-tag :options="sys_enable_flag" :value="scope.row.enableFlg"/>
        </template>
      </el-table-column>
      <el-table-column label="物品类型" align="center" prop="itemTypeName"/>
      <el-table-column label="物品图片" align="center" prop="viewImg">
        <template #default="scope">
          <el-button link type="primary" icon="View" @click="handleViewImage(scope.row)">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column label="标准单位" align="center" prop="stdUnitName"/>
      <el-table-column label="包装单位" align="center" prop="pkgUnitName"/>
      <el-table-column label="批号管理" align="center" prop="lotNoMgmtCls"/>
      <el-table-column label="默认库位号" width="100" align="center" prop="defaultStgBinCd"/>
      <el-table-column label="生产商" align="center" prop="manufacturer"/>
      <el-table-column label="供应商" align="center" prop="supplier"/>
      <el-table-column label="安全库存量" width="90" align="center" prop="safetyStock"/>
      <el-table-column label="最大库存量" width="90" align="center" prop="maxInvQty"/>
      <el-table-column label="购买阈值" align="center" prop="purchLimitQty"/>
      <el-table-column label="操作" align="center" width="140"  class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['wms:ItemInfo:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['wms:ItemInfo:remove']">删除</el-button>
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

    <!-- 添加或修改物品基础信息对话框 -->
    <el-dialog :title="title" v-model="open" width="65%" append-to-body>
      <el-form ref="ItemInfoRef" :model="form" :rules="rules" label-width="100px">
        <el-tabs v-model="activeName">
          <!-- ==================== 基础信息输入 ==================== -->
          <el-tab-pane label="基础信息" name="basicForm">
            <el-row>
              <el-col :span="12">
                <el-form-item label="物品代码" prop="itemCd">
                  <el-input v-model="form.itemCd" placeholder="请输入物品代码"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="物品名称" prop="itemName">
                  <el-input v-model="form.itemName" placeholder="请输入物品名称"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="启用标志" prop="enableFlg">
                  <el-radio-group v-model="form.enableFlg">
                    <el-radio
                        v-for="dict in sys_enable_flag"
                        :key="dict.value"
                        :label="dict.value"
                    >{{ dict.label }}
                    </el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="物品类型" prop="itemTypeCd">
                  <el-select v-model="form.itemTypeCd" placeholder="请选择物品类型" clearable filterable>
                    <el-option
                        v-for="item in itemTypeList"
                        :key="item.itemTypeCd"
                        :label="item.itemTypeName"
                        :value="item.itemTypeCd"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="供应商" prop="supplier">
                  <el-input v-model="form.supplier" placeholder="请输入供应商"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="生产商" prop="manufacturer">
                  <el-input v-model="form.manufacturer" placeholder="请输入生产商"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="自定物品区分" prop="goodsCls">
                  <el-input v-model="form.goodsCls" placeholder="请输入自定物品区分"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="批号管理" prop="lotNoMgmtCls">
                  <el-radio-group v-model="form.lotNoMgmtCls">
                    <el-radio
                        v-for="dict in sys_enable_flag"
                        :key="dict.value"
                        :label="dict.value"
                    >{{ dict.label }}
                    </el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="标准单位" prop="stdUnitCd">
                  <el-select v-model="form.stdUnitCd" placeholder="请选择标准单位" clearable filterable>
                    <el-option
                        v-for="item in unitList"
                        :key="item.unitCode"
                        :label="item.unitName"
                        :value="item.unitCode"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="包装单位" prop="pkgUnitCd">
                  <el-select v-model="form.pkgUnitCd" placeholder="请选择标准单位" clearable filterable>
                    <el-option
                        v-for="item in unitList"
                        :key="item.unitCode"
                        :label="item.unitName"
                        :value="item.unitCode"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="物品图片" prop="pictureId">
                  <fp-file-upload
                      ref="fileUpload"
                      style="width: 100%"
                      :file-type="['image/jpeg', 'image/png', 'image/webp', 'image/gif',]"
                  />
                </el-form-item>
              </el-col>
            </el-row>
          </el-tab-pane>
          <!-- ==================== 库存信息输入 ==================== -->
          <el-tab-pane label="库存信息" name="stockForm">
            <el-row>
              <el-col :span="12">
                <el-form-item label="默认库位号" prop="defaultStgBinCd">
                  <el-input v-model="form.defaultStgBinCd" placeholder="请输入默认库位号"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="供货周期" prop="deliveryPeriod">
                  <el-input v-model="form.deliveryPeriod" placeholder="请输入供货周期"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="安全库存量" prop="safetyStock">
                  <el-input-number v-model="form.safetyStock" placeholder="请输入安全库存量" :precision="2" :min="0"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="最大库存量" prop="maxInvQty">
                  <el-input-number v-model="form.maxInvQty" placeholder="请输入最大库存量" :precision="2" :min="0"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="购买阈值" prop="purchLimitQty">
                  <el-input-number v-model="form.purchLimitQty" placeholder="请输入购买阈值" :precision="2" :min="0"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="最小出库数" prop="outstockReqMinQty">
                  <el-input-number v-model="form.outstockReqMinQty" placeholder="请输入最小出库数" :precision="2"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="入库提前期" prop="instockLeadTime">
                  <el-input-number v-model="form.instockLeadTime" placeholder="请输入入库提前期" :precision="0"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="出库提前期" prop="outstockLeadTime">
                  <el-input-number v-model="form.outstockLeadTime" placeholder="请输入出库提前期" :precision="0"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="筹备提前期" prop="prepLeadTime">
                  <el-input-number v-model="form.prepLeadTime" placeholder="请输入筹备提前期" :precision="0"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="放置期" prop="restingPeriod">
                  <el-input-number v-model="form.restingPeriod" placeholder="请输入放置期" :precision="0"/>
                </el-form-item>
              </el-col>
            </el-row>
          </el-tab-pane>
          <!-- ==================== 规格信息输入 ==================== -->
          <el-tab-pane label="规格信息" name="specForm">
            <el-row>
              <el-col :span="12">
                <el-form-item label="单位净重" prop="netWeightPerUnit">
                  <el-input-number v-model="form.netWeightPerUnit" placeholder="请输入单位净重" :precision="6"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="自身体积(M3)" prop="ownVolM3">
                  <el-input-number v-model="form.ownVolM3" placeholder="请输入自身体积(M3)" :precision="6"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="尺寸(长)" prop="sizeD">
                  <el-input-number v-model="form.sizeD" placeholder="请输入尺寸(长)" :precision="6"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="尺寸(宽)" prop="sizeW">
                  <el-input-number v-model="form.sizeW" placeholder="请输入尺寸(宽)" :precision="6"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="尺寸(高)" prop="sizeH">
                  <el-input-number v-model="form.sizeH" placeholder="请输入尺寸(高)" :precision="6"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="包装要求描述" prop="pkgRqmtDesc">
                  <el-input v-model="form.pkgRqmtDesc" placeholder="请输入包装要求描述"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="码放要求描述" prop="stackingRqmtDesc">
                  <el-input v-model="form.stackingRqmtDesc" placeholder="请输入码放要求描述"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="储存要求描述" prop="stgRqmtDesc">
                  <el-input v-model="form.stgRqmtDesc" placeholder="请输入储存要求描述"/>
                </el-form-item>
              </el-col>
            </el-row>
          </el-tab-pane>
          <!-- ==================== 其他信息输入 ==================== -->
          <el-tab-pane label="其他信息" name="otherForm">
            <el-row>
              <el-col :span="12">
                <el-form-item label="规格1" prop="spec1">
                  <el-input v-model="form.spec1" placeholder="请输入规格1"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="规格2" prop="spec2">
                  <el-input v-model="form.spec2" placeholder="请输入规格2"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="规格3" prop="spec3">
                  <el-input v-model="form.spec3" placeholder="请输入规格3"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="规格4" prop="spec4">
                  <el-input v-model="form.spec4" placeholder="请输入规格4"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="规格5" prop="spec5">
                  <el-input v-model="form.spec5" placeholder="请输入规格5"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="分类1" prop="cls1">
                  <el-input v-model="form.cls1" placeholder="请输入分类1"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="分类2" prop="cls2">
                  <el-input v-model="form.cls2" placeholder="请输入分类2"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="分类3" prop="cls3">
                  <el-input v-model="form.cls3" placeholder="请输入分类3"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="分类4" prop="cls4">
                  <el-input v-model="form.cls4" placeholder="请输入分类4"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="分类5" prop="cls5">
                  <el-input v-model="form.cls5" placeholder="请输入分类5"/>
                </el-form-item>
              </el-col>
            </el-row>
          </el-tab-pane>
        </el-tabs>
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
import {listItemInfo, getItemInfo, delItemInfo, addItemInfo, updateItemInfo} from "@/api/wms/ItemInfo";
import {listUnitInfo} from "@/api/wms/UnitInfo";
import {listItemType} from "@/api/wms/ItemType";

const {proxy} = getCurrentInstance();
const {sys_enable_flag} = proxy.useDict("sys_enable_flag");

const ItemInfoList = ref([]);
const unitList = ref([]);
const itemTypeList = ref([]);
const open = ref(false);
const loading = ref(false);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const submitMode = ref('add');
const fileUpload = ref(null);

const activeName = ref('basicForm');

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 30,
    itemCd: null,
    itemName: null,
  },
  rules: {
    itemCd: [
      {required: true, message: "物品代码不能为空", trigger: "blur"}
    ],
    itemName: [
      {required: true, message: "物品名称不能为空", trigger: "blur"}
    ],
    enableFlg: [
      {required: true, message: "启用标志不能为空", trigger: "blur"}
    ],
    stdUnitCd: [
      {required: true, message: "标准单位不能为空", trigger: "blur"}
    ],
  }
});

const {queryParams, form, rules} = toRefs(data);

/** 查询物品基础信息列表 */
function getList() {
  loading.value = true;
  listItemInfo(queryParams.value).then(response => {
    ItemInfoList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

/** 查询物品单位列表 */
function getUnitList() {
  listUnitInfo({}).then(response => {
    unitList.value = response.rows;
  });
}

function getItemTypeList() {
  listItemType({}).then(response => {
    itemTypeList.value = response.rows;
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
    prepLeadTime: 0,
    instockLeadTime: 0,
    restingPeriod: 0,
    outstockLeadTime: 0,
    deliveryPeriod: null,
    defaultStgBinCd: null,
    enableFlg: null,
    safetyStock: 0,
    maxInvQty: 0,
    purchLimitQty: 0,
    goodsCls: null,
    lotNoMgmtCls: null,
    itemTypeCd: null,
    stdUnitCd: null,
    pkgUnitCd: null,
    outstockReqMinQty: 0,
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
    pictureUrl: null,
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
  };
  if (fileUpload.value) {
    fileUpload.value.removeFiles();
  }
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
  submitMode.value = 'add';
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
  submitMode.value = 'update';
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["ItemInfoRef"].validate(valid => {
    if (valid) {
      //准备要提交的文件，如果有的话
      const files = fileUpload.value.getFiles();
      const submitFiles = [];
      for (let i = 0; i < files.length; i++) {
        submitFiles.push({
          key: 'itemImages',
          value: files[i],
        })
      }
      //提交数据
      if (submitMode.value === 'update') {
        updateItemInfo(form.value, true, submitFiles).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addItemInfo(form.value, true, submitFiles).then(response => {
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
  proxy.$modal.confirm('是否确认删除物品基础信息编号为"' + _itemCds + '"的数据项？').then(function () {
    return delItemInfo(_itemCds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('wms/ItemInfo/export', {
    ...queryParams.value
  }, `ItemInfo_${new Date().getTime()}.xlsx`)
}

/** 查看图片 */
function handleViewImage(row) {
  if (!row.pictureUrl) {
    proxy.$modal.msgWarning("该物品没有图片");
    return;
  }
  window.open(row.pictureUrl)
}

//页面打开时查询
//getList();
getUnitList();
getItemTypeList();
</script>
<style scoped>
.el-select,
.el-input-number {
  width: 100%;
}

.filepond--item {
  width: calc(50% - 0.5em);
}
</style>
