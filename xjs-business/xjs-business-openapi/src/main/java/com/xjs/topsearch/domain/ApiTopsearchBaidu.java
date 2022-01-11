package com.xjs.topsearch.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 天行api百度热搜榜
 * @author xiejs
 * @since 2022-01-11
 */
@Data
public class ApiTopsearchBaidu implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 热搜话题 */
    @Excel(name = "热搜话题")
    private String title;

    /** 简介描述 */
    @Excel(name = "简介描述")
    private String digest;

    /** 热搜指数 */
    @Excel(name = "热搜指数")
    private Long hotnum;

    /** 发展趋势 */
    @Excel(name = "发展趋势")
    private String trend;

    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
