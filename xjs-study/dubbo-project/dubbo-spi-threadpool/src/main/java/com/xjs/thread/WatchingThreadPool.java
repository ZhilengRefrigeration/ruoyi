package com.xjs.thread;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.threadpool.support.fixed.FixedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author xiejs
 * @since 2022-05-24
 */
public class WatchingThreadPool extends FixedThreadPool implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(WatchingThreadPool.class);

    //定义阈值
    private static final double ALARM_PERCENT = 0.90;

    //存储map
    private final Map<URL, ThreadPoolExecutor> THREAD_POOLS = new ConcurrentHashMap<>();


    public WatchingThreadPool() {
        //每隔三秒打印线程使用情况
        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(this, 1, 3, TimeUnit.SECONDS);
    }

    //通过父类创建线程池
    @Override
    public Executor getExecutor(URL url) {
        final Executor executor = super.getExecutor(url);
        if (executor instanceof ThreadPoolExecutor) {
            THREAD_POOLS.put(url, (ThreadPoolExecutor) executor);
        }
        return executor;
    }

    @Override
    public void run() {
        //遍历线程池
        for (Map.Entry<URL, ThreadPoolExecutor> entry : THREAD_POOLS.entrySet()) {
            final URL url = entry.getKey();
            final ThreadPoolExecutor executor = entry.getValue();

            //开始计算相关指标
            final int activeCount = executor.getActiveCount();
            final int poolSize = executor.getCorePoolSize();
            double usedPercent = activeCount / (poolSize * 1.0);

            log.info("线程池运行状况：[{}/{}:{}%]",activeCount,poolSize,usedPercent);

            if (usedPercent > ALARM_PERCENT) {
                log.warn("超过警戒值！！！URL:{}",url);
            }
        }
    }
}
