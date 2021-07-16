package com.ruoyi.file.controller;

import com.ruoyi.file.service.IDfsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.file.FileUtils;
import com.ruoyi.system.api.domain.SysFile;

/**
 * 文件请求处理
 *
 * @author ruoyi
 */
@RestController
public class SysFileController {
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Autowired
    private IDfsService dfsService;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public R<SysFile> upload(MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = dfsService.uploadFile(file);
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
     * 删除文件
     */
    @PostMapping("delete")
    public R<Boolean> delete(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)) {
            return R.fail(false, "fileUrl 不能为空");
        }
        try {
            // 上传并返回访问地址
            boolean isOk = dfsService.deleteFile(fileUrl);
            return isOk ? R.ok(true, "删除成功") : R.fail(false, "删除失败");
        } catch (Exception e) {
            log.error("删除文件失败", e);
            return R.fail(false, "删除失败：" + e.getMessage());
        }
    }

    /**
     * 获取文件总大小，占用内容大小；
     * 形如：总 233.57 GB， 可用 72.12 GB
     */
    @GetMapping("objectsCapacityStr")
    public R<String> objectsCapacityStr() {
       return R.ok(dfsService.objectsCapacityStr(), "获取成功");
    }
}