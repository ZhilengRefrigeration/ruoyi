package com.ruoyi.system.api.factory;

import com.ruoyi.system.api.model.RoleAndPermission;
import com.ruoyi.system.api.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.model.LoginUser;
import feign.hystrix.FallbackFactory;

import java.util.Set;

/**
 * 用户服务降级处理
 * 
 * @author ruoyi
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public R<UserInfo> getUserInfo(String username)
            {
                return R.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public R<Set<String>> getRoles(Long userId) {
                return R.fail("获取用户角色失败:" + throwable.getMessage());
            }

            @Override
            public R<Set<String>> getPermissions(Long userId) {
                return R.fail("获取用户权限失败:" + throwable.getMessage());
            }

            @Override
            public R<RoleAndPermission> getRolesAndPermissions(Long userId) {
                return R.fail("获取用户角色和权限失败:" + throwable.getMessage());
            }
        };
    }
}
