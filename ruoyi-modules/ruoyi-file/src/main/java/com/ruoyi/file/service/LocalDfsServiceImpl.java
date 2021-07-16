package com.ruoyi.file.service;

import com.ruoyi.common.core.exception.CustomException;
import com.ruoyi.file.config.LocalConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.file.utils.FileUploadUtils;

/**
 * 本地文件存储
 * 
 * @author ruoyi
 */
@Primary
@Service
public class LocalDfsServiceImpl implements IDfsService
{
    private final LocalConfig localConfig;

    public LocalDfsServiceImpl(LocalConfig localConfig) {
        this.localConfig = localConfig;
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
        String localFilePath = localConfig.getLocalFilePath();
        String domain = localConfig.getDomain();
        String localFilePrefix = localConfig.getLocalFilePrefix();

        String name = FileUploadUtils.upload(localFilePath + "/" +  StringUtils.defaultString(modules, ""), file);
        return domain + localFilePrefix + name;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        return false;
    }

    @Override
    public String objectsCapacityStr() {
        throw new CustomException("本地存储-获取文件占用空间功能，敬请期待");
    }
}
