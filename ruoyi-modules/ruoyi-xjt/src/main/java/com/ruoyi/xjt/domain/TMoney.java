package com.ruoyi.xjt.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 支出或收入详情对象 t_money
 * 
 * @author ruoyi
 * @date 2022-04-01
 */
public class TMoney extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 对应用户 */
    @Excel(name = "对应用户")
    private Long userid;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal money;

    /** 交易类型 2：保养   4:进货 7：救援 10：维修厂营销卡  40：后台操作 50:提现  60：牛牛币返利 70：商城服务单  80:聚乐贝兑换 0：充值 */
    @Excel(name = "交易类型 2：保养   4:进货 7：救援 10：维修厂营销卡  40：后台操作 50:提现  60：牛牛币返利 70：商城服务单  80:聚乐贝兑换 0：充值")
    private Long tradetype;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tradedate;

    /** 获取与扣除1=获取，2=扣除  */
    @Excel(name = "获取与扣除1=获取，2=扣除 ")
    private Long type;

    /** 备注 */
    @Excel(name = "备注")
    private String note;

    /** 处理后剩余金额 */
    @Excel(name = "处理后剩余金额")
    private BigDecimal leftmoney;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderid;

    /** 状态1=正常，2=删除 */
    @Excel(name = "状态1=正常，2=删除")
    private Long state;

    /** bbsmall t_orderid表 id */
    @Excel(name = "bbsmall t_orderid表 id")
    private Long bbsmallorderid;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }
    public void setMoney(BigDecimal money) 
    {
        this.money = money;
    }

    public BigDecimal getMoney() 
    {
        return money;
    }
    public void setTradetype(Long tradetype) 
    {
        this.tradetype = tradetype;
    }

    public Long getTradetype() 
    {
        return tradetype;
    }
    public void setTradedate(Date tradedate) 
    {
        this.tradedate = tradedate;
    }

    public Date getTradedate() 
    {
        return tradedate;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setNote(String note) 
    {
        this.note = note;
    }

    public String getNote() 
    {
        return note;
    }
    public void setLeftmoney(BigDecimal leftmoney) 
    {
        this.leftmoney = leftmoney;
    }

    public BigDecimal getLeftmoney() 
    {
        return leftmoney;
    }
    public void setOrderid(Long orderid) 
    {
        this.orderid = orderid;
    }

    public Long getOrderid() 
    {
        return orderid;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }
    public void setBbsmallorderid(Long bbsmallorderid) 
    {
        this.bbsmallorderid = bbsmallorderid;
    }

    public Long getBbsmallorderid() 
    {
        return bbsmallorderid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userid", getUserid())
            .append("money", getMoney())
            .append("tradetype", getTradetype())
            .append("tradedate", getTradedate())
            .append("type", getType())
            .append("note", getNote())
            .append("leftmoney", getLeftmoney())
            .append("orderid", getOrderid())
            .append("state", getState())
            .append("bbsmallorderid", getBbsmallorderid())
            .toString();
    }
}
