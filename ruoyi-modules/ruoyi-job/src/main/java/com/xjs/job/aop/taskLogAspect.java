package com.xjs.job.aop;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.log.RemoteLogFeign;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 任务日志切面类
 *
 * @author xiejs
 * @since 2022-03-01
 */
@Component
@Aspect
@Log4j2
public class taskLogAspect {

    @Resource
    private RemoteLogFeign remoteLogFeign;


    /**
     * 声明AOP签名
     */
    @Pointcut("@annotation(com.xjs.job.aop.TaskLog)")
    public void pointcut() {
    }

    /**
     * 环绕切入
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        try {
            //切入前-----
            // 开始时间
            long startTime = System.currentTimeMillis();

            obj = joinPoint.proceed();

            //切入后-----
            // 结束时间
            long endTime = System.currentTimeMillis();
            long between = endTime - startTime;
            log.info("调用定时任务耗费时间:{}ms", between);

            this.handle(joinPoint, between);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return obj;
    }


    /**
     * 处理切面逻辑
     *
     * @param joinPoint 切入点
     * @param between   请求时长
     */
    private void handle(ProceedingJoinPoint joinPoint, Long between) {
        //获取目标类名及方法名
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        Class aClass = signature.getDeclaringType();
        Method[] methods = aClass.getMethods();
        String className = aClass.getName();

        com.xjs.business.log.domain.TaskLog taskLog = new com.xjs.business.log.domain.TaskLog();

        taskLog.setMethod(method);
        taskLog.setClassPath(className);
        taskLog.setRequestTime(between);

        //根据目标的方法名判断当前方法
        for (Method thisMethod : methods) {
            if (method.equals(thisMethod.getName())) {
                Annotation[] declaredAnnotations = thisMethod.getDeclaredAnnotations();
                for (Annotation annotation : declaredAnnotations) {
                    if (annotation instanceof TaskLog) {
                        String name = ((TaskLog) annotation).name();
                        taskLog.setName(name);
                    }
                }
            }
        }

        R<Object> r = remoteLogFeign.saveTaskLog(taskLog);
        log.info(r.getMsg());
    }


}
