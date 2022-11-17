package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 吴一博
 * @date 2022年11月11日 17:02
 * @Description
 */
@Data
public class CompetitionTeamVsTeamVo extends CompetitionTeamVsTeam {
    @ApiModelProperty(value = "主队logo", required = false)
    private String mainTeamLogo;

    @ApiModelProperty(value = "客队logo", required = false)
    private String guestTeamLogo;

    @ApiModelProperty(value = "中文状态", required = false)
    private String statusName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "比赛时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date competitionDate;
    private String weekDayName;
    private String theTime;
}