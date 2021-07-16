package com.ruoyi.file.service;

import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import com.ruoyi.file.config.FtpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * ftp目录用来上传文件；
 * ftp, 如：iis、linux ftp、vsftpd、FileZilla Server，需要自己搭建服务
 * @author dazer
 */
@Service
public class FtpFileServiceImpl implements ISysFileService {
    @Autowired
    private FtpConfig ftpConfig;
    public static final String ACCESS_PREFIX = "";

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        return this.uploadFile(file, null);
    }

    @Override
    public String uploadFile(MultipartFile file, String modules) throws Exception {
        String fileName = "upload/" + validateModule(file, modules);

        Ftp ftp = null;
        try {
            ftp = new Ftp(ftpConfig.getHostName(), ftpConfig.getPort(), ftpConfig.getUserName(), ftpConfig.getPassword());
            ftp.cd("");
            ftp.setMode(FtpMode.Passive);
            ftp.upload("", fileName, file.getInputStream());
        } finally {
            if (ftp != null) {
                ftp.close();
            }
        }
        return ftpConfig.getHostName() + "/" + fileName;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        Ftp ftp = null;
        try {
            ftp = new Ftp(ftpConfig.getHostName(), ftpConfig.getPort(), ftpConfig.getUserName(), ftpConfig.getPassword());
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
    public String listObject() {
        return null;
    }

    private String getStorePath(String filePath) {
        int groupStartPos = -1;
        if ((groupStartPos = filePath.indexOf(ACCESS_PREFIX) + ACCESS_PREFIX.length()) + 1 == 0) {
            groupStartPos = 0;
            //throw new RrException("解析文件路径错误,被解析路径url没有" + Constant.SERVIER_NAME_SUFFIX + ",当前解析路径为".concat(filePath));
        }
        // 获取group起始位置
        String groupAndPath = filePath.substring(groupStartPos);
        return groupAndPath + "";
    }
}
