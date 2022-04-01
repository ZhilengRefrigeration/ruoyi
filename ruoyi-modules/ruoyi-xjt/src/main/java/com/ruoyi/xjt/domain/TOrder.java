package com.ruoyi.xjt.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 缴费订单对象 t_order
 * 
 * @author ruoyi
 * @date 2022-04-01
 */
public class TOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 业务发生所属公司 */
    @Excel(name = "业务发生所属公司")
    private Long companyid;

    /** 对应用户 */
    @Excel(name = "对应用户")
    private Long khuserid;

    /** 服务商用户id（洗车工 或者 维修厂） */
    @Excel(name = "服务商用户id", readConverterExp = "洗=车工,或=者,维=修厂")
    private Long fwsuserid;

    /** 订单总额 */
    @Excel(name = "订单总额")
    private BigDecimal money;

    /** 其中使用余额支付多少元 */
    @Excel(name = "其中使用余额支付多少元")
    private BigDecimal yuepay;

    /** 其中使用保障基金支付多少元 */
    @Excel(name = "其中使用保障基金支付多少元")
    private BigDecimal fundpay;

    /** 其中使用微信活支付宝支付多少元 */
    @Excel(name = "其中使用微信活支付宝支付多少元")
    private BigDecimal wxorcftpay;

    /** 交易类型（  2：保养   4：进货   6：基金申请 7：救援  8：维修厂充值巴士币 9:  充值 10：购买维修厂卡片） */
    @Excel(name = "交易类型", readConverterExp = "2=：保养,4=：进货,6=：基金申请,7=：救援,8=：维修厂充值巴士币,9=:,充=值,1=0：购买维修厂卡片")
    private Long tradetype;

    /** 订单号 */
    @Excel(name = "订单号")
    private String ordernum;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paytime;

    /** 订单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdate;

    /** 支付类型 0=余额支付  1=微信支付，2=支付宝支付  ，3=保障金支付  4：现金支付   10：余额+微信   20：余额+支付宝  13：保障金+微信   23=保障金+支付宝   30: 保障金+余额支付     130：保障金+微信+余额支付   230=保障金+支付宝+余额支付 43：保障基金+现金支付 */
    @Excel(name = "支付类型 0=余额支付  1=微信支付，2=支付宝支付  ，3=保障金支付  4：现金支付   10：余额+微信   20：余额+支付宝  13：保障金+微信   23=保障金+支付宝   30: 保障金+余额支付     130：保障金+微信+余额支付   230=保障金+支付宝+余额支付 43：保障基金+现金支付")
    private Long paytype;

    /** 支付状态 0未支付  1已支付 */
    @Excel(name = "支付状态 0未支付  1已支付")
    private Long paystate;

    /** 微信或支付宝返回的交易号 */
    @Excel(name = "微信或支付宝返回的交易号")
    private String tradeNo;

    /** 商户号 */
    @Excel(name = "商户号")
    private String mchId;

    /** 微信或支付宝返回的交易状态 */
    @Excel(name = "微信或支付宝返回的交易状态")
    private String tradeStatus;

    /** 优惠价 用户关联表id */
    @Excel(name = "优惠价 用户关联表id")
    private Long cuid;

    /** 使用优惠价额度 */
    @Excel(name = "使用优惠价额度")
    private BigDecimal cumoney;

    /** 退款审核人id */
    @Excel(name = "退款审核人id")
    private Long refundAuditUserid;

    /** 退款金额 */
    @Excel(name = "退款金额")
    private BigDecimal refundMoney;

    /** 退款备注 */
    @Excel(name = "退款备注")
    private String refundNote;

    /** 退款单号 */
    @Excel(name = "退款单号")
    private String refundNo;

    /** 退款状态 1：已成功提交退款申请   2：退款成功  3：退款失败 */
    @Excel(name = "退款状态 1：已成功提交退款申请   2：退款成功  3：退款失败")
    private Integer refundState;

    /** 退款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date refundTime;

    /** 退款微信返回时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退款微信返回时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date refundSuccesstime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCompanyid(Long companyid) 
    {
        this.companyid = companyid;
    }

    public Long getCompanyid() 
    {
        return companyid;
    }
    public void setKhuserid(Long khuserid) 
    {
        this.khuserid = khuserid;
    }

    public Long getKhuserid() 
    {
        return khuserid;
    }
    public void setFwsuserid(Long fwsuserid) 
    {
        this.fwsuserid = fwsuserid;
    }

    public Long getFwsuserid() 
    {
        return fwsuserid;
    }
    public void setMoney(BigDecimal money) 
    {
        this.money = money;
    }

    public BigDecimal getMoney() 
    {
        return money;
    }
    public void setYuepay(BigDecimal yuepay) 
    {
        this.yuepay = yuepay;
    }

    public BigDecimal getYuepay() 
    {
        return yuepay;
    }
    public void setFundpay(BigDecimal fundpay) 
    {
        this.fundpay = fundpay;
    }

    public BigDecimal getFundpay() 
    {
        return fundpay;
    }
    public void setWxorcftpay(BigDecimal wxorcftpay) 
    {
        this.wxorcftpay = wxorcftpay;
    }

    public BigDecimal getWxorcftpay() 
    {
        return wxorcftpay;
    }
    public void setTradetype(Long tradetype) 
    {
        this.tradetype = tradetype;
    }

    public Long getTradetype() 
    {
        return tradetype;
    }
    public void setOrdernum(String ordernum) 
    {
        this.ordernum = ordernum;
    }

    public String getOrdernum() 
    {
        return ordernum;
    }
    public void setPaytime(Date paytime) 
    {
        this.paytime = paytime;
    }

    public Date getPaytime() 
    {
        return paytime;
    }
    public void setCreatedate(Date createdate) 
    {
        this.createdate = createdate;
    }

    public Date getCreatedate() 
    {
        return createdate;
    }
    public void setPaytype(Long paytype) 
    {
        this.paytype = paytype;
    }

    public Long getPaytype() 
    {
        return paytype;
    }
    public void setPaystate(Long paystate) 
    {
        this.paystate = paystate;
    }

    public Long getPaystate() 
    {
        return paystate;
    }
    public void setTradeNo(String tradeNo) 
    {
        this.tradeNo = tradeNo;
    }

    public String getTradeNo() 
    {
        return tradeNo;
    }
    public void setMchId(String mchId) 
    {
        this.mchId = mchId;
    }

    public String getMchId() 
    {
        return mchId;
    }
    public void setTradeStatus(String tradeStatus) 
    {
        this.tradeStatus = tradeStatus;
    }

    public String getTradeStatus() 
    {
        return tradeStatus;
    }
    public void setCuid(Long cuid) 
    {
        this.cuid = cuid;
    }

    public Long getCuid() 
    {
        return cuid;
    }
    public void setCumoney(BigDecimal cumoney) 
    {
        this.cumoney = cumoney;
    }

    public BigDecimal getCumoney() 
    {
        return cumoney;
    }
    public void setRefundAuditUserid(Long refundAuditUserid) 
    {
        this.refundAuditUserid = refundAuditUserid;
    }

    public Long getRefundAuditUserid() 
    {
        return refundAuditUserid;
    }
    public void setRefundMoney(BigDecimal refundMoney) 
    {
        this.refundMoney = refundMoney;
    }

    public BigDecimal getRefundMoney() 
    {
        return refundMoney;
    }
    public void setRefundNote(String refundNote) 
    {
        this.refundNote = refundNote;
    }

    public String getRefundNote() 
    {
        return refundNote;
    }
    public void setRefundNo(String refundNo) 
    {
        this.refundNo = refundNo;
    }

    public String getRefundNo() 
    {
        return refundNo;
    }
    public void setRefundState(Integer refundState) 
    {
        this.refundState = refundState;
    }

    public Integer getRefundState() 
    {
        return refundState;
    }
    public void setRefundTime(Date refundTime) 
    {
        this.refundTime = refundTime;
    }

    public Date getRefundTime() 
    {
        return refundTime;
    }
    public void setRefundSuccesstime(Date refundSuccesstime) 
    {
        this.refundSuccesstime = refundSuccesstime;
    }

    public Date getRefundSuccesstime() 
    {
        return refundSuccesstime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyid", getCompanyid())
            .append("khuserid", getKhuserid())
            .append("fwsuserid", getFwsuserid())
            .append("money", getMoney())
            .append("yuepay", getYuepay())
            .append("fundpay", getFundpay())
            .append("wxorcftpay", getWxorcftpay())
            .append("tradetype", getTradetype())
            .append("ordernum", getOrdernum())
            .append("paytime", getPaytime())
            .append("createdate", getCreatedate())
            .append("paytype", getPaytype())
            .append("paystate", getPaystate())
            .append("tradeNo", getTradeNo())
            .append("mchId", getMchId())
            .append("tradeStatus", getTradeStatus())
            .append("cuid", getCuid())
            .append("cumoney", getCumoney())
            .append("refundAuditUserid", getRefundAuditUserid())
            .append("refundMoney", getRefundMoney())
            .append("refundNote", getRefundNote())
            .append("refundNo", getRefundNo())
            .append("refundState", getRefundState())
            .append("refundTime", getRefundTime())
            .append("refundSuccesstime", getRefundSuccesstime())
            .toString();
    }
}
