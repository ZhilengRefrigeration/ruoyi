package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.CompetitionOfTeam;
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
}
