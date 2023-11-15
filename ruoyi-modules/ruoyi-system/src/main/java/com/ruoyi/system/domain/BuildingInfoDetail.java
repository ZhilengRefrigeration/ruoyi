package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.*;

import java.util.Date;

/**
 * 【请填写功能名称】对象 building_info_detail
 * 
 * @author ruoyi
 * @date 2023-07-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("building_info_detail")
public class BuildingInfoDetail extends BaseEntity
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

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long buildingId;

    /** 营业时间 */
    @Excel(name = "营业时间")
    private String businessHours;

    /** 联系人 */
    @Excel(name = "联系人")
    private String linkman;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String linkphone;

    /** 收费标准 */
    @Excel(name = "收费标准")
    private String feeStandard;

    /** 球馆标签 */
    @Excel(name = "球馆标签")
    private String labelDesc;

    /** 公告 */
    @Excel(name = "公告")
    private String notice;

}
