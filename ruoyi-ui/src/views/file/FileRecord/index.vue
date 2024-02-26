<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="85px">
      <el-form-item label="文件ID" prop="fileId">
        <el-input
          v-model="queryParams.fileId"
          placeholder="请输入文件ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="原始文件名" prop="originalName">
        <el-input
          v-model="queryParams.originalName"
          placeholder="请输入原始文件名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件后缀" prop="extension">
        <el-input
            v-model="queryParams.extension"
            placeholder="请输入文件后缀"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>


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
          v-hasPermi="['file:FileRecord:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['wms:FileRecord:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="FileRecordList" @selection-change="handleSelectionChange" :show-overflow-tooltip="true">
      <el-table-column type="selection" width="30" align="center" />
      <el-table-column label="文件ID" align="center" prop="fileId" />
      <el-table-column label="保存文件名" width="190" align="center" prop="savedName" />
      <el-table-column label="原始文件名" width="200" align="center" prop="originalName" />
      <el-table-column label="文件路径" align="center" prop="filePath" />
      <el-table-column label="文件后缀" align="center" prop="extension" />
      <el-table-column label="存储方式" align="center" prop="storageType" />
      <el-table-column label="文件URL" align="center" prop="requestUrl" />
      <el-table-column label="文件大小(Byte)" align="center" prop="fileSize" />
      <el-table-column label="创建者ID" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者ID" align="center" prop="updateBy" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
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
  </div>
</template>

<script setup name="FileRecord">
import { listFileRecord, delFileRecord } from "@/api/file/FileRecord";

const { proxy } = getCurrentInstance();

const FileRecordList = ref([]);
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
    fileId: null,
    savedName: null,
    originalName: null,
    extension: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询文件存储记录列表 */
function getList() {
  loading.value = true;
  listFileRecord(queryParams.value).then(response => {
    FileRecordList.value = response.rows;
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
    fileId: null,
    savedName: null,
    originalName: null,
    filePath: null,
    extension: null,
    storageType: null,
    requestUrl: null,
    fileSize: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  };
  proxy.resetForm("FileRecordRef");
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
  ids.value = selection.map(item => item.fileId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _fileIds = row.fileId || ids.value;
  proxy.$modal.confirm('是否确认删除文件ID为"' + _fileIds + '"的数据项？').then(function() {
    return delFileRecord(_fileIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('file/FileRecord/export', {
    ...queryParams.value
  }, `FileRecord_${new Date().getTime()}.xlsx`)
}

//页面打开时查询
//getList();
</script>
