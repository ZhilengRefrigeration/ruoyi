package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 球队管理对象 basketball_team
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
@Data
@TableName("basketball_team")
public class WxBasketballTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 删除 */
    private String isDeleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 更新人 */
    private String modifiedBy;

    /** 更新时间 */
    private Date lastUpdatedTime;

    /** 球队名称 */
    @Excel(name = "球队名称")
    private String teamName;

    /** 球队简介 */
    @Excel(name = "球队简介")
    private String teamDes;

    /** 球馆id */
    @Excel(name = "球馆id")
    private Long buildId;

    /** 球队图片 */
    private String defaultPicture;

    /** 球馆名称 */
    @Excel(name = "球馆名称")
    private String buildingName;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long createdId;

    /** 球队联系人电话 */
    @Excel(name = "球队联系人电话")
    private String contactTel;

    /** 球队logo */
    private String teamLogo;

}
