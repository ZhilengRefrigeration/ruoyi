package com.ruoyi.system.domain;

import com.mybatisflex.annotation.Table;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 【请填写功能名称】对象 camera_info
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Data
@Table("camera_info")
public class CameraInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
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

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cityCode;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String type;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 设备号 */
    @Excel(name = "设备号")
    private String sn;

    /** 场地ID */
    @Excel(name = "场地ID")
    private Long buildingId;

    /** 播放路径 */
    @Excel(name = "播放路径")
    private String url;

}
