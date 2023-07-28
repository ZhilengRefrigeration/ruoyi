package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 building_team_rel
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Data
@Table("building_team_rel")
public class BuildingTeamRel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
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
