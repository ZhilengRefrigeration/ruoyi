package com.ruoyi.system.domain.vo;
import com.ruoyi.system.domain.Competition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <B>系统名称：future篮球后台系统系统</B><BR>
 * <B>模块名称：BASKETBALL-DOMAIN</B><BR>
 * <B>中文类名：比赛信息表 实体类</B><BR>
 * <B>概要说明：比赛信息表 实体类</B><BR>
 * <B>@version：v1.0</B><BR>
 * <B>版本 修改人 备注</B><BR>
 *
 * @author : gc
 * @date : 2020年08月06日
 */
@ApiModel(value = "比赛详情实体")
@Setter
@Getter
public class CompetitionResponse extends Competition {

    @ApiModelProperty(value = "主队合照", required = false)
    private String mainTeamPhoto;

    @ApiModelProperty(value = "主队介绍", required = false)
    private String mainTeamDes;

    @ApiModelProperty(value = "客队合照", required = false)
    private String guestTeamPhoto;

    @ApiModelProperty(value = "客队介绍", required = false)
    private String guestTeamDes;

    @ApiModelProperty(value = "主队参赛人员", required = false)
    List<CompetitionMembersVo> mainTeamMemberList;

    @ApiModelProperty(value = "主队最近赛事", required = false)
    List<CompetitionTeamVsTeamVo> mainCompetitionTeamVsTeamList;

    @ApiModelProperty(value = "客队参赛人员", required = false)
    List<CompetitionMembersVo> guestTeamMemberList;

    @ApiModelProperty(value = "客队最近赛事", required = false)
    List<CompetitionTeamVsTeamVo> guestCompetitionTeamVsTeamList;
}
