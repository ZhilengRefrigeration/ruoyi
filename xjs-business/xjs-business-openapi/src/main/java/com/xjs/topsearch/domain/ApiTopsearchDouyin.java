package com.xjs.topsearch.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 抖音热搜榜实体
 * @author xiejs
 * @since 2022-01-12
 */
@Data
public class ApiTopsearchDouyin implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 热搜话题 */
    @Excel(name = "热搜话题")
    private String word;

    /** 热搜标签 */
    @Excel(name = "热搜标签")
    private String label;

    /** 热搜指数 */
    @Excel(name = "热搜指数")
    private Long hotindex;

    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
