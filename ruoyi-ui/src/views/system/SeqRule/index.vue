<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="序列号识别码" prop="seqDistCd">
        <el-input
          v-model="queryParams.seqDistCd"
          placeholder="请输入序列号识别码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规则名称" prop="ruleName">
        <el-input
          v-model="queryParams.ruleName"
          placeholder="请输入规则名称"
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
          v-hasPermi="['system:SeqRule:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:SeqRule:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:SeqRule:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['system:SeqRule:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="SeqRuleList" @selection-change="handleSelectionChange" show-overflow-tooltip="true">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="规则ID" align="center" prop="ruleId" />
      <el-table-column label="序列号识别码" align="center" prop="seqDistCd" />
      <el-table-column label="规则名称" align="center" prop="ruleName" />
      <el-table-column label="前缀" align="center" prop="prefix" />
      <el-table-column label="分隔符1" align="center" prop="separator1" />
      <el-table-column label="日期格式" align="center" prop="dateFormat" />
      <el-table-column label="最小位数" align="center" prop="minDigits" />
      <el-table-column label="分隔符2" align="center" prop="separator2" />
      <el-table-column label="生成器名" align="center" prop="generatorName" />
      <el-table-column label="状态" align="center" width="100">
        <template #default="scope">
          <el-switch
              v-model="scope.row.enableFlag"
              :active-value="1"
              :inactive-value="0"
              @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:SeqRule:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['system:SeqRule:remove']">删除</el-button>
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

    <!-- 添加或修改序列号生成规则对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="SeqRuleRef" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="序列号识别码" prop="seqDistCd">
          <el-input v-model="form.seqDistCd" placeholder="请输入序列号识别码" />
        </el-form-item>
        <el-form-item label="规则名称" prop="ruleName">
          <el-input v-model="form.ruleName" placeholder="请输入规则名称" />
        </el-form-item>
        <el-form-item label="前缀" prop="prefix">
          <el-input v-model="form.prefix" placeholder="请输入前缀" />
        </el-form-item>
        <el-form-item label="分隔符1" prop="separator1">
          <el-input v-model="form.separator1" placeholder="请输入分隔符1" />
        </el-form-item>
        <el-form-item label="日期格式" prop="dateFormat">
          <el-input v-model="form.dateFormat" placeholder="请输入日期格式" />
        </el-form-item>
        <el-form-item label="最小位数" prop="minDigits">
          <el-input-number v-model="form.minDigits" placeholder="请输入最小位数" />
        </el-form-item>
        <el-form-item label="分隔符2" prop="separator2">
          <el-input v-model="form.separator2" placeholder="请输入分隔符2" />
        </el-form-item>
        <el-form-item label="生成器名" prop="generatorName">
          <el-input v-model="form.generatorName" placeholder="请输入生成器名" />
        </el-form-item>
        <el-form-item label="状态" prop="enableFlag">
          <el-radio-group v-model="form.enableFlag">
            <el-radio
                v-for="dict in sys_normal_disable"
                :key="dict.value"
                :label="dict.value"
            >{{ dict.label }}</el-radio>
          </el-radio-group>
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

<script setup name="SeqRule">
import { listSeqRule, getSeqRule, delSeqRule, addSeqRule, updateSeqRule, changeRuleEnableFlag } from "@/api/system/SeqRule";

const { proxy } = getCurrentInstance();
const { sys_normal_disable } = proxy.useDict("sys_normal_disable");

const SeqRuleList = ref([]);
const open = ref(false);
const loading = ref(false);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const onSelecting = ref(false)

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    seqDistCd: null,
    ruleName: null,
  },
  rules: {
    seqDistCd: [
      { required: true, message: "序列号识别码不能为空", trigger: "blur" }
    ],
    ruleName: [
      { required: true, message: "规则名称不能为空", trigger: "blur" }
    ],
    prefix: [
      { required: true, message: "前缀不能为空", trigger: "blur" }
    ],
    minDigits: [
      { required: true, message: "序列号数字部分的最小位数，不足补0不能为空", trigger: "blur" }
    ],
    enableFlag: [
      { required: true, message: "是否启用不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询序列号生成规则列表 */
function getList() {
  loading.value = true;
  onSelecting.value = true;
  listSeqRule(queryParams.value).then(response => {
    SeqRuleList.value = response.rows;
    total.value = response.total;
  }).finally(() => {
    loading.value = false;
    onSelecting.value = false;
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
    ruleId: null,
    seqDistCd: null,
    ruleName: null,
    prefix: null,
    separator1: null,
    dateFormat: null,
    minDigits: null,
    separator2: null,
    generatorName: null,
    enableFlag: null,
    remark1: null,
    remark2: null,
    remark3: null,
    remark4: null,
    remark5: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  };
  proxy.resetForm("SeqRuleRef");
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
  ids.value = selection.map(item => item.ruleId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加序列号生成规则";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _ruleId = row.ruleId || ids.value
  getSeqRule(_ruleId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改序列号生成规则";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["SeqRuleRef"].validate(valid => {
    if (valid) {
      if (form.value.ruleId != null) {
        updateSeqRule(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSeqRule(form.value).then(response => {
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
  const _ruleIds = row.ruleId || ids.value;
  proxy.$modal.confirm('是否确认删除序列号生成规则编号为"' + _ruleIds + '"的数据项？').then(function() {
    return delSeqRule(_ruleIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/SeqRule/export', {
    ...queryParams.value
  }, `SeqRule_${new Date().getTime()}.xlsx`)
}

/** 启用状态修改 */
function handleStatusChange(row) {
  if (onSelecting.value) {
    return
  }
  let text = row.enableFlag === "0" ? "启用" : "停用";
  proxy.$modal.confirm('确认要' + text + '规则吗?').then(() => {
    return changeRuleEnableFlag(row.ruleId, row.enableFlag);
  }).then(() => {
    proxy.$modal.msgSuccess(text + "成功");
  }).catch(() => {
    row.enableFlag = row.enableFlag === "0" ? "1" : "0";
  });
}

//页面打开时查询
//getList();
</script>
