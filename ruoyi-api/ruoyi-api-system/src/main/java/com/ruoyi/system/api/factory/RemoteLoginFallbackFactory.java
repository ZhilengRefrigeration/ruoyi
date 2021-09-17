package com.ruoyi.system.api.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteLoginService;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginBody;
import com.ruoyi.system.api.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 * 
 * @author ruoyi
 */
@Component
public class RemoteLoginFallbackFactory implements FallbackFactory<RemoteLoginService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteLoginFallbackFactory.class);

    @Override
    public RemoteLoginService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteLoginService()
        {
            @Override
            public R<?> login(LoginBody loginUser, String source) {
                return R.fail("用户登录失败:" + throwable.getMessage());
            }
        };
    }
}
