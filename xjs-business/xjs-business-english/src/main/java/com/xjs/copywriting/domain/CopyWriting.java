package com.xjs.copywriting.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiejs
 * @desc  文案实体类
 * @create 2021-12-27
 */
@TableName("api_copywriting")
@Data
public class CopyWriting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    /** 文案内容 */
    @Excel(name = "文案内容")
    private String content;

    /** 文案来源 */
    @Excel(name = "文案来源")
    private String source;

    @Excel(name = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
