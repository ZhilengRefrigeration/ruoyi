package com.ruoyi.system.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;

/**
 * 邮件发送日志对象 sys_mail_log
 *
 * @author ryas
 * created on 2024-03-01
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMailLog extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    //==================== ↓↓↓↓↓↓ 非表字段 ↓↓↓↓↓↓ ====================

    /**
     * 创建者用户名
     */
    private String createByName;

    //==================== ↓↓↓↓↓↓ 表字段 ↓↓↓↓↓↓ ====================

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

}
