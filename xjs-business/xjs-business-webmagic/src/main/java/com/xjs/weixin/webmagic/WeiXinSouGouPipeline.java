package com.xjs.weixin.webmagic;

import cn.hutool.core.collection.CollUtil;
import com.xjs.weixin.pojo.WeiXinSouGou;
import com.xjs.weixin.service.WeiXinSouGouService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * 微信搜狗网 爬虫数据处理
 * @author xiejs
 * @since 2022-02-22
 */
@Component
@Log4j2
public class WeiXinSouGouPipeline implements Pipeline {

    @Autowired
    private WeiXinSouGouService weiXinSouGouService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<WeiXinSouGou> weiXinSouGouList =resultItems.get("weiXinSouGouList");

        if (CollUtil.isNotEmpty(weiXinSouGouList)) {
            weiXinSouGouService.saveBatch(weiXinSouGouList, 25);
        }


    }
}
