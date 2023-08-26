package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Table;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 赛会中-参赛队伍对象 competition_of_team
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Data
@Table("competition_of_team")
public class CompetitionOfTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 赛事id(competition的ID) */
    @Excel(name = "赛事id(competition的ID)")
    private Long competitionId;

    /** 球队ID */
    @Excel(name = "球队ID")
    private Long teamId;

    /** 球队名 */
    @Excel(name = "球队名")
    private String teamName;

    /** 球队所属的组 */
    @Excel(name = "球队所属的组")
    private String competitionGroup;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 状态 0=申请，1=同意；-1=驳回 */
    @Excel(name = "状态 0=申请，1=同意；-1=驳回")
    private Integer status;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 最后修改人 */
    @Excel(name = "最后修改人")
    private String modifiedBy;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Long isDeleted;

    /** 联系人 */
    @Excel(name = "领队人")
    private String contacts;
    @Excel(name = "是否隐藏头像")
    private Boolean isHideAvatar;
    /** 联系人电话 */
    @Excel(name = "领队人电话")
    private String contactsTel;

    /** 联系人电话区号 */
    @Excel(name = "联系人电话区号")
    private String contactsAreaCode;

    /** 组内的序号 */
    @Excel(name = "组内的序号")
    private Integer serialNumber;
    @Excel(name = "球队logo")
    private String teamLogo;
    @Excel(name = "球队队长")
    private String captain;

}
