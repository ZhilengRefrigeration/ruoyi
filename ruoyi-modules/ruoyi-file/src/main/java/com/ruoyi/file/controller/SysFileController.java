package com.ruoyi.file.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.file.FileUtils;
import com.ruoyi.file.domain.FileResult;
import com.ruoyi.file.service.ISysFileService;
import com.ruoyi.system.api.domain.SysFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysFileController {

    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Autowired
    private ISysFileService sysFileService;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public R<SysFileInfo> upload(MultipartFile file) {
        try {
            // 上传并返回访问地址
            FileResult saveResult = sysFileService.uploadFile(file);
            String requestUrl = saveResult.getRequestUrl();
            // 构建返回结果
            SysFileInfo responseInfo = new SysFileInfo();
            responseInfo.setFileId(saveResult.getFileId());
            responseInfo.setName(FileUtils.getName(requestUrl));
            responseInfo.setUrl(requestUrl);
            return R.ok(responseInfo);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }

    /**
     * 删除文件
     */
    @PostMapping("deleteFiles")
    public R<SysFileInfo> deleteFiles(@RequestParam("fileIds") String[] fileIds) {
        try {
            FileResult fileResult = sysFileService.deleteFiles(fileIds);
            return fileResult.isSuccess() ? R.ok() : R.fail(fileResult.getMessage("删除文件失败"));
        } catch (Exception e) {
            log.error("删除文件失败", e);
            return R.fail(e.getMessage());
        }
    }
}