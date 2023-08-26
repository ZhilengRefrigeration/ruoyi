package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 赛会中-赛程结果记录对象 competition_result
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Data
@Table("competition_result")
public class CompetitionResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /** 赛事id(competition的ID) */
    @Excel(name = "赛事id(competition的ID)")
    private Long competitionId;

    /** 赛程id(competition_team_vs_team的ID) */
    @Excel(name = "赛程id(competition_team_vs_team的ID)")
    private Long competitionVsId;

    @ApiModelProperty(value = "competition_of_team表的主键ID", required = false)
    private Long competitionOfTeamId;
    /** 球队ID */
    @Excel(name = "球队ID")
    private Long teamId;

    /** 球队名 */
    @Excel(name = "球队名")
    private String teamName;

    /** 比赛节数1计分 */
    @Excel(name = "比赛节数1计分")
    private Integer oneNodeScore;

    /** 比赛节数2计分 */
    @Excel(name = "比赛节数2计分")
    private Integer twoNodeScore;

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
    private Long isDeleted;

    /** 比赛节数3计分 */
    @Excel(name = "比赛节数3计分")
    private Integer threeNodeScore;

    /** 比赛节数4计分 */
    @Excel(name = "比赛节数4计分")
    private Integer fourNodeScore;

    /** 比赛节数5计分 */
    @Excel(name = "比赛节数5计分")
    private Integer fiveNodeScore;

    /** 比赛节数6计分 */
    @Excel(name = "比赛节数6计分")
    private Integer sixNodeScore;

    /** 比赛积分 */
    @Excel(name = "比赛积分")
    private Integer integral;
    @ApiModelProperty(value = "比赛结果", required = false)
    private String vsResult;
    @ApiModelProperty(value = "比赛类型：0=循环赛，1=淘汰赛", required = false)
    private String vsType;
    @ApiModelProperty(value = "比赛总分", required = false)
    private Integer totalScore;
    @ApiModelProperty(value = "净胜分", required = false)
    private Integer netWinPoint;

}
