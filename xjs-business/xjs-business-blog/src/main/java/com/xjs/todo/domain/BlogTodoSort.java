package com.xjs.todo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客待办分类实体
 * @author xiejs
 * @since 2022-01-27
 */
@Data
public class BlogTodoSort implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 外键分类id */
    @Excel(name = "外键分类id")
    private Long sortId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String sortName;

    /** 分类颜色（#999999）这种 */
    @Excel(name = "分类颜色")
    private String sortColor;

    /** 分类排序 */
    @Excel(name = "分类排序")
    private Long sort;

    @Excel(name = "创建时间" ,dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
