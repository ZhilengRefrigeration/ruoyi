package com.xjs.topsearch.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 全网热搜api实体
 * @author xiejs
 * @since 2022-01-10
 */
@Data
@TableName("api_topsearch_allnetwork")
public class ApiTopsearchAllnetwork {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 热搜话题 */
    @Excel(name = "热搜话题")
    private String title;

    /** 话题简介 */
    @Excel(name = "话题简介")
    private String digest;

    /** 热搜榜指数 */
    @Excel(name = "热搜榜指数")
    private Long hotnum;

    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;












}
