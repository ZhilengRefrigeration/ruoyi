package com.ruoyi.file.service;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.file.config.FtpProperties;
import com.ruoyi.file.utils.FileUploadUtils;
import com.ruoyi.file.utils.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * FTP 文件存储
 * @author xiejs
 * @since 2022-01-24
 */
@Service
@Primary
public class FtpSysFileServiceImpl implements ISysFileService{

    @Autowired
    private FtpUtils ftpUtils;
    @Autowired
    private FtpProperties ftpProperties;

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        Assert.notNull(file, "file is null");
        String extension = FileUploadUtils.getExtension(file);
        String url = "";
        String fileName = UUID.randomUUID() + "." + extension;
        boolean uploadResult = ftpUtils.uploadFile(String.valueOf(DateUtil.thisYear()),fileName , file.getInputStream());
        if (uploadResult) {
            url = ftpProperties.getDomain() + ftpProperties.getPrefix() +"/"+DateUtil.thisYear()+"/"+ fileName;
        }
        return url;
    }
}
