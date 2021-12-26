package com.xjs.log.aop;

import com.ruoyi.common.log.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-26
 */
@Component
@Aspect
public class ApiLogAspect {

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(apiLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, ApiLog apiLog, Object jsonResult)
    {
        this.handleApiLog(joinPoint, apiLog, null, jsonResult);
    }


    @AfterThrowing(value = "@annotation(apiLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, ApiLog apiLog, Exception e)
    {
        handleApiLog(joinPoint, apiLog, e, null);
    }







    private void handleApiLog(JoinPoint joinPoint, ApiLog apiLog, final Exception e, Object jsonResult) {
        String name = apiLog.name();//请求名称
        String url = apiLog.url();//请求地址
        Object[] args = joinPoint.getArgs();//请求体
        for (Object arg : args) {

        }






    }

}
