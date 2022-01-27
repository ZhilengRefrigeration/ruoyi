package com.xjs.todo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客待办实体
 *
 * @author xiejs
 * @since 2022-01-27
 */
@Data
public class BlogTodo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 待办标题
     */
    @Excel(name = "待办标题")
    private String todoTitle;

    /**
     * 待办内容
     */
    @Excel(name = "待办内容")
    private String todoContent;

    /**
     * 待办程度
     */
    @Excel(name = "待办程度")
    private String todoLevel;

    /**
     * 是否过期 1：未过期 2：已过期
     */
    @Excel(name = "是否过期 ", readConverterExp = "1=未过期 2=已过期")
    private Integer pastDue;

    /**
     * 显示颜色
     */
    @Excel(name = "显示颜色")
    private String todoColor;

    /**
     * 外键分类id
     */
    @Excel(name = "外键分类id")
    private Long sortId;

    /**
     * 博客分类对象
     */
    @TableField(exist = false)
    private BlogTodoSort blogTodoSort;


    /**
     * 博客用户账号
     */
    @Excel(name = "博客用户账号")
    private String userName;

    /**
     * 博客用户对象
     */
    @TableField(exist = false)
    private BlogUser blogUser;

    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
