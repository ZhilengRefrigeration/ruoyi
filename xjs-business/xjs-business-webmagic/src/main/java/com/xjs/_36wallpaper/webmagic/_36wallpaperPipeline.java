package com.xjs._36wallpaper.webmagic;

import cn.hutool.core.collection.CollUtil;
import com.xjs._36wallpaper.pojo._36wallpaper;
import com.xjs._36wallpaper.service._36wallpaperService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * webmagic持久化保存
 * @author xiejs
 * @since 2022-02-20
 */
@Component
@Log4j2
public class _36wallpaperPipeline implements Pipeline {

    @Autowired
    private  _36wallpaperService wallpaperService;

    @Override
    public void process(ResultItems resultItems, Task task) {

        List<_36wallpaper> wallpaperData = resultItems.get("_36wallpaperData");

        if (CollUtil.isNotEmpty(wallpaperData)) {
            wallpaperService.saveBatch(wallpaperData, 25);
        }

    }
}
