package com.ruoyi.system.utils;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.system.api.model.WxLoginUser;
import com.ruoyi.system.utils.jwt.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by jackma on 2020/8/17.
 */
@Log4j2
public class LoginUserUtil {
    /**
     * 获取登录人的userId
     *
     * @return
     */
    public static Long getCurrentUserId() {
        String token= JwtUtil.getToken();
        if(StringUtils.isEmpty(token)){
            throw new ServiceException("获取当前登录人token信息失败！");
        }
        return JwtUtil.getUserIdByToken(token);
    }

    /**
     * 获取登录人信息
     *
     * @return
     */
    public static WxLoginUser getLoginUser() {
        String token= JwtUtil.getToken();
        return JwtUtil.getLogUserByToken(token);
    }
}
