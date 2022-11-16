package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.CompetitionMembersScore;
import com.ruoyi.system.domain.CompetitionResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 吴一博
 * @date 2022年11月16日 9:48
 * @Description
 */
@Data
public class CompetitionResultVo extends CompetitionResult {
    @ApiModelProperty(value = "客队队员数据", required = false)
    private List<CompetitionMembersScoreVo> membersScoreList;
}
