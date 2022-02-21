package com.xjs.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.xjs.validation.group.SelectGroup;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xiejs
 * @desc  api预警实体类
 * @create 2022-01-07
 */
@TableName("api_warning")
@Data
public class ApiWarning implements Serializable {
    /** id */
    private Long id;

    /** api名称 */
    @Excel(name = "api名称")
    @Size(max = 20, message = "请控制api名称长度在20字符", groups = {SelectGroup.class})
    private String apiName;

    /** 预警类型 */
    @Excel(name = "预警类型")
    private String warningType;

    /** 预警等级 */
    @Excel(name = "预警等级")
    private String warningLevel;

    /** 预警记录信息 */
    @Excel(name = "预警记录信息")
    private String warningMessage;

    /** 限定值 */
    @Excel(name = "限定值")
    private String limitValue;

    /** 实际值 */
    @Excel(name = "实际值")
    private String realValue;

    /** 是否处理 1 处理 2未处理 */
    @Excel(name = "是否处理",readConverterExp=" 1=已处理,2=未处理")
    private Integer handle;

    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
