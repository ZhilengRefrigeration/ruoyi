package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;
import java.util.Date;

/**
 * 【请填写功能名称】对象 building_team_rel
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Data
@TableName("building_team_rel")
public class BuildingTeamRel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** $column.columnComment */

    private String isDeleted;

    /** $column.columnComment */

    private Date createdTime;

    /** $column.columnComment */

    private String createdBy;

    /** $column.columnComment */

    private String modifiedBy;

    /** $column.columnComment */

    private Date lastUpdatedTime;

    /** 场馆id */
    @Excel(name = "场馆id")
    private Long buildingId;

    /** 球队场馆关联表 */
    @Excel(name = "球队场馆关联表")
    private Long teamId;

}
