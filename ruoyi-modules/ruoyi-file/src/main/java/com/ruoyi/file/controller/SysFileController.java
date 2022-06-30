package com.ruoyi.file.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.file.FileUtils;
import com.ruoyi.file.service.*;
import com.ruoyi.system.api.domain.SysFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件请求处理
 *
 * @author ruoyi
 */
@RestController
@Api(tags = "文件模块")
public class SysFileController {
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    private final ISysFileService sysFileService;



    @Autowired
    public SysFileController(TencentCosFileServiceImpl tencentCosFileService,
                             FastDfsSysFileServiceImpl fastDfsSysFileService,
                             FtpSysFileServiceImpl ftpSysFileService,
                             LocalSysFileServiceImpl localSysFileService,
                             MinioSysFileServiceImpl minioSysFileService,
                             AliyunOssFileServiceImpl aliyunOssFileService) {

        this.sysFileService = tencentCosFileService;
    }


    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    @ApiOperation("上传文件")
    public R<SysFile> upload(MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return R.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }

    /**
     * <p>
     * 删除文件
     * </p>
     *
     * @param url 文件路径
     * @return R
     * @author xjs
     * @since 2022-03-02
     */
    @ApiOperation("删除文件")
    @DeleteMapping("/remove")
    public R removeFile(@RequestParam("url") String url) {
        sysFileService.removeFile(url);
        return R.ok();
    }
}
