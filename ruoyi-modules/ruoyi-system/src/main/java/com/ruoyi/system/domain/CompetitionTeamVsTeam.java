package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Table;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 赛会中-球队VS球队关系对象 competition_team_vs_team
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Data
@Table("competition_team_vs_team")
public class CompetitionTeamVsTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 赛事id(competition的ID) */
    @Excel(name = "赛事id(competition的ID)")
    private Long competitionId;

    /** 主队ID */
    @Excel(name = "主队ID")
    private Long mainTeamId;

    /** 主队名 */
    @Excel(name = "主队名")
    private String mainTeamName;

    /** 客队ID */
    @Excel(name = "客队ID")
    private Long guestTeamId;

    /** 客队名 */
    @Excel(name = "客队名")
    private String guestTeamName;

    /** 比赛时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "比赛时间", width = 60, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date competitionTime;

    /** 球场ID */
    @Excel(name = "球场ID")
    private Long buildingId;

    /** 球场名称 */
    @Excel(name = "球场名称")
    private String buildingName;

    /** 比赛地址 */
    @Excel(name = "比赛地址")
    private String competitionAddress;

    /** 比赛分组(A,B,C) */
    @Excel(name = "比赛分组(A,B,C)")
    private String competitionGroup;

    /** 状态-1=已取消； 0=报名中，1=比赛中；2=已结束 */
    @Excel(name = "状态-1=已取消； 0=报名中，1=比赛中；2=已结束")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

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
    private Integer isDeleted;

    /** 主队得分 */
    @Excel(name = "主队得分")
    private Integer mainTeamScore;

    /** 客队得分 */
    @Excel(name = "客队得分")
    private Integer guestTeamScore;

    /** 比赛类型：循环赛，淘汰赛 */
    @Excel(name = "比赛类型：循环赛，淘汰赛")
    private String vsType;

    /** 系统生成的赛程的批次号 */
    @Excel(name = "系统生成的赛程的批次号")
    private String batchNumber;

}
