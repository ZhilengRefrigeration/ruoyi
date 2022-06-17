package com.xjs.y2048community.webmagic;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 2048爬虫数据处理
 * @author xiejs
 * @since 2022-06-17
 */
@Component
@Log4j2
public class Y2048communityPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {

    }
}
