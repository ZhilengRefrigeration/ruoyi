package com.ruoyi.system.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 吴一博
 * @date 2023年06月26日 17:36
 * @Description
 */
@Data
public class CompetitionTeamIntegralVo implements Serializable {
    private String teamName;
    private String teamLogo;
    private Integer win;
    private Integer fail;
    private Integer totalScore;
    private Integer integral;
}
