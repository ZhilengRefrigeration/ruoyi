package com.xjs.weather.service.impl;

import com.ruoyi.common.redis.service.RedisService;
import com.xjs.exception.ApiException;
import com.xjs.weather.domain.IPInfoVo;
import com.xjs.weather.factory.impl.RollIPFactory;
import com.xjs.weather.service.IPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.xjs.consts.RedisConst.IP_INFO;
import static com.xjs.consts.RedisConst.IP_INFO_EXPIRE;

/**
 * ip api服务实现
 * @author xiejs
 * @since 2022-01-15
 */
@Service
public class IPServiceImpl implements IPService {

    @Autowired
    private RollIPFactory rollIPFactory;
    @Autowired
    private RedisService redisService;

    @Override
    public IPInfoVo getIPApiData() {
        if (redisService.hasKey(IP_INFO)) {
            return (IPInfoVo) redisService.getCacheObject(IP_INFO);
        }else {
            IPInfoVo ipInfoVo = rollIPFactory.IpApi();
            if (Objects.nonNull(ipInfoVo)) {
                redisService.setCacheObject(IP_INFO, ipInfoVo, IP_INFO_EXPIRE, TimeUnit.MINUTES);
                return ipInfoVo;
            }else {
                throw new ApiException("获取IP信息异常！！");
            }
        }
    }
}
