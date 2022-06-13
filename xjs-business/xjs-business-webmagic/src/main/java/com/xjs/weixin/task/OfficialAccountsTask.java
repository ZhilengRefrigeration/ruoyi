package com.xjs.weixin.task;

import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.RemoteConfigService;
import com.xjs.annotation.ReptileLog;
import com.xjs.weixin.webmagic.OfficialAccountsPipeline;
import com.xjs.weixin.webmagic.OfficialAccountsProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xjs.consts.RedisConst.REPTILE_WEIXIN_OFFICIAL_COUNT;
import static com.xjs.consts.RedisConst.REPTILE_WEIXIN_OFFICIAL_NAME;
import static com.xjs.consts.ReptileConst.WEIXIN_OFFCIAL_URL;
import static com.xjs.weixin.consts.WeiXinConst.CONFIG_KEY_OFFICIAL;
import static com.xjs.weixin.consts.WeiXinConst.REDIS_KEY_OFFICIAL;

/**
 * 微信公众号定时任务
 *
 * @author xiejs
 * @since 2022-06-13
 */
@Component
@SuppressWarnings("all")
public class OfficialAccountsTask {
    @Autowired
    private OfficialAccountsProcessor officialAccountsProcessor;
    @Autowired
    private RedisService redisService;
    @Autowired
    private OfficialAccountsPipeline officialAccountsPipeline;
    @Resource
    private RemoteConfigService remoteConfigService;

    //解决aop自调用不生成代理对象问题
    @Autowired
    private OfficialAccountsTask officialAccountsTask;

    public void execute() {

        List<String> names = this.convert();
        for (String name : names) {
            String url = WEIXIN_OFFCIAL_URL + name;

            redisService.setCacheObject(REPTILE_WEIXIN_OFFICIAL_NAME,name);

            Long aLong = officialAccountsTask.reptileWeiXinOfficialAccount(url);

        }

    }

    @ReptileLog(name = "微信公众号")
    public Long reptileWeiXinOfficialAccount(String url) {
        //执行爬虫
        Spider.create(officialAccountsProcessor)
                .addUrl(url)//设置爬取地址
                .thread(30)//设置爬取线程数
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(110000)))//设置url去重过滤器
                //.setDownloader(downloader)//设置下载器
                .addPipeline(officialAccountsPipeline)//设置爬取之后的数据操作
                .run();//同步执行

        Integer cache = redisService.getCacheObject(REPTILE_WEIXIN_OFFICIAL_COUNT);
        redisService.deleteObject(REPTILE_WEIXIN_OFFICIAL_COUNT);
        if (cache != null) {
            return Long.valueOf(cache);
        }
        return 0L;
    }

    private List<String> convert() {
        String str = this.getConfigSetting();

        if (StringUtils.isNotEmpty(str) && !str.contains(",")) {
            return Arrays.asList(str);
        }

        if (str.contains(",")) {
            String[] split = str.split(",");
            return Arrays.asList(split);
        }


        return new ArrayList<>();
    }

    /**
     * 获取系统配置参数
     *
     * @return str
     */
    private String getConfigSetting() {
        if (redisService.hasKey(REDIS_KEY_OFFICIAL)) {
            return redisService.getCacheObject(REDIS_KEY_OFFICIAL);
        }

        R<String> r = remoteConfigService.getConfigKeyForRPC(CONFIG_KEY_OFFICIAL);

        if (r.getCode() == HttpStatus.SUCCESS) {
            return r.getData();
        }

        return null;
    }
}
