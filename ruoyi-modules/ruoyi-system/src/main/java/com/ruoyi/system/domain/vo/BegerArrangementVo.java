package com.ruoyi.system.domain.vo;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 循环赛实体类
 */
@Setter
@Getter
public class BegerArrangementVo {
    private Integer round;
    private List<CompetitionTeamVsTeam> vsTeamList;
}
