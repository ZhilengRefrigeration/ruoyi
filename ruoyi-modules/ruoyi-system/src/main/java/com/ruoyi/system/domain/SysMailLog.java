package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

import java.io.Serial;

/**
 * 邮件发送日志对象 sys_mail_log
 *
 * @author ryas
 * created on 2024-03-01
 */
public class SysMailLog extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    private Long mailLogId;

    /**
     * 发送状态（0未发送 1成功 2失败）
     */
    @Excel(name = "发送状态", readConverterExp = "0=未发送,1=成功,2=失败")
    private Long status;

    /**
     * 自定业务类型
     */
    @Excel(name = "自定业务类型")
    private String businessType;

    /**
     * 发件人
     */
    @Excel(name = "发件人")
    private String from;

    /**
     * 收件人
     */
    @Excel(name = "收件人")
    private String to;

    /**
     * 抄送
     */
    @Excel(name = "抄送")
    private String cc;

    /**
     * 邮件主题
     */
    @Excel(name = "邮件主题")
    private String subject;

    /**
     * 日志消息
     */
    @Excel(name = "日志消息")
    private String msg;

    /**
     * 消耗时间(ms)
     */
    @Excel(name = "消耗时间(ms)")
    private Long costTime;

    public void setMailLogId(Long mailLogId) {
        this.mailLogId = mailLogId;
    }

    public Long getMailLogId() {
        return mailLogId;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCc() {
        return cc;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public Long getCostTime() {
        return costTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("mailLogId", getMailLogId())
                .append("status", getStatus())
                .append("businessType", getBusinessType())
                .append("from", getFrom())
                .append("to", getTo())
                .append("cc", getCc())
                .append("subject", getSubject())
                .append("msg", getMsg())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("costTime", getCostTime())
                .append("remark", getRemark())
                .toString();
    }
}
