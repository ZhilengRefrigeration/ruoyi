<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入任务名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          :picker-options="pickerOptions"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          @change="handleQuery"
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
          v-hasPermi="['log:taskLog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['log:taskLog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskLogList"
              :default-sort="defaultSort"
              @sort-change="handleSortChange"
              ref="tables"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务名称" align="center" prop="name" :show-overflow-tooltip="true"/>
      <el-table-column label="方法名" align="center" prop="method" :show-overflow-tooltip="true"/>
      <el-table-column label="类路径" align="center" prop="classPath" :show-overflow-tooltip="true"/>
      <el-table-column label="请求时间" align="center" prop="requestTime" :show-overflow-tooltip="true">
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
      <el-table-column label="创建时间"
                       align="center"
                       sortable="custom"
                       :sort-orders="['descending', 'ascending']"
                       prop="createTime" width="180" :show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['log:taskLog:remove']"
          >删除</el-button>
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
import { listTaskLog, delTaskLog } from "@/api/business/log/tasklog";
import {pickerOptions} from "@/layout/mixin/PickerOptions";

export default {
  name: "TaskLog",
  mixins: [pickerOptions],
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
      // 任务日志表格数据
      taskLogList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        isAsc: null,
        orderByColumn: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },

      //检查查询范围
      daterangeCreateTime: [],

      // 默认排序
      defaultSort: {prop: 'createTime', order: 'descending'},

      //标记排序重置（修复点击重置请求两次接口BUG）
      sortStatus: true,
    };
  },
  created() {
    this.resetSort()
    this.getList();
  },
  methods: {
    /** 查询任务日志列表 */
    getList() {
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.createTime = this.daterangeCreateTime[0];
        this.queryParams.endCreateTime = this.daterangeCreateTime[1];
      }
      this.loading = true;
      listTaskLog(this.queryParams).then(response => {
        this.loading = false;
        this.taskLogList = response.rows;
        this.total = response.total;
      });
    },

    //重置排序
    resetSort() {
      this.queryParams.isAsc = this.defaultSort.order
      this.queryParams.orderByColumn = this.defaultSort.prop
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetSort()
      this.daterangeCreateTime = [];
      this.queryParams.createTime = null
      this.queryParams.endCreateTime = null

      this.sortStatus=false
      this.$refs.tables.sort(this.defaultSort.prop, this.defaultSort.order)//拿到默认排序
      this.sortStatus=true

      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除任务日志编号为"' + ids + '"的数据项？').then(function() {
        return delTaskLog(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('log/taskLog/export', {
        ...this.queryParams
      }, `taskLog_${new Date().getTime()}.xlsx`)
    },

    /** 排序触发事件 */
    handleSortChange(column) {
      this.queryParams.isAsc = column.order;
      this.queryParams.orderByColumn = column.prop;


      //点击重置的时候不再次请求接口
      if (this.sortStatus) {
        this.getList();
      }
    },
  }
};
</script>
