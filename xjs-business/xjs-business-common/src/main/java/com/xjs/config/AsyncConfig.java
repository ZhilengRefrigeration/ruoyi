package com.xjs.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xiejs
 * @since 2022-09-20
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Bean
    public ThreadPoolTaskExecutor asyncExecutor() {
        // spring线程池各个方法的作用自行学习，按需配置。
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(99999);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("async-service-");// 看这里①
        executor.setAwaitTerminationSeconds(60);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor();
    }
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex,method,params)->System.out.printf("执行异步方法：%s错误，%s%n", Arrays.toString(params), ex);
    }

}
