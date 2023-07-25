package com.ruoyi.system.domain.vo;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.system.domain.CompetitionSharePermissions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 吴一博
 * @date 2023年07月20日 17:47
 * @Description
 */
@Data
public class CompetitionSharePermissionsVo {
    @ApiModelProperty(name = "赛事id(competition的ID)")
    private Long competitionId;
    @ApiModelProperty(name = "分享的人")
    List<CompetitionSharePermissions> sharePermissionList;
}
