package com.ruoyi.common.redis.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * redis锁工具类
 *
 * @author Mask
 */
@Component
public class RedisLock
{

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 获取锁对象，加锁前需要先获取此对象
     * @param lockKey
     * @return
     */
    public RLock getLock(String lockKey)
    {
        return redissonClient.getLock(lockKey);
    }

    /**
     * 尝试加锁（续命锁）
     * @param rLock
     * @param waitTime 加锁等待时间， 超时会返回false
     * @return
     * @throws InterruptedException
     */
    public boolean tryLock(RLock rLock, long waitTime) throws InterruptedException
    {
        return rLock.tryLock(waitTime, TimeUnit.SECONDS);
    }

    /**
     *  尝试加锁（自动释放锁）
     * @param rLock
     * @param waitTime 加锁等待时间， 超时会返回false
     * @param releaseTime 锁释放时间，上锁releaseTime秒后自动释放锁
     * @return
     * @throws InterruptedException
     */
    public boolean tryAutoReleaseLock(RLock rLock, long waitTime, long releaseTime) throws InterruptedException
    {
        return rLock.tryLock(waitTime, releaseTime, TimeUnit.SECONDS);
    }

    /**
     * 释放锁
     */
    public void unlock(RLock rLock)
    {
        rLock.unlock();
    }

}
