package com.xjs.business.webmagic;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.domain.CopyWritingNetworkDTO;
import com.xjs.business.webmagic.factory.RemoteWebmagicCopyWritingNetworkFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 内部 调用 文案网 爬虫定时任务
 *
 * @author xiejs
 * @since 2022-02-17
 */
@FeignClient(contextId = "remoteWebmagicCopyWritingNetworkFeign",
        value = ServiceNameConstants.BUSINESS_WEBMAGIC_SERVICE,
        fallbackFactory = RemoteWebmagicCopyWritingNetworkFactory.class)
public interface RemoteWebmagicCopyWritingNetworkFeign {

    @GetMapping("copyWritingNetwork/taskForPRC")
    R copyWritingNetworkTaskForPRC();

    @GetMapping("copyWritingNetwork/showCopyWriting")
    public R<List<CopyWritingNetworkDTO>> showCopyWriting();
}
