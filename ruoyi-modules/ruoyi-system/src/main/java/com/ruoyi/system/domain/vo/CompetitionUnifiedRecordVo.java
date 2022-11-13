package com.ruoyi.system.domain.vo;
import com.ruoyi.system.domain.CompetitionMembersScore;
import com.ruoyi.system.domain.CompetitionResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "比赛-赛程-统分Vo")
@Setter
@Getter
public class CompetitionUnifiedRecordVo implements Serializable {

    @ApiModelProperty(value = "对阵数据", required = false)
    private CompetitionTeamVsTeamVo teamVsTeamVo;

    @ApiModelProperty(value = "对阵节数统分数据", required = false)
    private List<CompetitionResult> competitionResultList;

    @ApiModelProperty(value = "赛程队员数据", required = false)
    private List<CompetitionMembersScore> competitionMembersScoreList;

    @ApiModelProperty(value = "客队队员数据", required = false)
    private List<CompetitionMembersScore> mainMembersScoreList;

    @ApiModelProperty(value = "客队队员数据", required = false)
    private List<CompetitionMembersScore> gustMembersScoreList;

}
