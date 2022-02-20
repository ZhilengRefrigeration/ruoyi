package com.xjs.business.webmagic;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.factory.RemoteWebmagic36wallpaperFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 内部 调用 36壁纸网 爬虫定时任务
 *
 * @author xiejs
 * @since 2022-02-20
 */
@FeignClient(contextId = "remoteWebmagic36wallpaperFeign",
        value = ServiceNameConstants.BUSINESS_WEBMAGIC_SERVICE,
        fallbackFactory = RemoteWebmagic36wallpaperFactory.class)
public interface RemoteWebmagic36wallpaperFeign {

    @GetMapping("/_36wallpaper/taskForPRC")
    public R _36wallpaperControllerTaskForPRC();


}
