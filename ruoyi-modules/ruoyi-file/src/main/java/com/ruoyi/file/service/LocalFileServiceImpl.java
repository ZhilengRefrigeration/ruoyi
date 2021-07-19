package com.ruoyi.file.service;

import cn.hutool.core.io.FileUtil;
import com.ruoyi.common.core.exception.CustomException;
import com.ruoyi.file.config.LocalFileConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.file.utils.FileUploadUtils;

import java.io.File;

/**
 * 本地文件存储
 * 
 * @author ruoyi
 */
@Service
public class LocalFileServiceImpl implements IDfsService
{
    private final LocalFileConfig localFileConfig;

    public LocalFileServiceImpl(LocalFileConfig localFileConfig) {
        this.localFileConfig = localFileConfig;
    }

    /**
     * 本地文件上传接口
     * 
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception
    {
       return this.uploadFile(file, null);
    }

    @Override
    public String uploadFile(MultipartFile file, String modules) throws Exception {
        String localFilePath = localFileConfig.getPath();
        String domain = localFileConfig.getDomain();
        String localFilePrefix = localFileConfig.getPrefix();
        if (StringUtils.isBlank(localFilePath) ||
                StringUtils.isBlank(localFilePath) ||
                StringUtils.isBlank(localFilePath) ) {
            throw new CustomException("文件服务器：file 相关配置不能为空!");
        }

        String name = FileUploadUtils.upload(localFilePath + "/" +  StringUtils.defaultString(modules, ""), file);
        return domain + localFilePrefix + name;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)) {
            throw new CustomException("fileUrl不能为空！");
        }
        String key = this.getStorePath(fileUrl);
        String localFilePath = localFileConfig.getPath();

        String filePath = localFilePath + "/" +  key;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new CustomException("文件不存在,或者已经删除:" + filePath);
        }
        try {
            return file.delete();
        } catch (Exception e) {
            throw new CustomException("文件删除失败:" + e.getLocalizedMessage());
        }
    }

    @Override
    public String objectsCapacityStr() {
        String localFilePath = localFileConfig.getPath();
        File file = new File(localFilePath);
        long total = file.getTotalSpace();
        long free = file.getFreeSpace();
        String totalSpace = FileUtil.readableFileSize(total);
        String freeSpace = FileUtil.readableFileSize(free);
        return "总 " + totalSpace + "， 可用 " + freeSpace;
    }

    @Override
    public String presignedUrl(String fileUrl) {
        return fileUrl;
    }

    /**
     * 转换url，为原始的key
     *
     * @param filePath http://localhost:9300/statics/2021/07/16/25292b96-a107-4cf8-baca-e1cb693fd078.jpg
     * @return 2021/07/16/25292b96-a107-4cf8-baca-e1cb693fd078.jpg
     */
    private String getStorePath(String filePath) {
        // 使用方式1
        String domain = localFileConfig.getDomain();
        String localFilePrefix = localFileConfig.getPrefix();
        String publicPath1 = domain + localFilePrefix;
        String key = filePath.replace(publicPath1, "");

        if (key.equals(filePath)) {
            // 使用方式2
            String group = localFileConfig.getPrefix();
            // 获取group起始位置
            int pathStartPos = filePath.indexOf(group) + group.length() + 1;
            key = filePath.substring(pathStartPos, filePath.length());
        }
       return key;
    }
}
