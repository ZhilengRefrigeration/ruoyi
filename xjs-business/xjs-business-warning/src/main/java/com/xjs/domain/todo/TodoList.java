package com.xjs.domain.todo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 待办对象 todo_list
 *
 * @author xiejs
 * @since 2022-05-07
 */
@Data
public class TodoList implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 待办内容
     */
    @Excel(name = "待办内容")
    private String content;

    /**
     * 颜色 #999999
     */
    @Excel(name = "颜色 #999999")
    private String color;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Long sort;

    /**
     * 截止时间
     */
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 是否完成 1:完成 2:未完成
     */
    @Excel(name = "是否完成", readConverterExp = " 1=完成,2=未完成")
    private Integer success;

    /**
     * 待办类别id
     */
    @Excel(name = "待办类别id")
    private Long categoryId;

    /**
     * 创建者
     */
    @Excel(name = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
