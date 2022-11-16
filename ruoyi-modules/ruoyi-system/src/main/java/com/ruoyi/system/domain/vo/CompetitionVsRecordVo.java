package com.ruoyi.system.domain.vo;

import com.ruoyi.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 吴一博
 * @date 2022年11月16日 9:46
 * @Description
 */
@ApiModel(value = "比赛-赛程-统分2Vo")
@Setter
@Getter
public class CompetitionVsRecordVo implements Serializable {
    @ApiModelProperty(value = "对阵数据", required = false)
    private CompetitionTeamVsTeamVo teamVsTeamVo;

    private CompetitionResultVo mainTeam;

    private CompetitionResultVo guestTeam;
}
