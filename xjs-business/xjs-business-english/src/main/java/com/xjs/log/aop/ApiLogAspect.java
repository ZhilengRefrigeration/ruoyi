package com.xjs.log.aop;

import cn.hutool.core.date.DateUtil;
import com.xjs.log.consts.ReqConst;
import com.xjs.log.enums.StatusEnum;
import com.xjs.log.mapper.ApiLogMapper;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-26
 */
@Component
@Aspect
@Log4j2
public class ApiLogAspect {

    @Resource
    private ApiLogMapper apiLogMapper;

    /**
     * 声明AOP签名
     */
    @Pointcut("@annotation(com.xjs.log.aop.ApiLog)")
    public void pointcut() {
    }

    /**
     * 环绕切入
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            LocalDateTime localDateTime1 = DateUtil.date().toLocalDateTime();
            Object obj = joinPoint.proceed();
            LocalDateTime localDateTime2 = DateUtil.date().toLocalDateTime();
            long between = ChronoUnit.MILLIS.between(localDateTime1, localDateTime2);
            log.info("调用接口耗费时间:{}ms", between);
            return obj;
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(apiLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, ApiLog apiLog, Object jsonResult) {
        this.handleApiLog(joinPoint, apiLog, null, jsonResult);
    }


    @AfterThrowing(value = "@annotation(apiLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, ApiLog apiLog, Exception e) {
        handleApiLog(joinPoint, apiLog, e, null);
    }


    private void handleApiLog(JoinPoint joinPoint, ApiLog apiLog, final Exception e, Object jsonResult) {
        com.xjs.log.domain.ApiLog entity = new com.xjs.log.domain.ApiLog();
        String name = apiLog.name();//请求名称
        entity.setApiName(name);
        String url = apiLog.url();//请求地址
        entity.setUrl(url);
        Object[] args = joinPoint.getArgs();//请求体
        StringBuilder builder = new StringBuilder();
        for (Object arg : args) {
            builder.append(arg);
        }
        entity.setMethod(apiLog.method());
        entity.setRequest(builder.toString());
        entity.setResponse(Optional.ofNullable(jsonResult).toString());
        if (e != null) {
            entity.setIsSuccess(StatusEnum.ERROR);
        }else {
            entity.setIsSuccess(StatusEnum.SUCCESS);
        }
        apiLogMapper.insert(entity);
    }

}
