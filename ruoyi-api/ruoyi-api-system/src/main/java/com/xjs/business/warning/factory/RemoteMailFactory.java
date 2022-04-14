package com.xjs.business.warning.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.warning.RemoteMailFeign;
import com.xjs.business.warning.domain.MailBean;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 远程调用预警服务邮件feign降级
 * @author xiejs
 * @since 2022-04-13
 */
@Component
@Log4j2
public class RemoteMailFactory implements FallbackFactory<RemoteMailFeign> {
    @Override
    public RemoteMailFeign create(Throwable cause) {
        return new RemoteMailFeign() {
            @Override
            public void sendWeatherMailForRPC() {
            }

            @Override
            public R sendMail(MailBean mailBean) {
                log.error("远程发送邮件调用异常:"+cause.getMessage());
                return R.fail("远程发送邮件调用异常:"+cause.getMessage());
            }
        };
    }
}
