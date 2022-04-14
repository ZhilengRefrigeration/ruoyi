package com.xjs.aop;

import cn.hutool.core.date.DateUtil;
import com.xjs.business.log.RemoteLogFeign;
import com.xjs.business.log.domain.MailLog;
import com.xjs.domain.mall.MailBean;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 邮件日志切面类
 * @author xiejs
 * @since 2022-04-13
 */
@Component
@Aspect
@Log4j2
public class MailLogAspect {

    @Autowired
    private RemoteLogFeign remoteLogFeign;

    /**
     * 声明AOP签名
     */
    @Pointcut("@annotation(com.xjs.annotation.MailLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录时间
        LocalDateTime localDateTime1 = DateUtil.date().toLocalDateTime();
        Object obj = joinPoint.proceed();
        LocalDateTime localDateTime2 = DateUtil.date().toLocalDateTime();
        long between = ChronoUnit.MILLIS.between(localDateTime1, localDateTime2);

        //获取形参
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof MailBean) {
                MailBean mailBean = (MailBean) arg;

                //构建对象
                MailLog mailLog = new MailLog();
                mailLog.setMailType(mailBean.getMailType().getMsg());
                mailLog.setContent(mailBean.getContent());
                mailLog.setTitle(mailBean.getSubject());
                mailLog.setRecipient(mailBean.getRecipient());
                mailLog.setRequestTime(between);

                remoteLogFeign.saveMailLog(mailLog);
            }
        }

        return obj;
    }



}
