<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="业务发生所属公司" prop="companyid">
        <el-input
          v-model="queryParams.companyid"
          placeholder="请输入业务发生所属公司"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="对应用户" prop="khuserid">
        <el-input
          v-model="queryParams.khuserid"
          placeholder="请输入对应用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务商用户id" prop="fwsuserid">
        <el-input
          v-model="queryParams.fwsuserid"
          placeholder="请输入服务商用户id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单总额" prop="money">
        <el-input
          v-model="queryParams.money"
          placeholder="请输入订单总额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="其中使用余额支付多少元" prop="yuepay">
        <el-input
          v-model="queryParams.yuepay"
          placeholder="请输入其中使用余额支付多少元"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="其中使用保障基金支付多少元" prop="fundpay">
        <el-input
          v-model="queryParams.fundpay"
          placeholder="请输入其中使用保障基金支付多少元"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="其中使用微信活支付宝支付多少元" prop="wxorcftpay">
        <el-input
          v-model="queryParams.wxorcftpay"
          placeholder="请输入其中使用微信活支付宝支付多少元"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单号" prop="ordernum">
        <el-input
          v-model="queryParams.ordernum"
          placeholder="请输入订单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="支付时间" prop="paytime">
        <el-date-picker clearable
          v-model="queryParams.paytime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择支付时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="订单时间" prop="createdate">
        <el-date-picker clearable
          v-model="queryParams.createdate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择订单时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="支付状态 0未支付  1已支付" prop="paystate">
        <el-input
          v-model="queryParams.paystate"
          placeholder="请输入支付状态 0未支付  1已支付"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信或支付宝返回的交易号" prop="tradeNo">
        <el-input
          v-model="queryParams.tradeNo"
          placeholder="请输入微信或支付宝返回的交易号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商户号" prop="mchId">
        <el-input
          v-model="queryParams.mchId"
          placeholder="请输入商户号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优惠价 用户关联表id" prop="cuid">
        <el-input
          v-model="queryParams.cuid"
          placeholder="请输入优惠价 用户关联表id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用优惠价额度" prop="cumoney">
        <el-input
          v-model="queryParams.cumoney"
          placeholder="请输入使用优惠价额度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退款审核人id" prop="refundAuditUserid">
        <el-input
          v-model="queryParams.refundAuditUserid"
          placeholder="请输入退款审核人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退款金额" prop="refundMoney">
        <el-input
          v-model="queryParams.refundMoney"
          placeholder="请输入退款金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退款备注" prop="refundNote">
        <el-input
          v-model="queryParams.refundNote"
          placeholder="请输入退款备注"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退款单号" prop="refundNo">
        <el-input
          v-model="queryParams.refundNo"
          placeholder="请输入退款单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退款状态 1：已成功提交退款申请   2：退款成功  3：退款失败" prop="refundState">
        <el-input
          v-model="queryParams.refundState"
          placeholder="请输入退款状态 1：已成功提交退款申请   2：退款成功  3：退款失败"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退款时间" prop="refundTime">
        <el-date-picker clearable
          v-model="queryParams.refundTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择退款时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="退款微信返回时间" prop="refundSuccesstime">
        <el-date-picker clearable
          v-model="queryParams.refundSuccesstime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择退款微信返回时间">
        </el-date-picker>
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
          v-hasPermi="['xjt:order:add']"
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
          v-hasPermi="['xjt:order:edit']"
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
          v-hasPermi="['xjt:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['xjt:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="id" />
      <el-table-column label="业务发生所属公司" align="center" prop="companyid" />
      <el-table-column label="对应用户" align="center" prop="khuserid" />
      <el-table-column label="服务商用户id" align="center" prop="fwsuserid" />
      <el-table-column label="订单总额" align="center" prop="money" />
      <el-table-column label="其中使用余额支付多少元" align="center" prop="yuepay" />
      <el-table-column label="其中使用保障基金支付多少元" align="center" prop="fundpay" />
      <el-table-column label="其中使用微信活支付宝支付多少元" align="center" prop="wxorcftpay" />
      <el-table-column label="交易类型" align="center" prop="tradetype" />
      <el-table-column label="订单号" align="center" prop="ordernum" />
      <el-table-column label="支付时间" align="center" prop="paytime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.paytime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单时间" align="center" prop="createdate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="支付类型 0=余额支付  1=微信支付，2=支付宝支付  ，3=保障金支付  4：现金支付   10：余额+微信   20：余额+支付宝  13：保障金+微信   23=保障金+支付宝   30: 保障金+余额支付     130：保障金+微信+余额支付   230=保障金+支付宝+余额支付 43：保障基金+现金支付" align="center" prop="paytype" />
      <el-table-column label="支付状态 0未支付  1已支付" align="center" prop="paystate" />
      <el-table-column label="微信或支付宝返回的交易号" align="center" prop="tradeNo" />
      <el-table-column label="商户号" align="center" prop="mchId" />
      <el-table-column label="微信或支付宝返回的交易状态" align="center" prop="tradeStatus" />
      <el-table-column label="优惠价 用户关联表id" align="center" prop="cuid" />
      <el-table-column label="使用优惠价额度" align="center" prop="cumoney" />
      <el-table-column label="退款审核人id" align="center" prop="refundAuditUserid" />
      <el-table-column label="退款金额" align="center" prop="refundMoney" />
      <el-table-column label="退款备注" align="center" prop="refundNote" />
      <el-table-column label="退款单号" align="center" prop="refundNo" />
      <el-table-column label="退款状态 1：已成功提交退款申请   2：退款成功  3：退款失败" align="center" prop="refundState" />
      <el-table-column label="退款时间" align="center" prop="refundTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.refundTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="退款微信返回时间" align="center" prop="refundSuccesstime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.refundSuccesstime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['xjt:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['xjt:order:remove']"
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

    <!-- 添加或修改缴费订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="业务发生所属公司" prop="companyid">
          <el-input v-model="form.companyid" placeholder="请输入业务发生所属公司" />
        </el-form-item>
        <el-form-item label="对应用户" prop="khuserid">
          <el-input v-model="form.khuserid" placeholder="请输入对应用户" />
        </el-form-item>
        <el-form-item label="服务商用户id" prop="fwsuserid">
          <el-input v-model="form.fwsuserid" placeholder="请输入服务商用户id" />
        </el-form-item>
        <el-form-item label="订单总额" prop="money">
          <el-input v-model="form.money" placeholder="请输入订单总额" />
        </el-form-item>
        <el-form-item label="其中使用余额支付多少元" prop="yuepay">
          <el-input v-model="form.yuepay" placeholder="请输入其中使用余额支付多少元" />
        </el-form-item>
        <el-form-item label="其中使用保障基金支付多少元" prop="fundpay">
          <el-input v-model="form.fundpay" placeholder="请输入其中使用保障基金支付多少元" />
        </el-form-item>
        <el-form-item label="其中使用微信活支付宝支付多少元" prop="wxorcftpay">
          <el-input v-model="form.wxorcftpay" placeholder="请输入其中使用微信活支付宝支付多少元" />
        </el-form-item>
        <el-form-item label="订单号" prop="ordernum">
          <el-input v-model="form.ordernum" placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="支付时间" prop="paytime">
          <el-date-picker clearable
            v-model="form.paytime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择支付时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订单时间" prop="createdate">
          <el-date-picker clearable
            v-model="form.createdate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择订单时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="支付状态 0未支付  1已支付" prop="paystate">
          <el-input v-model="form.paystate" placeholder="请输入支付状态 0未支付  1已支付" />
        </el-form-item>
        <el-form-item label="微信或支付宝返回的交易号" prop="tradeNo">
          <el-input v-model="form.tradeNo" placeholder="请输入微信或支付宝返回的交易号" />
        </el-form-item>
        <el-form-item label="商户号" prop="mchId">
          <el-input v-model="form.mchId" placeholder="请输入商户号" />
        </el-form-item>
        <el-form-item label="优惠价 用户关联表id" prop="cuid">
          <el-input v-model="form.cuid" placeholder="请输入优惠价 用户关联表id" />
        </el-form-item>
        <el-form-item label="使用优惠价额度" prop="cumoney">
          <el-input v-model="form.cumoney" placeholder="请输入使用优惠价额度" />
        </el-form-item>
        <el-form-item label="退款审核人id" prop="refundAuditUserid">
          <el-input v-model="form.refundAuditUserid" placeholder="请输入退款审核人id" />
        </el-form-item>
        <el-form-item label="退款金额" prop="refundMoney">
          <el-input v-model="form.refundMoney" placeholder="请输入退款金额" />
        </el-form-item>
        <el-form-item label="退款备注" prop="refundNote">
          <el-input v-model="form.refundNote" placeholder="请输入退款备注" />
        </el-form-item>
        <el-form-item label="退款单号" prop="refundNo">
          <el-input v-model="form.refundNo" placeholder="请输入退款单号" />
        </el-form-item>
        <el-form-item label="退款状态 1：已成功提交退款申请   2：退款成功  3：退款失败" prop="refundState">
          <el-input v-model="form.refundState" placeholder="请输入退款状态 1：已成功提交退款申请   2：退款成功  3：退款失败" />
        </el-form-item>
        <el-form-item label="退款时间" prop="refundTime">
          <el-date-picker clearable
            v-model="form.refundTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择退款时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="退款微信返回时间" prop="refundSuccesstime">
          <el-date-picker clearable
            v-model="form.refundSuccesstime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择退款微信返回时间">
          </el-date-picker>
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
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/xjt/order";

export default {
  name: "Order",
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
      // 缴费订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        companyid: null,
        khuserid: null,
        fwsuserid: null,
        money: null,
        yuepay: null,
        fundpay: null,
        wxorcftpay: null,
        tradetype: null,
        ordernum: null,
        paytime: null,
        createdate: null,
        paytype: null,
        paystate: null,
        tradeNo: null,
        mchId: null,
        tradeStatus: null,
        cuid: null,
        cumoney: null,
        refundAuditUserid: null,
        refundMoney: null,
        refundNote: null,
        refundNo: null,
        refundState: null,
        refundTime: null,
        refundSuccesstime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        khuserid: [
          { required: true, message: "对应用户不能为空", trigger: "blur" }
        ],
        fwsuserid: [
          { required: true, message: "服务商用户id不能为空", trigger: "blur" }
        ],
        money: [
          { required: true, message: "订单总额不能为空", trigger: "blur" }
        ],
        tradetype: [
          { required: true, message: "交易类型不能为空", trigger: "change" }
        ],
        ordernum: [
          { required: true, message: "订单号不能为空", trigger: "blur" }
        ],
        createdate: [
          { required: true, message: "订单时间不能为空", trigger: "blur" }
        ],
        paytype: [
          { required: true, message: "支付类型 0=余额支付  1=微信支付，2=支付宝支付  ，3=保障金支付  4：现金支付   10：余额+微信   20：余额+支付宝  13：保障金+微信   23=保障金+支付宝   30: 保障金+余额支付     130：保障金+微信+余额支付   230=保障金+支付宝+余额支付 43：保障基金+现金支付不能为空", trigger: "change" }
        ],
        paystate: [
          { required: true, message: "支付状态 0未支付  1已支付不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询缴费订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
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
        companyid: null,
        khuserid: null,
        fwsuserid: null,
        money: null,
        yuepay: null,
        fundpay: null,
        wxorcftpay: null,
        tradetype: null,
        ordernum: null,
        paytime: null,
        createdate: null,
        paytype: null,
        paystate: null,
        tradeNo: null,
        mchId: null,
        tradeStatus: "0",
        cuid: null,
        cumoney: null,
        refundAuditUserid: null,
        refundMoney: null,
        refundNote: null,
        refundNo: null,
        refundState: null,
        refundTime: null,
        refundSuccesstime: null
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
      this.title = "添加缴费订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改缴费订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除缴费订单编号为"' + ids + '"的数据项？').then(function() {
        return delOrder(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('xjt/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
