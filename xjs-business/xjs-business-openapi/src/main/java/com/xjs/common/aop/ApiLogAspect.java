package com.xjs.common.aop;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.annotation.ApiLog;
import com.xjs.business.log.RemoteLogFeign;
import com.xjs.business.warning.RemoteWarningCRUDFeign;
import com.xjs.business.warning.domain.ApiRecord;
import com.xjs.business.warning.domain.ApiWarning;
import com.xjs.consts.ReqConst;
import com.xjs.enums.WarnLevelEnum;
import com.xjs.enums.WarnTypeEnum;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;
import static com.xjs.consts.ApiWarnHandleConst.NO;
import static com.xjs.consts.ReqConst.ERROR;
import static com.xjs.consts.ReqConst.SUCCESS;

/**
 * api日志切面类
 *
 * @author xiejs
 * @since 2021-12-26
 */
@Component
@Aspect
@Log4j2
public class ApiLogAspect {

    @Autowired
    private RemoteLogFeign remoteLogFeign;

    /**
     * 用来调用预警，记录预警信息
     */
    @Autowired
    private RemoteWarningCRUDFeign remoteWarningCRUDFeign;


    public static final String ERROR_500= "{\"error\":500}";

    /**
     * 声明AOP签名
     */
    @Pointcut("@annotation(com.xjs.annotation.ApiLog)")
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
            //执行预警切入逻辑（降级不预警）
            if (obj instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) obj;

                if (!jsonObject.containsKey(DEMOTE_ERROR)) {
                    this.warning(between, joinPoint);
                } else {
                    //如果降级，接口状态修改为异常
                    this.demoteHandle(joinPoint);
                    log.info("降级！调用接口耗费时间:{}ms", between);
                }
            }

            //返回值为String情况
            if (obj instanceof String) {
                if (StringUtils.isNotEmpty(String.valueOf(obj))) {
                    this.warning(between, joinPoint);
                }else {
                    this.demoteHandle(joinPoint);
                    log.info("降级！调用接口耗费时间:{}ms", between);
                }
            }

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


    /**
     * 异常切点
     *
     * @param joinPoint 连接点
     * @param apiLog    自定义注解
     * @param e         抛出的异常
     */
    @AfterThrowing(value = "@annotation(apiLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, ApiLog apiLog, Exception e) {
        handleApiLog(joinPoint, apiLog, e, null);
    }


    private void handleApiLog(JoinPoint joinPoint, ApiLog apiLog, final Exception e, Object jsonResult) {
        com.xjs.business.log.domain.ApiLog entity = new com.xjs.business.log.domain.ApiLog();
        String name = apiLog.name();//请求名称
        entity.setApiName(name);
        String url = apiLog.url();//请求地址
        entity.setUrl(url);
        Object[] args = joinPoint.getArgs();//请求体

        if (args.length > 1) {
            JSONArray objects = new JSONArray();
            for (Object arg : args) {
                String json = JSON.toJSONString(arg);
                objects.add(json);
                entity.setRequest(objects.toJSONString());
            }
        } else {
            if (args.length > 0) {
                String jsonString = JSON.toJSONString(args[0]);
                entity.setRequest(jsonString);
            }
        }
        entity.setMethod(apiLog.method());
        entity.setIsSuccess(SUCCESS);
        String response = null;
        if (Objects.nonNull(jsonResult)) {
            response = jsonResult.toString();
            entity.setResponse(response);

            //如果降级error
            if (response.contains(ERROR_500)) {
                entity.setIsSuccess(ReqConst.ERROR);
            }
        }
        if (e != null || StringUtils.isEmpty(response)) {
            entity.setIsSuccess(ReqConst.ERROR);
        }
        remoteLogFeign.saveApiLog(entity);
    }


    /**
     * 预警切入
     *
     * @param between   api接口调用时间
     * @param joinPoint aop连接对象
     */
    private void warning(long between, ProceedingJoinPoint joinPoint) {

        Map<String, String> annotationInfo = this.getAnnotationInfo(joinPoint);

        if (CollUtil.isEmpty(annotationInfo)) {
            return;
        }

        String name = annotationInfo.get("name");
        String url = annotationInfo.get("url");

        log.info("调用{}接口耗费时间:{}ms",name, between);


        //根据拿到的url和name查询数据库是否存在，存在则count+1，不存在则add
        ApiRecord apiRecord = new ApiRecord();
        apiRecord.setApiName(name);
        apiRecord.setApiUrl(url);
        apiRecord.setRequestTime((int) between);
        R<List<ApiRecord>> listR = remoteWarningCRUDFeign.selectApiRecordListForRPC(apiRecord);
        if (listR.getCode() == R.SUCCESS) {
            List<ApiRecord> data = listR.getData();
            if (CollUtil.isEmpty(data)) {
                //设置初始请求次数
                apiRecord.setTotalCount(1L);
                apiRecord.setDayCount(1L);
                apiRecord.setLimitCount(30L);
                apiRecord.setStatus(SUCCESS);
                remoteWarningCRUDFeign.saveApiRecordForRPC(apiRecord);
            } else {
                ApiRecord haveApiRecord = data.get(0);

                haveApiRecord.setStatus(SUCCESS);
                haveApiRecord.setRequestTime((int) between);
                haveApiRecord.setTotalCount(haveApiRecord.getTotalCount() + 1L);
                //统计当前的请求次数，隔天清零
                haveApiRecord.setDayCount(haveApiRecord.getDayCount() + 1L);
                Date updateTime = haveApiRecord.getUpdateTime();
                String dateTime = DateUtil.formatDateTime(updateTime);
                Date date = DateUtil.parseDate(dateTime).toJdkDate();
                //当前时间和最后一次修改时间间隔天数（超过1 就清零）
                long compareTime = DateUtil.between(date, new Date(), DateUnit.DAY);
                if (compareTime > 0) {
                    haveApiRecord.setDayCount(1L);
                }
                //置为空让mp自动填充
                haveApiRecord.setUpdateTime(null);
                remoteWarningCRUDFeign.updateApiRecordForRPC(haveApiRecord);
                //判断接口请求是否超过阈值
                if (Objects.nonNull(haveApiRecord.getLimitCount())) {
                    if (haveApiRecord.getDayCount() > haveApiRecord.getLimitCount()) {
                        //把记录添加到预警表中
                        ApiWarning apiWarning = new ApiWarning();
                        apiWarning.setLimitValue(String.valueOf(haveApiRecord.getLimitCount()));
                        apiWarning.setRealValue(String.valueOf(haveApiRecord.getDayCount()));
                        apiWarning.setApiName(haveApiRecord.getApiName());
                        apiWarning.setHandle(NO);
                        apiWarning.setWarningLevel(WarnLevelEnum.NOEMAL.getMessage());
                        if (haveApiRecord.getDayCount() > haveApiRecord.getLimitCount() * 2 &&
                                haveApiRecord.getDayCount() < haveApiRecord.getLimitCount() * 3) {
                            apiWarning.setWarningLevel(WarnLevelEnum.WARNING.getMessage());
                        } else if (haveApiRecord.getDayCount() > haveApiRecord.getLimitCount() * 3) {
                            apiWarning.setWarningLevel(WarnLevelEnum.DANGER.getMessage());
                        }
                        apiWarning.setWarningType(WarnTypeEnum.API.getType());
                        String message = String.format(WarnTypeEnum.API.getMessage(),
                                haveApiRecord.getApiName(),
                                haveApiRecord.getLimitCount(),
                                haveApiRecord.getDayCount());
                        apiWarning.setWarningMessage(message);
                        remoteWarningCRUDFeign.saveApiWarningForRPC(apiWarning);
                    }
                }
            }
        }
    }

    /**
     * 接口降级修改状态异常
     * @param joinPoint 切入点
     */
    private void demoteHandle(ProceedingJoinPoint joinPoint) {

        Map<String, String> map = this.getAnnotationInfo(joinPoint);

        if (CollUtil.isEmpty(map)) {
            return;
        }

        String name = map.get("name");
        String url = map.get("url");
        ApiRecord apiRecord = new ApiRecord();
        apiRecord.setApiName(name);
        apiRecord.setApiUrl(url);

        R<List<ApiRecord>> listR = remoteWarningCRUDFeign.selectApiRecordListForRPC(apiRecord);
        if (listR.getCode() == R.SUCCESS) {
            List<ApiRecord> data = listR.getData();
            if (CollUtil.isNotEmpty(data)) {
                ApiRecord haveApiRecord = data.get(0);
                //置为空让mp自动填充
                haveApiRecord.setUpdateTime(null);
                haveApiRecord.setStatus(ERROR);
                remoteWarningCRUDFeign.updateApiRecordForRPC(haveApiRecord);
            }
        }
    }

    /**
     * 通过反射获取注解信息
     *
     * @param joinPoint 切入点
     * @return 注解信息map
     */
    private Map<String, String> getAnnotationInfo(ProceedingJoinPoint joinPoint) {
        //获取目标类名及方法名
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        Class aclass = signature.getDeclaringType();
        Method[] methods = aclass.getMethods();

        //根据目标的方法名判断当前方法
        for (Method thisMethod : methods) {
            if (method.equals(thisMethod.getName())) {

                //拿到当前方法的注解判断是否为apiLog注解
                Annotation[] declaredAnnotations = thisMethod.getDeclaredAnnotations();

                for (Annotation annotation : declaredAnnotations) {
                    if (annotation instanceof ApiLog) {
                        String name = ((ApiLog) annotation).name();
                        String url = ((ApiLog) annotation).url();

                        Map<String, String> hashMap = new HashMap<>();
                        hashMap.put("name", name);
                        hashMap.put("url", url);
                        return hashMap;
                    }
                }

            }
        }
        return null;
    }

}
