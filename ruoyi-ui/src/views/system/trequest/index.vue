<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
     
      <el-form-item label="申请日期" prop="vcRequestDate">
        <el-date-picker clearable
          v-model="queryParams.vcRequestDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择申请日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="申请时间" prop="vcRequestTime">
        <el-date-picker clearable
          v-model="queryParams.vcRequestTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择申请时间">
        </el-date-picker>
      </el-form-item>
 
      <el-form-item label="申请金额" prop="enBalance">
        <el-input
          v-model="queryParams.enBalance"
          placeholder="请输入申请金额"
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
          v-hasPermi="['system:trequest:add']"
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
          v-hasPermi="['system:trequest:edit']"
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
          v-hasPermi="['system:trequest:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:trequest:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="trequestList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="租户id" align="center" prop="vcTenantId" /> -->
      <el-table-column label="申请编号" align="center" prop="vcRequestNo" />
      <el-table-column label="基金账号" align="center" prop="vcFundAcco" />
      <!-- <el-table-column label="交易账号" align="center" prop="vcTradeAcco" /> -->
      <el-table-column label="申请日期" align="center" prop="vcRequestDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.vcRequestDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请时间" align="center" prop="vcRequestTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.vcRequestTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="业务代码" align="center" prop="cBusinFlag" /> -->
      <el-table-column label="产品代码" align="center" prop="vcProductCode" />
      <!-- <el-table-column label="份额类别" align="center" prop="cShareType" /> -->
      <!-- <el-table-column label="委托方式" align="center" prop="cTrust" /> -->
      <el-table-column label="申请金额" align="center" prop="enBalance" />
      <!-- <el-table-column label="申请份额" align="center" prop="enShare" /> -->
      <el-table-column label="折扣率" align="center" prop="enDiscount" />
      <!-- <el-table-column label="后收费折扣" align="center" prop="enEndDiscount" /> -->
      <!-- <el-table-column label="补差费折扣" align="center" prop="enOtherDiscount" /> -->
      <!-- <el-table-column label="发送状态" align="center" prop="cSendState" /> -->
      <!-- <el-table-column label="申清校验" align="center" prop="cTradeState" /> -->
      <!-- <el-table-column label="确认标志" align="center" prop="cConfirmFlag" /> -->
      <!-- <el-table-column label="确认份额" align="center" prop="enConfirmShare" /> -->
      <!-- <el-table-column label="确认金额" align="center" prop="enConfirmBala" /> -->
      <!-- <el-table-column label="预约日期" align="center" prop="vcHopeDate" /> -->
      <!-- <el-table-column label="对方基金账号" align="center" prop="vcOtherAcco" /> -->
      <!-- <el-table-column label="与安对方交易账号" align="center" prop="vcOtherTradeAcco" /> -->
      <!-- <el-table-column label="对方产品代码" align="center" prop="vcOtherProductCode" /> -->
      <!-- <el-table-column label="对方份额类别" align="center" prop="cOtherShareType" /> -->
      <!-- <el-table-column label="对方销售商" align="center" prop="vcOtherAgencyNo" /> -->
      <!-- <el-table-column label="分红方式" align="center" prop="cDividendMethod" /> -->
      <!-- <el-table-column label="冻结标志" align="center" prop="cFrozenFlag" /> -->
      <!-- <el-table-column label="冻结原因" align="center" prop="cFrozenCause" /> -->
      <!-- <el-table-column label="资金方式" align="center" prop="cCapitalMode" /> -->
      <!-- <el-table-column label="明细资金方式" align="center" prop="cSubCapitalMode" /> -->
      <!-- <el-table-column label="代扣接口类型" align="center" prop="cInterfaceType" /> -->
      <!-- <el-table-column label="允许顺延天数" align="center" prop="lDelayDay" /> -->
      <!-- <el-table-column label="复核标志" align="center" prop="cAuditFlag" /> -->
      <!-- <el-table-column label="ta代码" align="center" prop="vcTaCode" /> -->
      <el-table-column label="下单日期" align="center" prop="vcAcceptDate" />
      <el-table-column label="下单时间" align="center" prop="vcAcceptTime" />
      <!-- <el-table-column label="审批标志" align="center" prop="cCheckFlag" /> -->
      <!-- <el-table-column label="客户确认标志" align="center" prop="cCustConfirm" /> -->
      <!-- <el-table-column label="业务辅助代码" align="center" prop="cFixBusinFlag" /> -->
      <!-- <el-table-column label="系统日期" align="center" prop="vcSysDate" /> -->
      <!-- <el-table-column label="机器日期" align="center" prop="vcMachineDate" /> -->
      <!-- <el-table-column label="机器时间" align="center" prop="vcMachineTime" /> -->
      <!-- <el-table-column label="批次号" align="center" prop="vcBatchNo" /> -->
      <!-- <el-table-column label="交易业务类别" align="center" prop="cTradeBusinType" /> -->
      <!-- <el-table-column label="交易来源" align="center" prop="vcComeFrom" /> -->
      <!-- <el-table-column label="银商流水号" align="center" prop="vcChinaPaySerialNo" /> -->
      <!-- <el-table-column label="自动发起标志" align="center" prop="cAutoRequest" /> -->
      <!-- <el-table-column label="子账户编号" align="center" prop="vcSubAccoNo" /> -->
      <!-- <el-table-column label="客户编号" align="center" prop="vcCustNo" /> -->
      <el-table-column label="批次申请号" align="center" prop="vcBatchRequestNo" />
      <!-- <el-table-column label="确认处理清算日期" align="center" prop="vcConfirmSettleDate" /> -->
      <!-- <el-table-column label="交易预处理清算日期" align="center" prop="vcRequestSettleDate" /> -->
      <!-- <el-table-column label="资金账号" align="center" prop="vcCapAcco" /> -->
      <!-- <el-table-column label="功能号" align="center" prop="vcFunctionNo" /> -->
      <!-- <el-table-column label="分库分表位" align="center" prop="lNode" /> -->
      <!-- <el-table-column label="导出状态" align="center" prop="cExportState" /> -->
      <!-- <el-table-column label="删除标志" align="center" prop="cDelete" /> -->
      <!-- <el-table-column label="时间戳" align="center" prop="vcTimestamp" /> -->
      <!-- <el-table-column label="对方流水号" align="center" prop="vcOtherSerialNo" /> -->
      <!-- <el-table-column label="${comment}" align="center" prop="cRecordState" /> -->
      <!-- <el-table-column label="${comment}" align="center" prop="vcLcsExportBatchNo" /> -->
      <!-- <el-table-column label="${comment}" align="center" prop="cSendLiqState" /> -->
      <!-- <el-table-column label="${comment}" align="center" prop="vcAuditDateTime" /> -->
      <!-- <el-table-column label="${comment}" align="center" prop="vcCheckDateTime" /> -->
      <!-- <el-table-column label="${comment}" align="center" prop="vcFaxNo" /> -->
      <!-- <el-table-column label="${comment}" align="center" prop="vcDelayCause" /> -->
      <!-- <el-table-column label="${comment}" align="center" prop="cDelayCode" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:trequest:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:trequest:remove']"
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

    <!-- 添加或修改交易申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="基金账号" prop="vcFundAcco">
          <el-input v-model="form.vcFundAcco" placeholder="请输入基金账号" />
        </el-form-item>
        <el-form-item label="交易账号" prop="vcTradeAcco">
          <el-input v-model="form.vcTradeAcco" placeholder="请输入交易账号" />
        </el-form-item>
        <el-form-item label="申请日期" prop="vcRequestDate">
          <el-date-picker clearable
            v-model="form.vcRequestDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择申请日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="申请时间" prop="vcRequestTime">
          <el-date-picker clearable
            v-model="form.vcRequestTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择申请时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="业务代码" prop="cBusinFlag">
          <el-input v-model="form.cBusinFlag" placeholder="请输入业务代码" />
        </el-form-item>
        <el-form-item label="产品代码" prop="vcProductCode">
          <el-input v-model="form.vcProductCode" placeholder="请输入产品代码" />
        </el-form-item>
        <el-form-item label="委托方式" prop="cTrust">
          <el-input v-model="form.cTrust" placeholder="请输入委托方式" />
        </el-form-item>
        <el-form-item label="申请金额" prop="enBalance">
          <el-input v-model="form.enBalance" placeholder="请输入申请金额" />
        </el-form-item>
        <el-form-item label="申请份额" prop="enShare">
          <el-input v-model="form.enShare" placeholder="请输入申请份额" />
        </el-form-item>
        <el-form-item label="折扣率" prop="enDiscount">
          <el-input v-model="form.enDiscount" placeholder="请输入折扣率" />
        </el-form-item>
        <el-form-item label="后收费折扣" prop="enEndDiscount">
          <el-input v-model="form.enEndDiscount" placeholder="请输入后收费折扣" />
        </el-form-item>
        <el-form-item label="补差费折扣" prop="enOtherDiscount">
          <el-input v-model="form.enOtherDiscount" placeholder="请输入补差费折扣" />
        </el-form-item>
        <el-form-item label="发送状态" prop="cSendState">
          <el-input v-model="form.cSendState" placeholder="请输入发送状态" />
        </el-form-item>
        <el-form-item label="申清校验" prop="cTradeState">
          <el-input v-model="form.cTradeState" placeholder="请输入申清校验" />
        </el-form-item>
        <el-form-item label="确认标志" prop="cConfirmFlag">
          <el-input v-model="form.cConfirmFlag" placeholder="请输入确认标志" />
        </el-form-item>
        <el-form-item label="确认份额" prop="enConfirmShare">
          <el-input v-model="form.enConfirmShare" placeholder="请输入确认份额" />
        </el-form-item>
        <el-form-item label="确认金额" prop="enConfirmBala">
          <el-input v-model="form.enConfirmBala" placeholder="请输入确认金额" />
        </el-form-item>
        <el-form-item label="预约日期" prop="vcHopeDate">
          <el-input v-model="form.vcHopeDate" placeholder="请输入预约日期" />
        </el-form-item>
        <el-form-item label="对方基金账号" prop="vcOtherAcco">
          <el-input v-model="form.vcOtherAcco" placeholder="请输入对方基金账号" />
        </el-form-item>
        <el-form-item label="与安对方交易账号" prop="vcOtherTradeAcco">
          <el-input v-model="form.vcOtherTradeAcco" placeholder="请输入与安对方交易账号" />
        </el-form-item>
        <el-form-item label="对方产品代码" prop="vcOtherProductCode">
          <el-input v-model="form.vcOtherProductCode" placeholder="请输入对方产品代码" />
        </el-form-item>
        <el-form-item label="对方销售商" prop="vcOtherAgencyNo">
          <el-input v-model="form.vcOtherAgencyNo" placeholder="请输入对方销售商" />
        </el-form-item>
        <el-form-item label="分红方式" prop="cDividendMethod">
          <el-input v-model="form.cDividendMethod" placeholder="请输入分红方式" />
        </el-form-item>
        <el-form-item label="冻结标志" prop="cFrozenFlag">
          <el-input v-model="form.cFrozenFlag" placeholder="请输入冻结标志" />
        </el-form-item>
        <el-form-item label="冻结原因" prop="cFrozenCause">
          <el-input v-model="form.cFrozenCause" placeholder="请输入冻结原因" />
        </el-form-item>
        <el-form-item label="资金方式" prop="cCapitalMode">
          <el-input v-model="form.cCapitalMode" placeholder="请输入资金方式" />
        </el-form-item>
        <el-form-item label="明细资金方式" prop="cSubCapitalMode">
          <el-input v-model="form.cSubCapitalMode" placeholder="请输入明细资金方式" />
        </el-form-item>
        <el-form-item label="允许顺延天数" prop="lDelayDay">
          <el-input v-model="form.lDelayDay" placeholder="请输入允许顺延天数" />
        </el-form-item>
        <el-form-item label="复核标志" prop="cAuditFlag">
          <el-input v-model="form.cAuditFlag" placeholder="请输入复核标志" />
        </el-form-item>
        <el-form-item label="ta代码" prop="vcTaCode">
          <el-input v-model="form.vcTaCode" placeholder="请输入ta代码" />
        </el-form-item>
        <el-form-item label="下单日期" prop="vcAcceptDate">
          <el-input v-model="form.vcAcceptDate" placeholder="请输入下单日期" />
        </el-form-item>
        <el-form-item label="下单时间" prop="vcAcceptTime">
          <el-input v-model="form.vcAcceptTime" placeholder="请输入下单时间" />
        </el-form-item>
        <el-form-item label="审批标志" prop="cCheckFlag">
          <el-input v-model="form.cCheckFlag" placeholder="请输入审批标志" />
        </el-form-item>
        <el-form-item label="客户确认标志" prop="cCustConfirm">
          <el-input v-model="form.cCustConfirm" placeholder="请输入客户确认标志" />
        </el-form-item>
        <el-form-item label="业务辅助代码" prop="cFixBusinFlag">
          <el-input v-model="form.cFixBusinFlag" placeholder="请输入业务辅助代码" />
        </el-form-item>
        <el-form-item label="系统日期" prop="vcSysDate">
          <el-input v-model="form.vcSysDate" placeholder="请输入系统日期" />
        </el-form-item>
        <el-form-item label="机器日期" prop="vcMachineDate">
          <el-input v-model="form.vcMachineDate" placeholder="请输入机器日期" />
        </el-form-item>
        <el-form-item label="机器时间" prop="vcMachineTime">
          <el-input v-model="form.vcMachineTime" placeholder="请输入机器时间" />
        </el-form-item>
        <el-form-item label="批次号" prop="vcBatchNo">
          <el-input v-model="form.vcBatchNo" placeholder="请输入批次号" />
        </el-form-item>
        <el-form-item label="交易来源" prop="vcComeFrom">
          <el-input v-model="form.vcComeFrom" placeholder="请输入交易来源" />
        </el-form-item>
        <el-form-item label="银商流水号" prop="vcChinaPaySerialNo">
          <el-input v-model="form.vcChinaPaySerialNo" placeholder="请输入银商流水号" />
        </el-form-item>
        <el-form-item label="自动发起标志" prop="cAutoRequest">
          <el-input v-model="form.cAutoRequest" placeholder="请输入自动发起标志" />
        </el-form-item>
        <el-form-item label="子账户编号" prop="vcSubAccoNo">
          <el-input v-model="form.vcSubAccoNo" placeholder="请输入子账户编号" />
        </el-form-item>
        <el-form-item label="客户编号" prop="vcCustNo">
          <el-input v-model="form.vcCustNo" placeholder="请输入客户编号" />
        </el-form-item>
        <el-form-item label="批次申请号" prop="vcBatchRequestNo">
          <el-input v-model="form.vcBatchRequestNo" placeholder="请输入批次申请号" />
        </el-form-item>
        <el-form-item label="确认处理清算日期" prop="vcConfirmSettleDate">
          <el-input v-model="form.vcConfirmSettleDate" placeholder="请输入确认处理清算日期" />
        </el-form-item>
        <el-form-item label="交易预处理清算日期" prop="vcRequestSettleDate">
          <el-input v-model="form.vcRequestSettleDate" placeholder="请输入交易预处理清算日期" />
        </el-form-item>
        <el-form-item label="资金账号" prop="vcCapAcco">
          <el-input v-model="form.vcCapAcco" placeholder="请输入资金账号" />
        </el-form-item>
        <el-form-item label="功能号" prop="vcFunctionNo">
          <el-input v-model="form.vcFunctionNo" placeholder="请输入功能号" />
        </el-form-item>
        <el-form-item label="分库分表位" prop="lNode">
          <el-input v-model="form.lNode" placeholder="请输入分库分表位" />
        </el-form-item>
        <el-form-item label="导出状态" prop="cExportState">
          <el-input v-model="form.cExportState" placeholder="请输入导出状态" />
        </el-form-item>
        <el-form-item label="删除标志" prop="cDelete">
          <el-input v-model="form.cDelete" placeholder="请输入删除标志" />
        </el-form-item>
        <el-form-item label="时间戳" prop="vcTimestamp">
          <el-input v-model="form.vcTimestamp" placeholder="请输入时间戳" />
        </el-form-item>
        <el-form-item label="对方流水号" prop="vcOtherSerialNo">
          <el-input v-model="form.vcOtherSerialNo" placeholder="请输入对方流水号" />
        </el-form-item>
        <el-form-item label="${comment}" prop="cRecordState">
          <el-input v-model="form.cRecordState" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="vcLcsExportBatchNo">
          <el-input v-model="form.vcLcsExportBatchNo" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="cSendLiqState">
          <el-input v-model="form.cSendLiqState" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="vcAuditDateTime">
          <el-input v-model="form.vcAuditDateTime" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="vcCheckDateTime">
          <el-input v-model="form.vcCheckDateTime" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="vcFaxNo">
          <el-input v-model="form.vcFaxNo" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="vcDelayCause">
          <el-input v-model="form.vcDelayCause" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="cDelayCode">
          <el-input v-model="form.cDelayCode" placeholder="请输入${comment}" />
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
import { listTrequest, getTrequest, delTrequest, addTrequest, updateTrequest } from "@/api/system/trequest";

export default {
  name: "Trequest",
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
      // 交易申请表格数据
      trequestList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vcFundAcco: null,
        vcTradeAcco: null,
        vcRequestDate: null,
        vcRequestTime: null,
        cBusinFlag: null,
        vcProductCode: null,
        cShareType: null,
        cTrust: null,
        enBalance: null,
        enShare: null,
        enDiscount: null,
        enEndDiscount: null,
        enOtherDiscount: null,
        cSendState: null,
        cTradeState: null,
        cConfirmFlag: null,
        enConfirmShare: null,
        enConfirmBala: null,
        vcHopeDate: null,
        vcOtherAcco: null,
        vcOtherTradeAcco: null,
        vcOtherProductCode: null,
        cOtherShareType: null,
        vcOtherAgencyNo: null,
        cDividendMethod: null,
        cFrozenFlag: null,
        cFrozenCause: null,
        cCapitalMode: null,
        cSubCapitalMode: null,
        cInterfaceType: null,
        lDelayDay: null,
        cAuditFlag: null,
        vcTaCode: null,
        vcAcceptDate: null,
        vcAcceptTime: null,
        cCheckFlag: null,
        cCustConfirm: null,
        cFixBusinFlag: null,
        vcSysDate: null,
        vcMachineDate: null,
        vcMachineTime: null,
        vcBatchNo: null,
        cTradeBusinType: null,
        vcComeFrom: null,
        vcChinaPaySerialNo: null,
        cAutoRequest: null,
        vcSubAccoNo: null,
        vcCustNo: null,
        vcBatchRequestNo: null,
        vcConfirmSettleDate: null,
        vcRequestSettleDate: null,
        vcCapAcco: null,
        vcFunctionNo: null,
        lNode: null,
        cExportState: null,
        cDelete: null,
        vcTimestamp: null,
        vcOtherSerialNo: null,
        cRecordState: null,
        vcLcsExportBatchNo: null,
        cSendLiqState: null,
        vcAuditDateTime: null,
        vcCheckDateTime: null,
        vcFaxNo: null,
        vcDelayCause: null,
        cDelayCode: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        vcFundAcco: [
          { required: true, message: "基金账号不能为空", trigger: "blur" }
        ],
        vcTradeAcco: [
          { required: true, message: "交易账号不能为空", trigger: "blur" }
        ],
        vcRequestDate: [
          { required: true, message: "申请日期不能为空", trigger: "blur" }
        ],
        cBusinFlag: [
          { required: true, message: "业务代码不能为空", trigger: "blur" }
        ],
        cTrust: [
          { required: true, message: "委托方式不能为空", trigger: "blur" }
        ],
        enBalance: [
          { required: true, message: "申请金额不能为空", trigger: "blur" }
        ],
        enShare: [
          { required: true, message: "申请份额不能为空", trigger: "blur" }
        ],
        enDiscount: [
          { required: true, message: "折扣率不能为空", trigger: "blur" }
        ],
        cSendState: [
          { required: true, message: "发送状态不能为空", trigger: "blur" }
        ],
        cConfirmFlag: [
          { required: true, message: "确认标志不能为空", trigger: "blur" }
        ],
        enConfirmShare: [
          { required: true, message: "确认份额不能为空", trigger: "blur" }
        ],
        enConfirmBala: [
          { required: true, message: "确认金额不能为空", trigger: "blur" }
        ],
        vcTaCode: [
          { required: true, message: "ta代码不能为空", trigger: "blur" }
        ],
        vcTimestamp: [
          { required: true, message: "时间戳不能为空", trigger: "blur" }
        ],
        cSendLiqState: [
          { required: true, message: "$comment不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询交易申请列表 */
    getList() {
      this.loading = true;
      listTrequest(this.queryParams).then(response => {
        this.trequestList = response.rows;
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
        vcTenantId: null,
        vcRequestNo: null,
        vcFundAcco: null,
        vcTradeAcco: null,
        vcRequestDate: null,
        vcRequestTime: null,
        cBusinFlag: null,
        vcProductCode: null,
        cShareType: null,
        cTrust: null,
        enBalance: null,
        enShare: null,
        enDiscount: null,
        enEndDiscount: null,
        enOtherDiscount: null,
        cSendState: null,
        cTradeState: null,
        cConfirmFlag: null,
        enConfirmShare: null,
        enConfirmBala: null,
        vcHopeDate: null,
        vcOtherAcco: null,
        vcOtherTradeAcco: null,
        vcOtherProductCode: null,
        cOtherShareType: null,
        vcOtherAgencyNo: null,
        cDividendMethod: null,
        cFrozenFlag: null,
        cFrozenCause: null,
        cCapitalMode: null,
        cSubCapitalMode: null,
        cInterfaceType: null,
        lDelayDay: null,
        cAuditFlag: null,
        vcTaCode: null,
        vcAcceptDate: null,
        vcAcceptTime: null,
        cCheckFlag: null,
        cCustConfirm: null,
        cFixBusinFlag: null,
        vcSysDate: null,
        vcMachineDate: null,
        vcMachineTime: null,
        vcBatchNo: null,
        cTradeBusinType: null,
        vcComeFrom: null,
        vcChinaPaySerialNo: null,
        cAutoRequest: null,
        vcSubAccoNo: null,
        vcCustNo: null,
        vcBatchRequestNo: null,
        vcConfirmSettleDate: null,
        vcRequestSettleDate: null,
        vcCapAcco: null,
        vcFunctionNo: null,
        lNode: null,
        cExportState: null,
        cDelete: null,
        vcTimestamp: null,
        vcOtherSerialNo: null,
        cRecordState: null,
        vcLcsExportBatchNo: null,
        cSendLiqState: null,
        vcAuditDateTime: null,
        vcCheckDateTime: null,
        vcFaxNo: null,
        vcDelayCause: null,
        cDelayCode: null
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
      this.ids = selection.map(item => item.vcTenantId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加交易申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const vcTenantId = row.vcTenantId || this.ids
      getTrequest(vcTenantId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改交易申请";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.vcTenantId != null) {
            updateTrequest(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTrequest(this.form).then(response => {
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
      const vcTenantIds = row.vcTenantId || this.ids;
      this.$modal.confirm('是否确认删除交易申请编号为"' + vcTenantIds + '"的数据项？').then(function() {
        return delTrequest(vcTenantIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/trequest/export', {
        ...this.queryParams
      }, `trequest_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
