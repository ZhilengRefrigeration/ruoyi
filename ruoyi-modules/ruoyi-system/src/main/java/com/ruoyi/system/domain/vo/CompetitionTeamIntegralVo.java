package com.ruoyi.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 吴一博
 * @date 2023年06月26日 17:36
 * @Description
 */
@Data
public class CompetitionTeamIntegralVo implements Serializable {
    @ApiModelProperty(value = "赛会ID")
    private Long competitionId;
    @ApiModelProperty(value = "球队")
    private String teamName;
    @ApiModelProperty(value = "球队Logo")
    private String teamLogo;
    @ApiModelProperty(value = "胜场")
    private Integer win;
    @ApiModelProperty(value = "负场")
    private Integer fail;
    @ApiModelProperty(value = "总得分")
    private Integer totalScore;
    @ApiModelProperty(value = "总输分")
    private Integer totalFailScore;
    @ApiModelProperty(value = "净胜分")
    private Integer netWinPoint;
    @ApiModelProperty(value = "积分")
    private Integer integral;
    @ApiModelProperty(value = "赛程类型")
    private String vsType;
    @ApiModelProperty(value = "球队分组")
    private String competitionGroup;
}
