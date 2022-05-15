package com.ruoyi.xjt.service.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.xjt.service.XjtUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class XjtUserServiceFallbackFactory implements FallbackFactory<XjtUserService> {
    private static final Logger log = LoggerFactory.getLogger(XjtUserServiceFallbackFactory.class);
    @Override
    public XjtUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new XjtUserService()
        {
            @Override
            public R<Map<String,Object>> getUserInfo(String username, String source)
            {
                return R.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> registerUserInfo(Map<String,Object> sysUser, String source)
            {
                return R.fail("注册用户失败:" + throwable.getMessage());
            }
        };
    }
}
