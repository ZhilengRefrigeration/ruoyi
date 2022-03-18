package com.xjs.weixin.service.impl;

import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.RemoteConfigService;
import com.xjs.exception.BusinessException;
import com.xjs.weixin.service.WeiXinLinkService;
import com.xjs.weixin.task.WeiXinLinkTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.regex.Pattern;

import static com.xjs.consts.RedisConst.REPTILE_WEIXIN_LINK_COUNT;
import static com.xjs.consts.RegexConst.FILE_PATH_REGEX;
import static com.xjs.consts.RegexConst.URL_REGEX;
import static com.xjs.weixin.consts.WeiXinConst.*;


/**
 * 微信文章链接service接口实现
 *
 * @author xiejs
 * @since 2022-03-17
 */
@Service
public class WeiXinLinkServiceImpl implements WeiXinLinkService {

    @Autowired
    private WeiXinLinkTask weiXinLinkTask;
    @Resource
    private RemoteConfigService remoteConfigService;
    @Autowired
    private RedisService redisService;

    @Override
    public Boolean getPicture(String link) {
        //校验link有效性
        if (StringUtils.isBlank(link)) {
            throw new BusinessException("链接地址不能为空");
        }

        boolean matches = Pattern.matches(URL_REGEX, link);
        if (!matches) {
            throw new BusinessException("链接格式不正确");
        }

        Long count = weiXinLinkTask.reptileWeiXinLink(link);
        return count != 0L;
    }

    @Override
    public String getSettings() {
        if (redisService.hasKey(REDIS_KEY)) {
            return redisService.getCacheObject(REDIS_KEY);
        }

        R<String> r = remoteConfigService.getConfigKeyForRPC(CONFIG_KEY);

        if (r.getCode() == HttpStatus.SUCCESS) {
            return r.getData();
        }

        return null;
    }

    @Override
    public boolean updateSettings(String path) {
        this.checkRunning();

        boolean matches = Pattern.matches(FILE_PATH_REGEX, path);
        if (!matches) {
            throw new BusinessException("文件路径格式不正确");
        }
        R r = remoteConfigService.editForRPC(CONFIG_KEY, path);
        return r.getCode() == HttpStatus.SUCCESS;
    }

    @Override
    public boolean restSettings() {
        this.checkRunning();
        R r = remoteConfigService.editForRPC(CONFIG_KEY, PATH);

        return r.getCode() == HttpStatus.SUCCESS;
    }


    /**
     * 检测是否正在运行
     */
    private void checkRunning() {
        //判断爬虫是否正在执行，正在执行不可修改！
        if (redisService.hasKey(REPTILE_WEIXIN_LINK_COUNT)) {
            throw new BusinessException("爬虫正在执行中！暂时无法修改，请稍后再试");
        }
    }
}
