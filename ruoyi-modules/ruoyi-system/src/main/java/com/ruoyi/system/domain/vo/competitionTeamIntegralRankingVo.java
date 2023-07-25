package com.ruoyi.system.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 吴一博
 * @date 2023年07月19日 18:27
 * @Description
 */
@Data
public class competitionTeamIntegralRankingVo {
    private String competitionGroup;
    public List<CompetitionTeamIntegralVo> integralList;
}
