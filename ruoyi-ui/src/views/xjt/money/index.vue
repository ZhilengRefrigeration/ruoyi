<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="对应用户" prop="userid">
        <el-input
          v-model="queryParams.userid"
          placeholder="请输入对应用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="金额" prop="money">
        <el-input
          v-model="queryParams.money"
          placeholder="请输入金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交易时间" prop="tradedate">
        <el-date-picker clearable
          v-model="queryParams.tradedate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择交易时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="处理后剩余金额" prop="leftmoney">
        <el-input
          v-model="queryParams.leftmoney"
          placeholder="请输入处理后剩余金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单id" prop="orderid">
        <el-input
          v-model="queryParams.orderid"
          placeholder="请输入订单id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态1=正常，2=删除" prop="state">
        <el-input
          v-model="queryParams.state"
          placeholder="请输入状态1=正常，2=删除"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="bbsmall t_orderid表 id" prop="bbsmallorderid">
        <el-input
          v-model="queryParams.bbsmallorderid"
          placeholder="请输入bbsmall t_orderid表 id"
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
          v-hasPermi="['xjt:money:add']"
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
          v-hasPermi="['xjt:money:edit']"
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
          v-hasPermi="['xjt:money:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['xjt:money:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="moneyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="id" />
      <el-table-column label="对应用户" align="center" prop="userid" />
      <el-table-column label="金额" align="center" prop="money" />
      <el-table-column label="交易类型 2：保养   4:进货 7：救援 10：维修厂营销卡  40：后台操作 50:提现  60：牛牛币返利 70：商城服务单  80:聚乐贝兑换 0：充值" align="center" prop="tradetype" />
      <el-table-column label="交易时间" align="center" prop="tradedate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.tradedate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="获取与扣除1=获取，2=扣除 " align="center" prop="type" />
      <el-table-column label="备注" align="center" prop="note" />
      <el-table-column label="处理后剩余金额" align="center" prop="leftmoney" />
      <el-table-column label="订单id" align="center" prop="orderid" />
      <el-table-column label="状态1=正常，2=删除" align="center" prop="state" />
      <el-table-column label="bbsmall t_orderid表 id" align="center" prop="bbsmallorderid" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['xjt:money:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['xjt:money:remove']"
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

    <!-- 添加或修改支出或收入详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="对应用户" prop="userid">
          <el-input v-model="form.userid" placeholder="请输入对应用户" />
        </el-form-item>
        <el-form-item label="金额" prop="money">
          <el-input v-model="form.money" placeholder="请输入金额" />
        </el-form-item>
        <el-form-item label="交易时间" prop="tradedate">
          <el-date-picker clearable
            v-model="form.tradedate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择交易时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="note">
          <el-input v-model="form.note" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="处理后剩余金额" prop="leftmoney">
          <el-input v-model="form.leftmoney" placeholder="请输入处理后剩余金额" />
        </el-form-item>
        <el-form-item label="订单id" prop="orderid">
          <el-input v-model="form.orderid" placeholder="请输入订单id" />
        </el-form-item>
        <el-form-item label="状态1=正常，2=删除" prop="state">
          <el-input v-model="form.state" placeholder="请输入状态1=正常，2=删除" />
        </el-form-item>
        <el-form-item label="bbsmall t_orderid表 id" prop="bbsmallorderid">
          <el-input v-model="form.bbsmallorderid" placeholder="请输入bbsmall t_orderid表 id" />
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
import { listMoney, getMoney, delMoney, addMoney, updateMoney } from "@/api/xjt/money";

export default {
  name: "Money",
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
      // 支出或收入详情表格数据
      moneyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userid: null,
        money: null,
        tradetype: null,
        tradedate: null,
        type: null,
        note: null,
        leftmoney: null,
        orderid: null,
        state: null,
        bbsmallorderid: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userid: [
          { required: true, message: "对应用户不能为空", trigger: "blur" }
        ],
        money: [
          { required: true, message: "金额不能为空", trigger: "blur" }
        ],
        tradetype: [
          { required: true, message: "交易类型 2：保养   4:进货 7：救援 10：维修厂营销卡  40：后台操作 50:提现  60：牛牛币返利 70：商城服务单  80:聚乐贝兑换 0：充值不能为空", trigger: "change" }
        ],
        tradedate: [
          { required: true, message: "交易时间不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "获取与扣除1=获取，2=扣除 不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询支出或收入详情列表 */
    getList() {
      this.loading = true;
      listMoney(this.queryParams).then(response => {
        this.moneyList = response.rows;
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
        id: null,
        userid: null,
        money: null,
        tradetype: null,
        tradedate: null,
        type: null,
        note: null,
        leftmoney: null,
        orderid: null,
        state: null,
        bbsmallorderid: null
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加支出或收入详情";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMoney(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改支出或收入详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMoney(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMoney(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除支出或收入详情编号为"' + ids + '"的数据项？').then(function() {
        return delMoney(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('xjt/money/export', {
        ...this.queryParams
      }, `money_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
