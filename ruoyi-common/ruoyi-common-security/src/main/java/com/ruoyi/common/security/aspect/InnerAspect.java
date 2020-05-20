package com.ruoyi.common.security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.annotation.Inner;

/**
 * 服务间接口不鉴权处理逻辑
 * 
 * @author ruoyi
 */
@Aspect
@Component
public class InnerAspect implements Ordered
{
    private final Logger log = LoggerFactory.getLogger(InnerAspect.class);

    @Around("@annotation(inner)")
    public Object around(ProceedingJoinPoint point, Inner inner) throws Throwable
    {
        String header = ServletUtils.getRequest().getHeader(SecurityConstants.FROM);
        if (inner.value() && !StringUtils.equals(SecurityConstants.FROM_IN, header))
        {
            log.warn("访问接口 {} 没有权限", point.getSignature().getName());
            throw new AccessDeniedException("Access is denied");
        }
        return point.proceed();
    }

    @Override
    public int getOrder()
    {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
