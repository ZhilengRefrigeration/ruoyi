package com.xjs.common.aop;

import com.ruoyi.common.core.domain.R;
import com.xjs.annotation.ReptileLog;
import com.xjs.business.log.RemoteLogFeign;
import com.xjs.business.log.domain.WebmagicLog;
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

import static com.xjs.consts.ReqConst.ERROR;
import static com.xjs.consts.ReqConst.SUCCESS;

/**
 * 爬虫日志切面类
 *
 * @author xiejs
 * @since 2022-02-17
 */
@Component
@Aspect
@Log4j2
public class reptileLogAspect {

    @Resource
    private RemoteLogFeign remoteLogFeign;

    /**
     * 声明AOP签名
     */
    @Pointcut("@annotation(com.xjs.annotation.ReptileLog)")
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
            log.info("调用爬虫接口耗费时间:{}ms", between);

            this.handle(joinPoint, between, obj);
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
     * @param obj       返回值
     */
    private void handle(ProceedingJoinPoint joinPoint, Long between, Object obj) {
        //获取目标类名及方法名
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        Class aClass = signature.getDeclaringType();
        Method[] methods = aClass.getMethods();

        //根据目标的方法名判断当前方法
        for (Method thisMethod : methods) {
            if (method.equals(thisMethod.getName())) {
                Annotation[] declaredAnnotations = thisMethod.getDeclaredAnnotations();
                for (Annotation annotation : declaredAnnotations) {
                    if (annotation instanceof ReptileLog) {
                        String name = ((ReptileLog) annotation).name();
                        String url = ((ReptileLog) annotation).url();

                        WebmagicLog webmagicLog = new WebmagicLog();
                        webmagicLog.setName(name);
                        webmagicLog.setUrl(url);
                        webmagicLog.setRequestTime(between);
                        if (obj instanceof Long) {
                            webmagicLog.setComplexRate((Long) obj);
                        }
                        this.saveData(webmagicLog);
                    }
                }
            }
        }

    }

    /**
     * 持久化保存数据
     */
    private void saveData(WebmagicLog webmagicLog) {
        if (webmagicLog.getComplexRate() != null && webmagicLog.getComplexRate() == 0L) {
            webmagicLog.setStatus(ERROR);
        } else {
            webmagicLog.setStatus(SUCCESS);
        }
        R<Object> r = remoteLogFeign.saveReptileLog(webmagicLog);
        log.info(r.getMsg());
    }


}
