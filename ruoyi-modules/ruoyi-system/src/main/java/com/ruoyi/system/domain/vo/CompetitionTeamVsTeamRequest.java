package com.ruoyi.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CompetitionTeamVsTeamRequest {
    @ApiModelProperty(value="比赛赛事ID",required=false)
    Long competitionId;
    @ApiModelProperty(value="比赛日期",required=false)

    String competitionDate;
    @ApiModelProperty(value="日期对应的星期几",required=false)
    String weekDay;

    @ApiModelProperty(value="比赛方式：循环赛，淘汰赛",required=false)
    String vsType;

    @ApiModelProperty(value="禁止操作",required=false)
    Boolean isDisabled;

    @ApiModelProperty(value="比赛的双方球队",required=false)
    List<CompetitionTeamVsTeamVo> teamVsTeamList;
}
