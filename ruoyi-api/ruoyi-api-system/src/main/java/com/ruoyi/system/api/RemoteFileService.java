package com.ruoyi.system.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.SysFile;
import com.ruoyi.system.api.factory.RemoteFileFallbackFactory;

/**
 * 文件服务
 * 
 * @author ruoyi
 */
@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.FILE_SERVICE, fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService
{
    /**
     * 上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    R<SysFile> upload(@RequestPart(value = "file") MultipartFile file);

    /**
     * 删除文件
     * 根据文件的完整url进行删除资源
     */
    @PostMapping("delete")
    R<Boolean> delete(@RequestParam("fileUrl")  String fileUrl);

    /**
     * 获取文件服务器的容量
     * 获取文件服务器占用资源的容量
     */
    @GetMapping("objectsCapacityStr")
    R<String> capacityStr();

    /**
     * 临时安全凭证、获取加签的url; 根据输入的url,获取带有临时安全凭证的url
     * @param fileUrl 待加签的url
     */
    @GetMapping("getPresignedUrl")
    R<String> getPresignedUrl(@RequestParam(value = "fileUrl") String fileUrl);
}
