package com.ruoyi.system.domain.vo;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.system.domain.UserWxAqrCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 吴一博
 * @date 2022年10月20日 13:42
 * @Description
 */
@Setter
@Getter
public class UserWxAqrCodeVo extends UserWxAqrCode {
    @Excel(name = "用户")
    private String userName;
    @Excel(name = "球队")
    private String teamName;
}
