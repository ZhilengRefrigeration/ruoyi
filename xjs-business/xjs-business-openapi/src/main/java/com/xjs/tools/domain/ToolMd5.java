package com.xjs.tools.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * md5工具实体类
 *
 * @author xiejs
 * @since 2022-05-10
 */
@Data
public class ToolMd5 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 来源字符
     */
    @Excel(name = "来源字符")
    private String source;

    /**
     * 目标字符16位
     */
    @Excel(name = "目标字符16位")
    private String target16;

    /**
     * 目标字符32位
     */
    @Excel(name = "目标字符32位")
    private String target32;

    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
