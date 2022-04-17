package com.ruoyi.system.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.factory.RemoteUserFallbackFactory;
import com.ruoyi.system.api.model.LoginUser;

import java.util.List;

/**
 * 用户服务 <br>
 * 新增修改用户remote
 * @since 2022-01-21
 * @author ruoyi,xjs
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public R<Boolean> registerUserInfo(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 修改用户登录次数
     * @param id 用户id
     * @param count 登录次数
     * @return int
     */
    @PutMapping("/user/updateForRPC/{id}")
    R<Integer> updateForRPC(@PathVariable("id") String id,@RequestBody Integer count,@RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("/user/selectUserNameByPostCodeAndDeptId")
    R<List<String>> selectUserNameByPostCodeAndDeptId(@RequestParam("postCode") String postCode,
                                                      @RequestParam("deptId") Long deptId);

}
