package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 【请填写功能名称】对象 training_info
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Data
@TableName("training_info")
public class TrainingInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long isDeleted;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createdTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createdBy;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String modifiedBy;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date lastUpdatedTime;

    /** 培训机构名称 */
    @Excel(name = "培训机构名称")
    private String trainName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String linkman;

    /** 培训公告 */
    @Excel(name = "培训公告")
    private String trainDesc;

    /** 场馆ID */
    @Excel(name = "场馆ID")
    private Long buildId;

    /** 默认图片 */
    @Excel(name = "默认图片")
    private String defaultPicture;

    /** 培训价格 */
    @Excel(name = "培训价格")
    private String price;

}
