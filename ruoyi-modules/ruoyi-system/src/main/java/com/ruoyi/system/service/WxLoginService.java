package com.ruoyi.system.service;

import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.api.model.WxLoginUser;
import com.ruoyi.system.domain.vo.WxLoginRequest;

/**
 * @author 吴一博
 * @date 2023年07月06日 10:47
 * @Description
 */
public interface WxLoginService {
    WxLoginUser loginInFromWx(WxLoginRequest entity);

    LoginUser loginFromWx(LoginUser loginUser);
}
