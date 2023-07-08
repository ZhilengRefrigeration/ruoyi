package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 球队人员对象 team_members
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TeamMembers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 球队ID */
    @Excel(name = "球队ID")
    private Long teamId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 角色编码 */
    @Excel(name = "角色编码")
    private String roleCode;

    /** 球衣号码 */
    @Excel(name = "球衣号码")
    private String jerseyNumber;

    /** 人员状态 */
    @Excel(name = "人员状态")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 删除1 */
    @Excel(name = "删除1")
    private Long isDeleted;

    /** 最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String modifiedBy;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String roleName;

}
