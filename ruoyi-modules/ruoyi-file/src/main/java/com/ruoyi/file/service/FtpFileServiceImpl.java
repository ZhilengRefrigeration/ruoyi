package com.ruoyi.file.service;

import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import com.ruoyi.common.core.exception.CustomException;
import com.ruoyi.file.config.FtpConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * ftp目录用来上传文件；
 * ftp, 如：iis、linux ftp、vsftpd、FileZilla Server，需要自己搭建服务
 * @author dazer
 */
@Service
public class FtpFileServiceImpl implements IDfsService {
    @Autowired
    private FtpConfig ftpConfig;

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        return this.uploadFile(file, null);
    }

    @Override
    public String uploadFile(MultipartFile file, String modules) throws Exception {
        validateModule(file, modules);
        String fileName = extractFileNameSimple(file);

        modules = StringUtils.defaultString(modules, "default");
        String picturePath = "/upload/" + modules;

        Ftp ftp = null;
        try {
            ftp = new Ftp(ftpConfig.getHostName(), ftpConfig.getPort(), ftpConfig.getUserName(), ftpConfig.getPassword());
            ftp.setBackToPwd(true);
            ftp.cd("/");
            // 主要是主动模式还是被动
            ftp.setMode(FtpMode.Active);
            ftp.upload(picturePath, fileName, file.getInputStream());
        } finally {
            if (ftp != null) {
                ftp.close();
            }
        }
        String url = ftpConfig.getDomain() + "/" + picturePath + "/" + fileName;
        return url.replace("//", "/");
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        Ftp ftp = null;
        try {
            ftp = new Ftp(ftpConfig.getHostName(), ftpConfig.getPort(), ftpConfig.getUserName(), ftpConfig.getPassword());
            // 主要是主动模式还是被动
            ftp.setMode(FtpMode.Active);
            String storePath = getStorePath(fileUrl);
            return ftp.delFile(storePath);
        } finally {
            if (ftp != null) {
                try {
                    ftp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String objectsCapacityStr() {
        throw new CustomException("fpt-获取文件占用空间功能，敬请期待");
    }

    /**
     * 转换url，为原始的key
     *
     * @param filePath https://test53.ourslook.com/upload/default/20210717-e646d18a-e405-4d09-a62a-11e58b67b48d.jpeg
     * @return upload/default/20210717-e646d18a-e405-4d09-a62a-11e58b67b48d.jpeg
     */
    private String getStorePath(String filePath) {
        // 使用方式1
        String domain = ftpConfig.getDomain();
        return filePath.replace(domain, "");
    }
}
