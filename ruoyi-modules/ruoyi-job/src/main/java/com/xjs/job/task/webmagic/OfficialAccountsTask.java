package com.xjs.job.task.webmagic;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagicWeiXinSouGouFeign;
import com.xjs.job.aop.TaskLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 微信公众号定时任务
 * @author xiejs
 * @since 2022-06-13
 */
@Component("OfficialAccountsTask")
@Log4j2
public class OfficialAccountsTask {
    @Resource
    private RemoteWebmagicWeiXinSouGouFeign remoteWebmagicWeiXinSouGouFeign;

    /**
     * 爬虫 公众号 定时任务执行
     */
    @TaskLog(name = "微信公众号爬虫任务")
    public void execute() {
        log.info("---------------爬虫-公众号定时任务Start-------------------");

        R r = remoteWebmagicWeiXinSouGouFeign.WeiXinOfficialAccountsTaskForPRC();

        log.info("爬虫-公众号定时任务结果:code={},msg={},data={}",r.getCode(),r.getMsg(),r.getData());
        log.info("---------------爬虫-公众号定时任务end---------------------");
    }

}
