package com.xjs.zol.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.xjs.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 爬虫数据中关村笔记本对象 webmagic_zol_notebook
 *
 * @author xjs
 * @since 2022-04-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("webmagic_zol_notebook")
public class ZolNotebook extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 笔记本名称
     */
    @Excel(name = "笔记本名称")
    private String notebookName;

    /**
     * 图片地址
     */
    @Excel(name = "图片地址")
    private String pictureUrl;

    /**
     * 笔记本描述
     */
    @Excel(name = "笔记本描述")
    private String description;

    /**
     * 笔记本详情页面
     */
    @Excel(name = "笔记本详情页面")
    private String detailPage;

    /**
     * 热度
     */
    @Excel(name = "热度")
    private BigDecimal heat;

    /**
     * 价格
     */
    @Excel(name = "价格")
    private String price;

    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
