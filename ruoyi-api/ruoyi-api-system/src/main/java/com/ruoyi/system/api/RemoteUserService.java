package com.ruoyi.system.api;

import com.ruoyi.system.api.model.RoleAndPermission;
import com.ruoyi.system.api.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.factory.RemoteUserFallbackFactory;
import com.ruoyi.system.api.model.LoginUser;

import java.util.Set;

/**
 * 用户服务
 * 
 * @author ruoyi
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    public R<UserInfo> getUserInfo(@PathVariable("username") String username);

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/roles/{userId}")
    public R<Set<String>> getRoles(@PathVariable("userId") Long userId);

    /**
     * 根据用户id查询权限
     * @param userId
     * @return
     */
    @GetMapping(value = "/user/permissions/{userId}")
    public R<Set<String>> getPermissions(@PathVariable("userId") Long userId);


    /**
     * 根据用户id查询角色和权限
     *
     * @param userId 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/rolesAndPermissions/{userId}")
    public R<RoleAndPermission> getRolesAndPermissions(@PathVariable("userId") Long userId);


}
