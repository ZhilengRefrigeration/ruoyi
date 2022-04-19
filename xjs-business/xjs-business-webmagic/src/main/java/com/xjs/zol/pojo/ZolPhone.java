package com.xjs.zol.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.xjs.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 爬虫数据中关村手机对象 webmagic_zol_phone
 * @author xiejs
 * @since 2022-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("webmagic_zol_phone")
public class ZolPhone extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 手机名称 */
    @Excel(name = "手机名称")
    private String phoneName;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String pictureUrl;

    /** 手机描述 */
    @Excel(name = "手机描述")
    private String description;

    /** 手机详情页面 */
    @Excel(name = "手机详情页面")
    private String detailPage;

    /** 热度 */
    @Excel(name = "热度")
    private BigDecimal heat;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Integer sort;

    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
