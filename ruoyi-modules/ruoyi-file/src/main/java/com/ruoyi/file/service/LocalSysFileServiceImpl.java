package com.ruoyi.file.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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
public class LocalSysFileServiceImpl implements ISysFileService
{
    /**
     * 资源映射路径 前缀
     * eg: eg: /statics
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    /**
     * 域名或本机访问地址
     * eg: http://127.0.0.1:9300
     */
    @Value("${file.domain}")
    public String domain;
    
    /**
     * 上传文件存储在本地的根路径
     * eg: D:/ruoyi/uploadPath
     */
    @Value("${file.path}")
    private String localFilePath;

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
        String name = FileUploadUtils.upload(localFilePath + "/" +  StringUtils.defaultString(modules, ""), file);
        String url = domain + localFilePrefix + name;
        return url;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        return false;
    }

    @Override
    public String listObject() {
        return null;
    }
}
