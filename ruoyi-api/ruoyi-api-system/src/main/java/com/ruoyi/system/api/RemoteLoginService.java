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

/**
 * 无界面登录服务
 *
 * 若部署环境不同，IP无法相互访问如部分应用在docker中，部分在外部，则最好指定url，明确应用的访问地址
 * kubernetes.docker.internal 为指向本机的IP地址的本地域名，若使用则需要在hosts文件中添加
 * url = "http://kubernetes.docker.internal:9200/",
 */
@FeignClient(contextId = "remoteLoginService",value = ServiceNameConstants.AUTH_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
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