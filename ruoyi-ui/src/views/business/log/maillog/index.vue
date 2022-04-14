<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="邮件主题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入邮件主题"
          clearable
          maxlength="20"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="邮件内容" prop="content">
        <el-input
          v-model="queryParams.content"
          placeholder="请输入邮件内容"
          clearable
          maxlength="20"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="收件人" prop="recipient">
        <el-input
          v-model="queryParams.recipient"
          placeholder="请输入收件人"
          clearable
          maxlength="20"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          size="small"
          :picker-options="pickerOptions"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          @change="dateQuery('queryForm')"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['log:maillog:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['log:maillog:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="maillogList" @selection-change="handleSelectionChange" border>
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="邮件主题" align="center" prop="title" :show-overflow-tooltip="true"/>
      <el-table-column label="邮件内容" align="center" prop="content" :show-overflow-tooltip="true"/>
      <el-table-column label="收件人" align="center" prop="recipient" :show-overflow-tooltip="true"/>
      <el-table-column label="邮件类型" align="center" prop="mailType" :show-overflow-tooltip="true"/>
      <el-table-column label="请求时间" align="center" prop="requestTime">
        <template slot-scope="scope">
          <span>{{
              scope.row.requestTime < 1000
                ?
                scope.row.requestTime + '毫秒'
                :
                Math.round(scope.row.requestTime / 1000) + '秒'
            }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" :show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['log:maillog:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />


  </div>
</template>

<script>
import {listMaillog, delMaillog,} from "@/api/business/log/maillog";
import {pickerOptions} from "@/layout/mixin/PickerOptions";

export default {
  mixins: [pickerOptions],
  name: "Maillog",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 邮件日志表格数据
      maillogList: [],
      // 创建时间时间范围
      daterangeCreateTime: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        content: null,
        recipient: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询邮件日志列表 */
    getList() {
      this.loading = true;
      if (null != this.daterangeCreateTime && '' !== this.daterangeCreateTime) {
        this.queryParams.createTime = this.daterangeCreateTime[0];
        this.queryParams.endCreateTime = this.daterangeCreateTime[1];
      }
      listMaillog(this.queryParams).then(response => {
        this.maillogList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    dateQuery(formName) {
      //清空时间参数
      this.queryParams.createTime=null
      this.queryParams.endCreateTime=null

      this.handleQuery(formName);
    },

    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        content: null,
        recipient: null,
        mailType: null,
        requestTime: null,
        createTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },


    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除邮件日志编号为"' + ids + '"的数据项？').then(function () {
        return delMaillog(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('log/maillog/export', {
        ...this.queryParams
      }, `maillog_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
