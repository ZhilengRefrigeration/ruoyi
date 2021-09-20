package com.ruoyi.system.api;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.factory.RemoteUserFallbackFactory;
import com.ruoyi.system.api.model.LoginBody;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "remoteLoginService",url = "http://lynn.server.local:9200/", value = ServiceNameConstants.AUTH_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteLoginService {
    /**
     * 通过用户名查询用户信息
     *
     * @param loginUser 用户名
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/login")
    public R<?> login(@RequestBody LoginBody loginUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
