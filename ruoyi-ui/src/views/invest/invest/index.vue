<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业id" prop="firmId">
        <el-input
          v-model="queryParams.firmId"
          placeholder="请输入企业id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投资金额" prop="investAmount">
        <el-input
          v-model="queryParams.investAmount"
          placeholder="请输入投资金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投资收益" prop="investYield">
        <el-input
          v-model="queryParams.investYield"
          placeholder="请输入投资收益"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投资状态0:手动投资1:自动投资" prop="investState">
        <el-input
          v-model="queryParams.investState"
          placeholder="请输入投资状态0:手动投资1:自动投资"
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
          v-hasPermi="['system:invest:add']"
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
          v-hasPermi="['system:invest:edit']"
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
          v-hasPermi="['system:invest:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:invest:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="investList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="投资id" align="center" prop="investId" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="企业id" align="center" prop="firmId" />
      <el-table-column label="投资金额" align="center" prop="investAmount" />
      <el-table-column label="投资收益" align="center" prop="investYield" />
      <el-table-column label="投资状态0:手动投资1:自动投资" align="center" prop="investState" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:invest:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:invest:remove']"
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

    <!-- 添加或修改投资对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="企业id" prop="firmId">
          <el-input v-model="form.firmId" placeholder="请输入企业id" />
        </el-form-item>
        <el-form-item label="投资金额" prop="investAmount">
          <el-input v-model="form.investAmount" placeholder="请输入投资金额" />
        </el-form-item>
        <el-form-item label="投资收益" prop="investYield">
          <el-input v-model="form.investYield" placeholder="请输入投资收益" />
        </el-form-item>
        <el-form-item label="投资状态0:手动投资1:自动投资" prop="investState">
          <el-input v-model="form.investState" placeholder="请输入投资状态0:手动投资1:自动投资" />
        </el-form-item>
        <el-form-item label="删除状态0:存在2:删除" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除状态0:存在2:删除" />
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
import { listInvest, getInvest, delInvest, addInvest, updateInvest } from "@/api/invest/invest";

export default {
  name: "Invest",
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
      // 投资表格数据
      investList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        firmId: null,
        investAmount: null,
        investYield: null,
        investState: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询投资列表 */
    getList() {
      this.loading = true;
      listInvest(this.queryParams).then(response => {
        this.investList = response.rows;
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
        investId: null,
        userId: null,
        firmId: null,
        investAmount: null,
        investYield: null,
        investState: null,
        delFlag: null,
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null
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
      this.ids = selection.map(item => item.investId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加投资";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const investId = row.investId || this.ids
      getInvest(investId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改投资";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.investId != null) {
            updateInvest(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInvest(this.form).then(response => {
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
      const investIds = row.investId || this.ids;
      this.$modal.confirm('是否确认删除投资编号为"' + investIds + '"的数据项？').then(function() {
        return delInvest(investIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/invest/export', {
        ...this.queryParams
      }, `invest_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
