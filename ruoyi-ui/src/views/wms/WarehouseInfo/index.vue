<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="仓库代码" prop="whsCd">
        <el-input
          v-model="queryParams.whsCd"
          placeholder="请输入仓库代码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库名称" prop="whsName">
        <el-input
          v-model="queryParams.whsName"
          placeholder="请输入仓库名称"
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
          v-hasPermi="['wms:WarehouseInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['wms:WarehouseInfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['wms:WarehouseInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['wms:WarehouseInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="WarehouseInfoList" @selection-change="handleSelectionChange" :show-overflow-tooltip="true">
      <el-table-column type="selection" width="30" align="center" />
      <el-table-column label="仓库代码" align="center" prop="whsCd" />
      <el-table-column label="仓库名称" align="center" prop="whsName" />
      <el-table-column label="仓库类型代码" align="center" prop="whsTypeCd" />
      <el-table-column label="缩写" align="center" prop="abbr" />
      <el-table-column label="地址1" align="center" prop="addr1" />
      <el-table-column label="地址2" align="center" prop="addr2" />
      <el-table-column label="地址3" align="center" prop="addr3" />
      <el-table-column label="联系电话" align="center" prop="phoneNo" />
      <el-table-column label="邮编" align="center" prop="zipCd" />
      <el-table-column label="联系邮箱" align="center" prop="email" />
      <el-table-column label="传真号" align="center" prop="faxNo" />
      <el-table-column label="负责人" align="center" prop="respPerson" />
      <el-table-column label="区域" align="center" prop="area" />
      <el-table-column label="租赁费用" align="center" prop="rentalFee" />
      <el-table-column label="存储费用" align="center" prop="storingFee" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['wms:WarehouseInfo:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['wms:WarehouseInfo:remove']">删除</el-button>
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

    <!-- 添加或修改仓库基础信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="WarehouseInfoRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="仓库名称" prop="whsName">
          <el-input v-model="form.whsName" placeholder="请输入仓库名称" />
        </el-form-item>
        <el-form-item label="仓库类型代码" prop="whsTypeCd">
          <el-input v-model="form.whsTypeCd" placeholder="请输入仓库类型代码" />
        </el-form-item>
        <el-form-item label="缩写" prop="abbr">
          <el-input v-model="form.abbr" placeholder="请输入缩写" />
        </el-form-item>
        <el-form-item label="地址1" prop="addr1">
          <el-input v-model="form.addr1" placeholder="请输入地址1" />
        </el-form-item>
        <el-form-item label="地址2" prop="addr2">
          <el-input v-model="form.addr2" placeholder="请输入地址2" />
        </el-form-item>
        <el-form-item label="地址3" prop="addr3">
          <el-input v-model="form.addr3" placeholder="请输入地址3" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phoneNo">
          <el-input v-model="form.phoneNo" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="邮编" prop="zipCd">
          <el-input v-model="form.zipCd" placeholder="请输入邮编" />
        </el-form-item>
        <el-form-item label="联系邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入联系邮箱" />
        </el-form-item>
        <el-form-item label="传真号" prop="faxNo">
          <el-input v-model="form.faxNo" placeholder="请输入传真号" />
        </el-form-item>
        <el-form-item label="负责人" prop="respPerson">
          <el-input v-model="form.respPerson" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="区域" prop="area">
          <el-input v-model="form.area" placeholder="请输入区域" />
        </el-form-item>
        <el-form-item label="租赁费用" prop="rentalFee">
          <el-input-number v-model="form.rentalFee" placeholder="请输入租赁费用" :precision="2"/>
        </el-form-item>
        <el-form-item label="存储费用" prop="storingFee">
          <el-input-number v-model="form.storingFee" placeholder="请输入存储费用" :precision="2"/>
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

<script setup name="WarehouseInfo">
import { listWarehouseInfo, getWarehouseInfo, delWarehouseInfo, addWarehouseInfo, updateWarehouseInfo } from "@/api/wms/WarehouseInfo";

const { proxy } = getCurrentInstance();

const WarehouseInfoList = ref([]);
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
    whsName: null,
  },
  rules: {
    whsName: [
      { required: true, message: "仓库名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询仓库基础信息列表 */
function getList() {
  loading.value = true;
  listWarehouseInfo(queryParams.value).then(response => {
    WarehouseInfoList.value = response.rows;
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
    whsName: null,
    whsTypeCd: null,
    abbr: null,
    addr1: null,
    addr2: null,
    addr3: null,
    phoneNo: null,
    zipCd: null,
    email: null,
    faxNo: null,
    respPerson: null,
    area: null,
    rentalFee: null,
    storingFee: null,
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
  proxy.resetForm("WarehouseInfoRef");
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
  ids.value = selection.map(item => item.whsCd);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加仓库基础信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _whsCd = row.whsCd || ids.value
  getWarehouseInfo(_whsCd).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改仓库基础信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["WarehouseInfoRef"].validate(valid => {
    if (valid) {
      if (form.value.whsCd != null) {
        updateWarehouseInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addWarehouseInfo(form.value).then(response => {
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
  const _whsCds = row.whsCd || ids.value;
  proxy.$modal.confirm('是否确认删除仓库基础信息编号为"' + _whsCds + '"的数据项？').then(function() {
    return delWarehouseInfo(_whsCds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('wms/WarehouseInfo/export', {
    ...queryParams.value
  }, `WarehouseInfo_${new Date().getTime()}.xlsx`)
}

//页面打开时查询
//getList();
</script>
