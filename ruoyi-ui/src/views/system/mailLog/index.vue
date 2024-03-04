<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="发送状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择发送状态" clearable>
          <el-option
              v-for="dict in sys_mail_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="收件人" prop="to">
        <el-input
            v-model="queryParams.to"
            placeholder="请输入收件人"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮件主题" prop="subject">
        <el-input
            v-model="queryParams.subject"
            placeholder="请输入邮件主题"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发送时间" style="width: 308px">
        <el-date-picker
            v-model="daterangeCreateTime"
            value-format="YYYY-MM-DD"
            type="daterange"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
        ></el-date-picker>
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
            type="primary"
            plain
            icon="Plus"
            @click="handleSend"
            v-hasPermi="['system:mailLog:send']"
        >临时邮件发送
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:mailLog:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['system:mailLog:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="mailLogList" @selection-change="handleSelectionChange"
              :show-overflow-tooltip="true">
      <el-table-column type="selection" width="30" align="center"/>
      <el-table-column label="日志ID" align="center" prop="mailLogId"/>
      <el-table-column label="发送状态" align="center" prop="status">
        <template #default="scope">
          <dict-tag :options="sys_mail_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="自定业务类型" align="center" prop="businessType"/>
      <el-table-column label="发件人" align="center" prop="from"/>
      <el-table-column label="收件人" align="center" prop="to"/>
      <el-table-column label="抄送" align="center" prop="cc"/>
      <el-table-column label="邮件主题" align="center" prop="subject"/>
      <el-table-column label="日志消息" align="center" prop="msg"/>
      <el-table-column label="发送时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作用户" align="center" prop="createByName"/>
      <el-table-column label="消耗时间(ms)" align="center" prop="costTime"/>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 临时邮件发送对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="mailLogRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="发件人" prop="from">
          <el-input v-model="form.from" placeholder="请输入发件人" disabled/>
        </el-form-item>
        <el-form-item label="收件人" prop="to">
          <el-input v-model="form.to" placeholder="请输入收件人，多个用英文分号;分隔"/>
        </el-form-item>
        <el-form-item label="抄送人" prop="cc">
          <el-input v-model="form.cc" placeholder="请输入抄送人，多个用英文分号;分隔"/>
        </el-form-item>
        <el-form-item label="邮件主题" prop="subject">
          <el-input v-model="form.subject" placeholder="请输入邮件主题"/>
        </el-form-item>
        <el-form-item label="邮件正文" prop="content">
          <el-input type="textarea" v-model="form.content" placeholder="请输入邮件正文"/>
        </el-form-item>
        <el-form-item label="附件" prop="attachmentsId">
          <fp-file-upload
              ref="fileUploadRef"
              style="width: 100%"
              :max-files-limit="5"
          />
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

<script setup name="MailLog">
import {listMailLog, delMailLog, sendTemporality, getMailSenderInfo} from "@/api/system/mailLog";

const {proxy} = getCurrentInstance();
const {sys_mail_status} = proxy.useDict('sys_mail_status');

const mailLogList = ref([]);
const open = ref(false);
const loading = ref(false);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const daterangeCreateTime = ref([]);
const senderAccount = ref({
  host: null,
  port: null,
  username: null,
});
const fileUploadRef = ref(null);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 30,
    status: null,
    to: null,
    subject: null,
    createTime: null,
  },
  rules: {
    from: [
      {required: true, message: "发件人不能为空", trigger: "change"},
    ],
    to: [
      {required: true, message: "收件人不能为空", trigger: "change"},
    ],
    subject: [
      {required: true, message: "邮件主题不能为空", trigger: "change"},
    ],
  }
});

const {queryParams, form, rules} = toRefs(data);

/** 查询邮件发送日志列表 */
function getList() {
  loading.value = true;
  queryParams.value.params = {};
  if (null != daterangeCreateTime && '' != daterangeCreateTime) {
    queryParams.value.params["beginCreateTime"] = daterangeCreateTime.value[0];
    queryParams.value.params["endCreateTime"] = daterangeCreateTime.value[1];
  }
  listMailLog(queryParams.value).then(response => {
    mailLogList.value = response.rows;
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
    mailLogId: null,
    status: null,
    businessType: null,
    from: null,
    to: null,
    cc: null,
    subject: null,
    content: null,
    attachmentsId: null,
  };
  if (fileUploadRef.value) {
    fileUploadRef.value.removeFiles();
  }
  proxy.resetForm("mailLogRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  daterangeCreateTime.value = [];
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.mailLogId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 临时邮件发送按钮操作 */
function handleSend() {
  reset();
  open.value = true;
  title.value = "临时邮件发送";
  form.value.from = senderAccount.value.username;
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["mailLogRef"].validate(valid => {
    if (valid) {
      const files = fileUploadRef.value.getFiles();
      const submitFiles = [];
      for (let i = 0; i < files.length; i++) {
        submitFiles.push({
          key: 'attachments',
          value: files[i],
        })
      }
      sendTemporality(form.value, submitFiles)
          .then(() => {
            proxy.$modal.msgSuccess("发送成功");
          })
          .finally(() => {
            open.value = false;
            getList();
          });
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _mailLogIds = row.mailLogId || ids.value;
  proxy.$modal.confirm('是否确认删除邮件发送日志编号为"' + _mailLogIds + '"的数据项？').then(function () {
    return delMailLog(_mailLogIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/mailLog/export', {
    ...queryParams.value
  }, `mailLog_${new Date().getTime()}.xlsx`)
}

/** 获取发件人账号 */
function getMailSenderAccount() {
  getMailSenderInfo().then(response => {
    senderAccount.value = response.data;
  });
}

// 页面打开时查询发送者账号
getMailSenderAccount();

// 页面打开时查询
//getList();
</script>
