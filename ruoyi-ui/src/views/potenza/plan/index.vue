<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名称" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="共几期" prop="borrowerPeriods">
        <el-input
          v-model="queryParams.borrowerPeriods"
          placeholder="请输入共几期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="每期应还金额" prop="periodsMoney">
        <el-input
          v-model="queryParams.periodsMoney"
          placeholder="请输入每期应还金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="月供本金" prop="periodsCapital">
        <el-input
          v-model="queryParams.periodsCapital"
          placeholder="请输入月供本金"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="月供利息" prop="periodsInterests">
        <el-input
          v-model="queryParams.periodsInterests"
          placeholder="请输入月供利息"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计划还款日期" prop="planDate">
        <el-date-picker clearable
          v-model="queryParams.planDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择计划还款日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="0:本月未还   1:提前还款  2:按时还款 3:逾期还款" prop="planState">
        <el-input
          v-model="queryParams.planState"
          placeholder="请输入0:本月未还   1:提前还款  2:按时还款 3:逾期还款"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['potenza:plan:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['potenza:plan:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['potenza:plan:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['potenza:plan:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="还款结果ID" align="center" prop="planId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="用户名称" align="center" prop="userName" />
      <el-table-column label="共几期" align="center" prop="borrowerPeriods" />
      <el-table-column label="每期应还金额" align="center" prop="periodsMoney" />
      <el-table-column label="月供本金" align="center" prop="periodsCapital" />
      <el-table-column label="月供利息" align="center" prop="periodsInterests" />
      <el-table-column label="计划还款日期" align="center" prop="planDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.planDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="0:本月未还   1:提前还款  2:按时还款 3:逾期还款" align="center" prop="planState" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['potenza:plan:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['potenza:plan:remove']"
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

    <!-- 添加或修改计划对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="用户名称" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名称" />
        </el-form-item>
        <el-form-item label="共几期" prop="borrowerPeriods">
          <el-input v-model="form.borrowerPeriods" placeholder="请输入共几期" />
        </el-form-item>
        <el-form-item label="每期应还金额" prop="periodsMoney">
          <el-input v-model="form.periodsMoney" placeholder="请输入每期应还金额" />
        </el-form-item>
        <el-form-item label="月供本金" prop="periodsCapital">
          <el-input v-model="form.periodsCapital" placeholder="请输入月供本金" />
        </el-form-item>
        <el-form-item label="月供利息" prop="periodsInterests">
          <el-input v-model="form.periodsInterests" placeholder="请输入月供利息" />
        </el-form-item>
        <el-form-item label="计划还款日期" prop="planDate">
          <el-date-picker clearable
            v-model="form.planDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择计划还款日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="0:本月未还   1:提前还款  2:按时还款 3:逾期还款" prop="planState">
          <el-input v-model="form.planState" placeholder="请输入0:本月未还   1:提前还款  2:按时还款 3:逾期还款" />
        </el-form-item>
        <el-form-item label="删除状态0：存在，2：删除" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除状态0：存在，2：删除" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPlan, getPlan, delPlan, addPlan, updatePlan } from "@/api/potenza/plan";

export default {
  name: "Plan",
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
      // 计划表格数据
      planList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        userName: null,
        borrowerPeriods: null,
        periodsMoney: null,
        periodsCapital: null,
        periodsInterests: null,
        planDate: null,
        planState: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
        userName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" }
        ],
        borrowerPeriods: [
          { required: true, message: "共几期不能为空", trigger: "blur" }
        ],
        periodsMoney: [
          { required: true, message: "每期应还金额不能为空", trigger: "blur" }
        ],
        periodsCapital: [
          { required: true, message: "月供本金不能为空", trigger: "blur" }
        ],
        periodsInterests: [
          { required: true, message: "月供利息不能为空", trigger: "blur" }
        ],
        planDate: [
          { required: true, message: "计划还款日期不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询计划列表 */
    getList() {
      this.loading = true;
      listPlan(this.queryParams).then(response => {
        this.planList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        planId: null,
        userId: null,
        userName: null,
        borrowerPeriods: null,
        periodsMoney: null,
        periodsCapital: null,
        periodsInterests: null,
        planDate: null,
        planState: null,
        delFlag: null,
        createTime: null,
        updateTime: null
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
      this.ids = selection.map(item => item.planId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加计划";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const planId = row.planId || this.ids
      getPlan(planId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改计划";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.planId != null) {
            updatePlan(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPlan(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const planIds = row.planId || this.ids;
      this.$modal.confirm('是否确认删除计划编号为"' + planIds + '"的数据项？').then(function() {
        return delPlan(planIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('potenza/plan/export', {
        ...this.queryParams
      }, `plan_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
