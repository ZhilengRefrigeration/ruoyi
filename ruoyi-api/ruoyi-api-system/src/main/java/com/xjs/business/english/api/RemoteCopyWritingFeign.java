package com.xjs.business.english.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.SysFile;
import com.ruoyi.system.api.factory.RemoteFileFallbackFactory;
import com.xjs.business.english.api.domain.CopyWriting;
import com.xjs.business.english.api.factory.RemoteCopyWritingFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xiejs
 * @desc RPC远程调用文案接口服务
 * @create 2021-12-27
 */
@FeignClient(contextId = "remoteCopyWritingFeign", value = ServiceNameConstants.BUSINESS_ENGLISH_SERVICE, fallbackFactory = RemoteCopyWritingFactory.class)
public interface RemoteCopyWritingFeign {
    /**
     * 获取文案
     *
     * @return 结果
     */
    @GetMapping(value = "/copyWriting/forPRC")
    R<CopyWriting> copyWriting();

}
