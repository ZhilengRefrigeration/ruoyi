package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 赛会中-赛程-人员得分对象 competition_members_score
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Data
public class CompetitionMembersScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 赛事id(competition的ID) */
    @Excel(name = "赛事id(competition的ID)")
    private Long competitionId;

    /** 赛程id(competition_team_vs_team的ID) */
    @Excel(name = "赛程id(competition_team_vs_team的ID)")
    private Long competitionVsId;

    /** 球队ID */
    @Excel(name = "球队ID")
    private Long teamId;

    /** 球队名 */
    @Excel(name = "球队名")
    private String teamName;

    /** 比赛节数 */
    @Excel(name = "比赛节数")
    private Long nodeNum;

    /** 球队人员ID */
    @Excel(name = "球队人员ID")
    private Long teamUserId;

    /** 球衣号 */
    @Excel(name = "球衣号")
    private String jerseyNumber;

    /** 总得分 */
    @Excel(name = "总得分")
    private Long totalScore;

    /** 2分球 */
    @Excel(name = "2分球")
    private Long twoPoints;

    /** 3分球 */
    @Excel(name = "3分球")
    private Long threePoints;

    /** 罚球 */
    @Excel(name = "罚球")
    private Long penalty;

    /** 篮板 */
    @Excel(name = "篮板")
    private Long backboard;

    /** 前板 */
    @Excel(name = "前板")
    private Long frontPlate;

    /** 后板 */
    @Excel(name = "后板")
    private Long backPlate;

    /** 助攻 */
    @Excel(name = "助攻")
    private Long assists;

    /** 抢断 */
    @Excel(name = "抢断")
    private Long snatch;

    /** 盖帽 */
    @Excel(name = "盖帽")
    private Long block;

    /** 失误 */
    @Excel(name = "失误")
    private Long fault;

    /** 犯规 */
    @Excel(name = "犯规")
    private Long breaks;

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

    /** 是否首发球员 */
    @Excel(name = "是否首发球员")
    private Integer isFirstLaunch;

    @ApiModelProperty(value = "真实姓名", required = false)
    private String realName;

}
