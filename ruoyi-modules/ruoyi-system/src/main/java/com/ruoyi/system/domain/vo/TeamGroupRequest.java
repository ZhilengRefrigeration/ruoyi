package com.ruoyi.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TeamGroupRequest {
    @ApiModelProperty(value = "分组", required = false)
    private String group;
    @ApiModelProperty(value = "比赛ID", required = false)
    private Long competitionId;
    @ApiModelProperty(value = "IDS", required = false)
    private List<Long> ofTeamIds;
}
