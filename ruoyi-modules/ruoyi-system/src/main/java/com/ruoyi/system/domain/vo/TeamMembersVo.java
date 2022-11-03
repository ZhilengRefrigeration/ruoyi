package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.WxUser;
import lombok.Data;

/**
 * @author 吴一博
 * @date 2022年11月03日 16:11
 * @Description
 */
@Data
public class TeamMembersVo extends WxUser {
    private Long teamId;
    private Long userId;
    private String roleCode;
    private String jerseyNumber;
    private String roleName;
    private String status;
}
