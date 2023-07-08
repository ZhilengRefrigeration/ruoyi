package com.ruoyi.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CompetitionOfTeamGroupVo {

    @ApiModelProperty(value = "分组名", required = false)
    private String groupName;

    @ApiModelProperty(value="球队列表",required=false)
    private List<CompetitionOfTeamVo> teamList;


}
