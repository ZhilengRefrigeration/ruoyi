package com.xjs.domain.todo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.xjs.entity.BaseEntity;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 待办分类实体类
 *
 * @author xiejs
 * @since 2022-04-28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TodoCategory extends BaseEntity implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空", groups = {UpdateGroup.class, AddGroup.class})
    @Size(max = 5, message = "请控制分类名称长度在 5 字符", groups = {UpdateGroup.class, AddGroup.class})
    private String name;

    /**
     * 颜色
     */
    @NotBlank(message = "颜色不能为空", groups = {UpdateGroup.class, AddGroup.class})
    @Size(max = 10, message = "请控制分类名称长度在 10 字符", groups = {UpdateGroup.class, AddGroup.class})
    private String color;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空", groups = {UpdateGroup.class, AddGroup.class})
    @Max(value = 100, message = "排序范围在 0 - 100 ", groups = {UpdateGroup.class, AddGroup.class})
    private Long sort;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
