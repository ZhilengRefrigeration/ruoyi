package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易申请对象 tc_trequest
 * 
 * @author ruoyi
 * @date 2023-07-26
 */
public class TcTrequest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 租户id */
    private String vcTenantId;

    /** 申请编号 */
    private String vcRequestNo;

    /** 基金账号 */
    @Excel(name = "基金账号")
    private String vcFundAcco;

    /** 交易账号 */
    @Excel(name = "交易账号")
    private String vcTradeAcco;

    /** 申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vcRequestDate;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vcRequestTime;

    /** 业务代码 */
    @Excel(name = "业务代码")
    private String cBusinFlag;

    /** 产品代码 */
    @Excel(name = "产品代码")
    private String vcProductCode;

    /** 份额类别 */
    @Excel(name = "份额类别")
    private String cShareType;

    /** 委托方式 */
    @Excel(name = "委托方式")
    private String cTrust;

    /** 申请金额 */
    @Excel(name = "申请金额")
    private BigDecimal enBalance;

    /** 申请份额 */
    @Excel(name = "申请份额")
    private BigDecimal enShare;

    /** 折扣率 */
    @Excel(name = "折扣率")
    private BigDecimal enDiscount;

    /** 后收费折扣 */
    @Excel(name = "后收费折扣")
    private BigDecimal enEndDiscount;

    /** 补差费折扣 */
    @Excel(name = "补差费折扣")
    private BigDecimal enOtherDiscount;

    /** 发送状态 */
    @Excel(name = "发送状态")
    private String cSendState;

    /** 申清校验 */
    @Excel(name = "申清校验")
    private String cTradeState;

    /** 确认标志 */
    @Excel(name = "确认标志")
    private String cConfirmFlag;

    /** 确认份额 */
    @Excel(name = "确认份额")
    private BigDecimal enConfirmShare;

    /** 确认金额 */
    @Excel(name = "确认金额")
    private BigDecimal enConfirmBala;

    /** 预约日期 */
    @Excel(name = "预约日期")
    private String vcHopeDate;

    /** 对方基金账号 */
    @Excel(name = "对方基金账号")
    private String vcOtherAcco;

    /** 与安对方交易账号 */
    @Excel(name = "与安对方交易账号")
    private String vcOtherTradeAcco;

    /** 对方产品代码 */
    @Excel(name = "对方产品代码")
    private String vcOtherProductCode;

    /** 对方份额类别 */
    @Excel(name = "对方份额类别")
    private String cOtherShareType;

    /** 对方销售商 */
    @Excel(name = "对方销售商")
    private String vcOtherAgencyNo;

    /** 分红方式 */
    @Excel(name = "分红方式")
    private String cDividendMethod;

    /** 冻结标志 */
    @Excel(name = "冻结标志")
    private String cFrozenFlag;

    /** 冻结原因 */
    @Excel(name = "冻结原因")
    private String cFrozenCause;

    /** 资金方式 */
    @Excel(name = "资金方式")
    private String cCapitalMode;

    /** 明细资金方式 */
    @Excel(name = "明细资金方式")
    private String cSubCapitalMode;

    /** 代扣接口类型 */
    @Excel(name = "代扣接口类型")
    private String cInterfaceType;

    /** 允许顺延天数 */
    @Excel(name = "允许顺延天数")
    private Long lDelayDay;

    /** 复核标志 */
    @Excel(name = "复核标志")
    private String cAuditFlag;

    /** ta代码 */
    @Excel(name = "ta代码")
    private String vcTaCode;

    /** 下单日期 */
    @Excel(name = "下单日期")
    private String vcAcceptDate;

    /** 下单时间 */
    @Excel(name = "下单时间")
    private String vcAcceptTime;

    /** 审批标志 */
    @Excel(name = "审批标志")
    private String cCheckFlag;

    /** 客户确认标志 */
    @Excel(name = "客户确认标志")
    private String cCustConfirm;

    /** 业务辅助代码 */
    @Excel(name = "业务辅助代码")
    private String cFixBusinFlag;

    /** 系统日期 */
    @Excel(name = "系统日期")
    private String vcSysDate;

    /** 机器日期 */
    @Excel(name = "机器日期")
    private String vcMachineDate;

    /** 机器时间 */
    @Excel(name = "机器时间")
    private String vcMachineTime;

    /** 批次号 */
    @Excel(name = "批次号")
    private String vcBatchNo;

    /** 交易业务类别 */
    @Excel(name = "交易业务类别")
    private String cTradeBusinType;

    /** 交易来源 */
    @Excel(name = "交易来源")
    private String vcComeFrom;

    /** 银商流水号 */
    @Excel(name = "银商流水号")
    private String vcChinaPaySerialNo;

    /** 自动发起标志 */
    @Excel(name = "自动发起标志")
    private String cAutoRequest;

    /** 子账户编号 */
    @Excel(name = "子账户编号")
    private String vcSubAccoNo;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String vcCustNo;

    /** 批次申请号 */
    @Excel(name = "批次申请号")
    private String vcBatchRequestNo;

    /** 确认处理清算日期 */
    @Excel(name = "确认处理清算日期")
    private String vcConfirmSettleDate;

    /** 交易预处理清算日期 */
    @Excel(name = "交易预处理清算日期")
    private String vcRequestSettleDate;

    /** 资金账号 */
    @Excel(name = "资金账号")
    private String vcCapAcco;

    /** 功能号 */
    @Excel(name = "功能号")
    private String vcFunctionNo;

    /** 分库分表位 */
    @Excel(name = "分库分表位")
    private Long lNode;

    /** 导出状态 */
    @Excel(name = "导出状态")
    private String cExportState;

    /** 删除标志 */
    @Excel(name = "删除标志")
    private String cDelete;

    /** 时间戳 */
    @Excel(name = "时间戳")
    private String vcTimestamp;

    /** 对方流水号 */
    @Excel(name = "对方流水号")
    private String vcOtherSerialNo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cRecordState;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String vcLcsExportBatchNo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cSendLiqState;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String vcAuditDateTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String vcCheckDateTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String vcFaxNo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String vcDelayCause;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cDelayCode;

    //月申请金额之和
    private BigDecimal monthEnbalance;
    //年
    private String year;

    //月
    private int month;

    public BigDecimal getMonthEnbalance() {
        return monthEnbalance;
    }

    public void setMonthEnbalance(BigDecimal monthEnbalance) {
        this.monthEnbalance = monthEnbalance;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setVcTenantId(String vcTenantId)
    {
        this.vcTenantId = vcTenantId;
    }

    public String getVcTenantId() 
    {
        return vcTenantId;
    }
    public void setVcRequestNo(String vcRequestNo) 
    {
        this.vcRequestNo = vcRequestNo;
    }

    public String getVcRequestNo() 
    {
        return vcRequestNo;
    }
    public void setVcFundAcco(String vcFundAcco) 
    {
        this.vcFundAcco = vcFundAcco;
    }

    public String getVcFundAcco() 
    {
        return vcFundAcco;
    }
    public void setVcTradeAcco(String vcTradeAcco) 
    {
        this.vcTradeAcco = vcTradeAcco;
    }

    public String getVcTradeAcco() 
    {
        return vcTradeAcco;
    }
    public void setVcRequestDate(Date vcRequestDate) 
    {
        this.vcRequestDate = vcRequestDate;
    }

    public Date getVcRequestDate() 
    {
        return vcRequestDate;
    }
    public void setVcRequestTime(Date vcRequestTime) 
    {
        this.vcRequestTime = vcRequestTime;
    }

    public Date getVcRequestTime() 
    {
        return vcRequestTime;
    }
    public void setcBusinFlag(String cBusinFlag) 
    {
        this.cBusinFlag = cBusinFlag;
    }

    public String getcBusinFlag() 
    {
        return cBusinFlag;
    }
    public void setVcProductCode(String vcProductCode) 
    {
        this.vcProductCode = vcProductCode;
    }

    public String getVcProductCode() 
    {
        return vcProductCode;
    }
    public void setcShareType(String cShareType) 
    {
        this.cShareType = cShareType;
    }

    public String getcShareType() 
    {
        return cShareType;
    }
    public void setcTrust(String cTrust) 
    {
        this.cTrust = cTrust;
    }

    public String getcTrust() 
    {
        return cTrust;
    }
    public void setEnBalance(BigDecimal enBalance) 
    {
        this.enBalance = enBalance;
    }

    public BigDecimal getEnBalance() 
    {
        return enBalance;
    }
    public void setEnShare(BigDecimal enShare) 
    {
        this.enShare = enShare;
    }

    public BigDecimal getEnShare() 
    {
        return enShare;
    }
    public void setEnDiscount(BigDecimal enDiscount) 
    {
        this.enDiscount = enDiscount;
    }

    public BigDecimal getEnDiscount() 
    {
        return enDiscount;
    }
    public void setEnEndDiscount(BigDecimal enEndDiscount) 
    {
        this.enEndDiscount = enEndDiscount;
    }

    public BigDecimal getEnEndDiscount() 
    {
        return enEndDiscount;
    }
    public void setEnOtherDiscount(BigDecimal enOtherDiscount) 
    {
        this.enOtherDiscount = enOtherDiscount;
    }

    public BigDecimal getEnOtherDiscount() 
    {
        return enOtherDiscount;
    }
    public void setcSendState(String cSendState) 
    {
        this.cSendState = cSendState;
    }

    public String getcSendState() 
    {
        return cSendState;
    }
    public void setcTradeState(String cTradeState) 
    {
        this.cTradeState = cTradeState;
    }

    public String getcTradeState() 
    {
        return cTradeState;
    }
    public void setcConfirmFlag(String cConfirmFlag) 
    {
        this.cConfirmFlag = cConfirmFlag;
    }

    public String getcConfirmFlag() 
    {
        return cConfirmFlag;
    }
    public void setEnConfirmShare(BigDecimal enConfirmShare) 
    {
        this.enConfirmShare = enConfirmShare;
    }

    public BigDecimal getEnConfirmShare() 
    {
        return enConfirmShare;
    }
    public void setEnConfirmBala(BigDecimal enConfirmBala) 
    {
        this.enConfirmBala = enConfirmBala;
    }

    public BigDecimal getEnConfirmBala() 
    {
        return enConfirmBala;
    }
    public void setVcHopeDate(String vcHopeDate) 
    {
        this.vcHopeDate = vcHopeDate;
    }

    public String getVcHopeDate() 
    {
        return vcHopeDate;
    }
    public void setVcOtherAcco(String vcOtherAcco) 
    {
        this.vcOtherAcco = vcOtherAcco;
    }

    public String getVcOtherAcco() 
    {
        return vcOtherAcco;
    }
    public void setVcOtherTradeAcco(String vcOtherTradeAcco) 
    {
        this.vcOtherTradeAcco = vcOtherTradeAcco;
    }

    public String getVcOtherTradeAcco() 
    {
        return vcOtherTradeAcco;
    }
    public void setVcOtherProductCode(String vcOtherProductCode) 
    {
        this.vcOtherProductCode = vcOtherProductCode;
    }

    public String getVcOtherProductCode() 
    {
        return vcOtherProductCode;
    }
    public void setcOtherShareType(String cOtherShareType) 
    {
        this.cOtherShareType = cOtherShareType;
    }

    public String getcOtherShareType() 
    {
        return cOtherShareType;
    }
    public void setVcOtherAgencyNo(String vcOtherAgencyNo) 
    {
        this.vcOtherAgencyNo = vcOtherAgencyNo;
    }

    public String getVcOtherAgencyNo() 
    {
        return vcOtherAgencyNo;
    }
    public void setcDividendMethod(String cDividendMethod) 
    {
        this.cDividendMethod = cDividendMethod;
    }

    public String getcDividendMethod() 
    {
        return cDividendMethod;
    }
    public void setcFrozenFlag(String cFrozenFlag) 
    {
        this.cFrozenFlag = cFrozenFlag;
    }

    public String getcFrozenFlag() 
    {
        return cFrozenFlag;
    }
    public void setcFrozenCause(String cFrozenCause) 
    {
        this.cFrozenCause = cFrozenCause;
    }

    public String getcFrozenCause() 
    {
        return cFrozenCause;
    }
    public void setcCapitalMode(String cCapitalMode) 
    {
        this.cCapitalMode = cCapitalMode;
    }

    public String getcCapitalMode() 
    {
        return cCapitalMode;
    }
    public void setcSubCapitalMode(String cSubCapitalMode) 
    {
        this.cSubCapitalMode = cSubCapitalMode;
    }

    public String getcSubCapitalMode() 
    {
        return cSubCapitalMode;
    }
    public void setcInterfaceType(String cInterfaceType) 
    {
        this.cInterfaceType = cInterfaceType;
    }

    public String getcInterfaceType() 
    {
        return cInterfaceType;
    }
    public void setlDelayDay(Long lDelayDay) 
    {
        this.lDelayDay = lDelayDay;
    }

    public Long getlDelayDay() 
    {
        return lDelayDay;
    }
    public void setcAuditFlag(String cAuditFlag) 
    {
        this.cAuditFlag = cAuditFlag;
    }

    public String getcAuditFlag() 
    {
        return cAuditFlag;
    }
    public void setVcTaCode(String vcTaCode) 
    {
        this.vcTaCode = vcTaCode;
    }

    public String getVcTaCode() 
    {
        return vcTaCode;
    }
    public void setVcAcceptDate(String vcAcceptDate) 
    {
        this.vcAcceptDate = vcAcceptDate;
    }

    public String getVcAcceptDate() 
    {
        return vcAcceptDate;
    }
    public void setVcAcceptTime(String vcAcceptTime) 
    {
        this.vcAcceptTime = vcAcceptTime;
    }

    public String getVcAcceptTime() 
    {
        return vcAcceptTime;
    }
    public void setcCheckFlag(String cCheckFlag) 
    {
        this.cCheckFlag = cCheckFlag;
    }

    public String getcCheckFlag() 
    {
        return cCheckFlag;
    }
    public void setcCustConfirm(String cCustConfirm) 
    {
        this.cCustConfirm = cCustConfirm;
    }

    public String getcCustConfirm() 
    {
        return cCustConfirm;
    }
    public void setcFixBusinFlag(String cFixBusinFlag) 
    {
        this.cFixBusinFlag = cFixBusinFlag;
    }

    public String getcFixBusinFlag() 
    {
        return cFixBusinFlag;
    }
    public void setVcSysDate(String vcSysDate) 
    {
        this.vcSysDate = vcSysDate;
    }

    public String getVcSysDate() 
    {
        return vcSysDate;
    }
    public void setVcMachineDate(String vcMachineDate) 
    {
        this.vcMachineDate = vcMachineDate;
    }

    public String getVcMachineDate() 
    {
        return vcMachineDate;
    }
    public void setVcMachineTime(String vcMachineTime) 
    {
        this.vcMachineTime = vcMachineTime;
    }

    public String getVcMachineTime() 
    {
        return vcMachineTime;
    }
    public void setVcBatchNo(String vcBatchNo) 
    {
        this.vcBatchNo = vcBatchNo;
    }

    public String getVcBatchNo() 
    {
        return vcBatchNo;
    }
    public void setcTradeBusinType(String cTradeBusinType) 
    {
        this.cTradeBusinType = cTradeBusinType;
    }

    public String getcTradeBusinType() 
    {
        return cTradeBusinType;
    }
    public void setVcComeFrom(String vcComeFrom) 
    {
        this.vcComeFrom = vcComeFrom;
    }

    public String getVcComeFrom() 
    {
        return vcComeFrom;
    }
    public void setVcChinaPaySerialNo(String vcChinaPaySerialNo) 
    {
        this.vcChinaPaySerialNo = vcChinaPaySerialNo;
    }

    public String getVcChinaPaySerialNo() 
    {
        return vcChinaPaySerialNo;
    }
    public void setcAutoRequest(String cAutoRequest) 
    {
        this.cAutoRequest = cAutoRequest;
    }

    public String getcAutoRequest() 
    {
        return cAutoRequest;
    }
    public void setVcSubAccoNo(String vcSubAccoNo) 
    {
        this.vcSubAccoNo = vcSubAccoNo;
    }

    public String getVcSubAccoNo() 
    {
        return vcSubAccoNo;
    }
    public void setVcCustNo(String vcCustNo) 
    {
        this.vcCustNo = vcCustNo;
    }

    public String getVcCustNo() 
    {
        return vcCustNo;
    }
    public void setVcBatchRequestNo(String vcBatchRequestNo) 
    {
        this.vcBatchRequestNo = vcBatchRequestNo;
    }

    public String getVcBatchRequestNo() 
    {
        return vcBatchRequestNo;
    }
    public void setVcConfirmSettleDate(String vcConfirmSettleDate) 
    {
        this.vcConfirmSettleDate = vcConfirmSettleDate;
    }

    public String getVcConfirmSettleDate() 
    {
        return vcConfirmSettleDate;
    }
    public void setVcRequestSettleDate(String vcRequestSettleDate) 
    {
        this.vcRequestSettleDate = vcRequestSettleDate;
    }

    public String getVcRequestSettleDate() 
    {
        return vcRequestSettleDate;
    }
    public void setVcCapAcco(String vcCapAcco) 
    {
        this.vcCapAcco = vcCapAcco;
    }

    public String getVcCapAcco() 
    {
        return vcCapAcco;
    }
    public void setVcFunctionNo(String vcFunctionNo) 
    {
        this.vcFunctionNo = vcFunctionNo;
    }

    public String getVcFunctionNo() 
    {
        return vcFunctionNo;
    }
    public void setlNode(Long lNode) 
    {
        this.lNode = lNode;
    }

    public Long getlNode() 
    {
        return lNode;
    }
    public void setcExportState(String cExportState) 
    {
        this.cExportState = cExportState;
    }

    public String getcExportState() 
    {
        return cExportState;
    }
    public void setcDelete(String cDelete) 
    {
        this.cDelete = cDelete;
    }

    public String getcDelete() 
    {
        return cDelete;
    }
    public void setVcTimestamp(String vcTimestamp) 
    {
        this.vcTimestamp = vcTimestamp;
    }

    public String getVcTimestamp() 
    {
        return vcTimestamp;
    }
    public void setVcOtherSerialNo(String vcOtherSerialNo) 
    {
        this.vcOtherSerialNo = vcOtherSerialNo;
    }

    public String getVcOtherSerialNo() 
    {
        return vcOtherSerialNo;
    }
    public void setcRecordState(String cRecordState) 
    {
        this.cRecordState = cRecordState;
    }

    public String getcRecordState() 
    {
        return cRecordState;
    }
    public void setVcLcsExportBatchNo(String vcLcsExportBatchNo) 
    {
        this.vcLcsExportBatchNo = vcLcsExportBatchNo;
    }

    public String getVcLcsExportBatchNo() 
    {
        return vcLcsExportBatchNo;
    }
    public void setcSendLiqState(String cSendLiqState) 
    {
        this.cSendLiqState = cSendLiqState;
    }

    public String getcSendLiqState() 
    {
        return cSendLiqState;
    }
    public void setVcAuditDateTime(String vcAuditDateTime) 
    {
        this.vcAuditDateTime = vcAuditDateTime;
    }

    public String getVcAuditDateTime() 
    {
        return vcAuditDateTime;
    }
    public void setVcCheckDateTime(String vcCheckDateTime) 
    {
        this.vcCheckDateTime = vcCheckDateTime;
    }

    public String getVcCheckDateTime() 
    {
        return vcCheckDateTime;
    }
    public void setVcFaxNo(String vcFaxNo) 
    {
        this.vcFaxNo = vcFaxNo;
    }

    public String getVcFaxNo() 
    {
        return vcFaxNo;
    }
    public void setVcDelayCause(String vcDelayCause) 
    {
        this.vcDelayCause = vcDelayCause;
    }

    public String getVcDelayCause() 
    {
        return vcDelayCause;
    }
    public void setcDelayCode(String cDelayCode) 
    {
        this.cDelayCode = cDelayCode;
    }

    public String getcDelayCode() 
    {
        return cDelayCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("vcTenantId", getVcTenantId())
            .append("vcRequestNo", getVcRequestNo())
            .append("vcFundAcco", getVcFundAcco())
            .append("vcTradeAcco", getVcTradeAcco())
            .append("vcRequestDate", getVcRequestDate())
            .append("vcRequestTime", getVcRequestTime())
            .append("cBusinFlag", getcBusinFlag())
            .append("vcProductCode", getVcProductCode())
            .append("cShareType", getcShareType())
            .append("cTrust", getcTrust())
            .append("enBalance", getEnBalance())
            .append("enShare", getEnShare())
            .append("enDiscount", getEnDiscount())
            .append("enEndDiscount", getEnEndDiscount())
            .append("enOtherDiscount", getEnOtherDiscount())
            .append("cSendState", getcSendState())
            .append("cTradeState", getcTradeState())
            .append("cConfirmFlag", getcConfirmFlag())
            .append("enConfirmShare", getEnConfirmShare())
            .append("enConfirmBala", getEnConfirmBala())
            .append("vcHopeDate", getVcHopeDate())
            .append("vcOtherAcco", getVcOtherAcco())
            .append("vcOtherTradeAcco", getVcOtherTradeAcco())
            .append("vcOtherProductCode", getVcOtherProductCode())
            .append("cOtherShareType", getcOtherShareType())
            .append("vcOtherAgencyNo", getVcOtherAgencyNo())
            .append("cDividendMethod", getcDividendMethod())
            .append("cFrozenFlag", getcFrozenFlag())
            .append("cFrozenCause", getcFrozenCause())
            .append("cCapitalMode", getcCapitalMode())
            .append("cSubCapitalMode", getcSubCapitalMode())
            .append("cInterfaceType", getcInterfaceType())
            .append("lDelayDay", getlDelayDay())
            .append("cAuditFlag", getcAuditFlag())
            .append("vcTaCode", getVcTaCode())
            .append("vcAcceptDate", getVcAcceptDate())
            .append("vcAcceptTime", getVcAcceptTime())
            .append("cCheckFlag", getcCheckFlag())
            .append("cCustConfirm", getcCustConfirm())
            .append("cFixBusinFlag", getcFixBusinFlag())
            .append("vcSysDate", getVcSysDate())
            .append("vcMachineDate", getVcMachineDate())
            .append("vcMachineTime", getVcMachineTime())
            .append("vcBatchNo", getVcBatchNo())
            .append("cTradeBusinType", getcTradeBusinType())
            .append("vcComeFrom", getVcComeFrom())
            .append("vcChinaPaySerialNo", getVcChinaPaySerialNo())
            .append("cAutoRequest", getcAutoRequest())
            .append("vcSubAccoNo", getVcSubAccoNo())
            .append("vcCustNo", getVcCustNo())
            .append("vcBatchRequestNo", getVcBatchRequestNo())
            .append("vcConfirmSettleDate", getVcConfirmSettleDate())
            .append("vcRequestSettleDate", getVcRequestSettleDate())
            .append("vcCapAcco", getVcCapAcco())
            .append("vcFunctionNo", getVcFunctionNo())
            .append("lNode", getlNode())
            .append("cExportState", getcExportState())
            .append("cDelete", getcDelete())
            .append("vcTimestamp", getVcTimestamp())
            .append("vcOtherSerialNo", getVcOtherSerialNo())
            .append("cRecordState", getcRecordState())
            .append("vcLcsExportBatchNo", getVcLcsExportBatchNo())
            .append("cSendLiqState", getcSendLiqState())
            .append("vcAuditDateTime", getVcAuditDateTime())
            .append("vcCheckDateTime", getVcCheckDateTime())
            .append("vcFaxNo", getVcFaxNo())
            .append("vcDelayCause", getVcDelayCause())
            .append("cDelayCode", getcDelayCode())
            .toString();
    }
}
