package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.Competition;
import com.ruoyi.system.domain.CompetitionMembers;
import com.ruoyi.system.domain.CompetitionOfTeam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 吴一博
 * @date 2023年04月14日 10:39
 * @Description
 */
@Data
public class CompetitionExcleVo extends Competition {
    @ApiModelProperty(value = "球队", required = false)
    public CompetitionOfTeam ofTeam;
    @ApiModelProperty(value = "参赛人员", required = false)
    List<CompetitionMembers> teamMemberList;
}
