package com.xjs.topsearch.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 微博热搜实体
 * @author xiejs
 * @since 2022-01-12
 */
@Data
public class ApiTopsearchWeibo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 热搜话题 */
    @Excel(name = "热搜话题")
    private String hotword;

    /** 热搜指数 */
    @Excel(name = "热搜指数")
    private String hotnum;

    /** 热搜标签 */
    @Excel(name = "热搜标签")
    private String hottag;

    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
