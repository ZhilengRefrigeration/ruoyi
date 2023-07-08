package com.ruoyi.auth.service;

import com.ruoyi.auth.form.WxLoginBody;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 吴一博
 * @date 2023年07月07日 17:52
 * @Description
 */
@Component
public class WxLoginService {
    @Autowired
    private RemoteUserService remoteUserService;
    public LoginUser loginInFromWx(LoginUser entity) {
        // 查询用户信息
        R<LoginUser> userResult  = remoteUserService.getWxUserInfo(entity,SecurityConstants.INNER);
        LoginUser userInfo = userResult.getData();
        return userInfo;
    }
}
