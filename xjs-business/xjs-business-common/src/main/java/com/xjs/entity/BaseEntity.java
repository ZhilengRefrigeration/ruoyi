package com.xjs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xjs.validation.group.SelectGroup;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 通用参数
 * @author xiejs
 * @since 2022-02-22
 */
@Data
public class BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 通用查询条件
     */
    @TableField(exist = false)
    @Size(max = 100, message = "请控制长度在100字符", groups = { SelectGroup.class})
    private String condition;

    /**
     * 查询条件的结束时间
     */
    @TableField(exist = false)
    private Date endCreateTime;

}
