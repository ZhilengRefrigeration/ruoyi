package com.xjs.tasklog.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务日志实体类
 * @author xiejs
 * @since 2022-03-01
 */
@Data
public class TaskLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String name;

    /** 方法名 */
    @Excel(name = "方法名")
    private String method;

    /** 类路径 */
    @Excel(name = "类路径")
    private String classPath;

    /** 请求时间 */
    @Excel(name = "请求时间")
    private Long requestTime;

    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
