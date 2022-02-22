package com.xjs.weixin.task;

import com.xjs.annotation.ReptileLog;
import com.xjs.weixin.service.WeiXinSouGouService;
import com.xjs.weixin.webmagic.WeiXinSouGouProcessor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ReptileConst.WEIXIN_SOUGOU_URL;

/**
 * 微信搜狗任务
 * @author xiejs
 * @since 2022-02-22
 */
@Component
@Log4j2
public class WeiXinSouGouTask {

    @Autowired
    private WeiXinSouGouProcessor weiXinSouGouProcessor;
    @Autowired
    private WeiXinSouGouService weiXinSouGouService;

    @ReptileLog(name = "微信搜狗", url = WEIXIN_SOUGOU_URL)
    public Long reptileWeiXinSouGou() {
        Long run = weiXinSouGouProcessor.run();

        //删除重复数据
        int count = weiXinSouGouService.deleteRepeatData();
        log.info("微信搜狗删除重复数据数：" + count);

        return run;
    }

}
