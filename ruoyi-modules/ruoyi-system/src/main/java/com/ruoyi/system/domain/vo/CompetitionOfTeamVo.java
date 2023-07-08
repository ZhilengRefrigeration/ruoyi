package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.CompetitionOfTeam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 吴一博
 * @date 2022年11月11日 11:12
 * @Description
 */
@Data
public class CompetitionOfTeamVo extends CompetitionOfTeam {
    /** 球队logo */
    private String teamLogo;
    private String defaultPicture;

    private String captain;


    @ApiModelProperty(value="球队图片",required=false)
    private String teamImg;

    @ApiModelProperty(value="用户id",required=false)
    private Long userId;

    @ApiModelProperty(value="球队队长userId",required=false)
    private String teamManagerUserId;

    @ApiModelProperty(value="胜场",required=false)
    private int victory;

    @ApiModelProperty(value="负场",required=false)
    private int lose;

    @ApiModelProperty(value="积分",required=false)
    private Integer integral = 0;

    @ApiModelProperty(value="赛程类型【0 循环赛 1 淘汰赛】",required=false)
    private int vsType;
}
