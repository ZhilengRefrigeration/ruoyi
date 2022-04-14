package com.xjs.maillog.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import com.xjs.validation.group.SelectGroup;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 邮件日志对象
 *
 * @author xiejs
 * @since 2022-04-14
 */
@Data
public class MailLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 邮件主题
     */
    @Excel(name = "邮件主题")
    @Size(max = 20, message = "请控制邮件主题长度在20字符", groups = { SelectGroup.class})
    private String title;

    /**
     * 邮件内容
     */
    @Excel(name = "邮件内容")
    @Size(max = 20, message = "请控制邮件内容长度在20字符", groups = { SelectGroup.class})
    private String content;

    /**
     * 收件人
     */
    @Excel(name = "收件人")
    @Size(max = 20, message = "请控制收件人长度在20字符", groups = { SelectGroup.class})
    private String recipient;

    /**
     * 邮件类型
     */
    @Excel(name = "邮件类型")
    private String mailType;

    /**
     * 请求时间（毫秒）
     */
    @Excel(name = "请求时间")
    private Long requestTime;

    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endCreateTime;
}
