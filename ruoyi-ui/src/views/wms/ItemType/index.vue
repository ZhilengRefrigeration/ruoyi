<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="物品类型名称" prop="itemTypeName">
        <el-input
          v-model="queryParams.itemTypeName"
          placeholder="请输入物品类型名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注" prop="remark1">
        <el-input
          v-model="queryParams.remark1"
          placeholder="请输入备注"
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
          v-hasPermi="['wms:ItemType:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['wms:ItemType:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['wms:ItemType:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['wms:ItemType:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ItemTypeList" @selection-change="handleSelectionChange" show-overflow-tooltip="true">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="物品类型编码" align="center" prop="itemTypeCd" />
      <el-table-column label="物品类型名称" align="center" prop="itemTypeName" />
      <el-table-column label="备注" align="center" prop="remark1" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['wms:ItemType:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['wms:ItemType:remove']">删除</el-button>
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

    <!-- 添加或修改物品类型对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="ItemTypeRef" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="物品类型名称" prop="itemTypeName">
          <el-input v-model="form.itemTypeName" placeholder="请输入物品类型名称" />
        </el-form-item>
        <el-form-item label="备注" prop="remark1">
          <el-input v-model="form.remark1" placeholder="请输入备注" />
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

<script setup name="ItemType">
import { listItemType, getItemType, delItemType, addItemType, updateItemType } from "@/api/wms/ItemType";

const { proxy } = getCurrentInstance();

const ItemTypeList = ref([]);
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
    itemTypeName: null,
    remark1: null,
  },
  rules: {
    deptId: [
      { required: true, message: "从属部门ID不能为空", trigger: "blur" }
    ],
    itemTypeName: [
      { required: true, message: "物品类型名称不能为空", trigger: "blur" }
    ],
    updateCount: [
      { required: true, message: "更新次数不能为空", trigger: "blur" }
    ],
    deleteFlag: [
      { required: true, message: "更新次数不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询物品类型列表 */
function getList() {
  loading.value = true;
  listItemType(queryParams.value).then(response => {
    ItemTypeList.value = response.rows;
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
    itemTypeCd: null,
    itemTypeName: null,
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
  proxy.resetForm("ItemTypeRef");
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
  ids.value = selection.map(item => item.itemTypeCd);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加物品类型";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _itemTypeCd = row.itemTypeCd || ids.value
  getItemType(_itemTypeCd).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改物品类型";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["ItemTypeRef"].validate(valid => {
    if (valid) {
      if (form.value.itemTypeCd != null) {
        updateItemType(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addItemType(form.value).then(response => {
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
  const _itemTypeCds = row.itemTypeCd || ids.value;
  proxy.$modal.confirm('是否确认删除物品类型编号为"' + _itemTypeCds + '"的数据项？').then(function() {
    return delItemType(_itemTypeCds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('wms/ItemType/export', {
    ...queryParams.value
  }, `ItemType_${new Date().getTime()}.xlsx`)
}

//页面打开时查询
//getList();
</script>
