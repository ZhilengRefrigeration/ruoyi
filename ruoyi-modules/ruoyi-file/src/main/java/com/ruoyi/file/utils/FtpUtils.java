package com.ruoyi.file.utils;

import cn.hutool.extra.ftp.Ftp;
import com.ruoyi.file.config.FtpProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * ftp工具类
 * @author xjs
 * @since 2022-01-24 16:49:40
 */
@Component
public class FtpUtils {

    private Ftp ftp = null;

    private static final Logger logger = LoggerFactory.getLogger(FtpUtils.class);
    public static String LOCAL_CHARSET = "GBK";

    @Autowired
    private FtpProperties ftpProperties;

    public Ftp instance() {
        if (ftp != null) {
            ftp.reconnectIfTimeout();
        } else {
            String ftpServerIp = ftpProperties.getServerIp();
            String ftpServerAccount = ftpProperties.getUser();
            String ftpServerPassword = ftpProperties.getPassword();
            String ftpServerPort = ftpProperties.getPort();
            synchronized (this) {
                if (StringUtils.isBlank(ftpServerPassword) || StringUtils.isBlank(ftpServerAccount)) {
                    ftp = new  Ftp(ftpServerIp, Integer.parseInt(ftpServerPort));
                } else {
                    ftp = new Ftp(ftpServerIp, Integer.parseInt(ftpServerPort), ftpServerAccount, ftpServerPassword);
                }
                ftp.setBackToPwd(true);
                logger.info("login ftp server with {}:{}@{}:{}", ftpServerIp, Integer.parseInt(ftpServerPort), ftpServerAccount, ftpServerPassword);
                setClientCharacter();
            }
        }
        return ftp;
    }



    private void setClientCharacter() {
        FTPClient ftpClient = ftp.getClient();
        ftpClient.setControlKeepAliveTimeout(60);
        try {
            int command = ftpClient.sendCommand("OPTS UTF8", "ON");
            if (FTPReply.isPositiveCompletion(command)) {
                LOCAL_CHARSET = "UTF-8";
            }
            ftpClient.setControlEncoding(LOCAL_CHARSET);
        } catch (Exception exception) {
            throw new RuntimeException("获取ftp服务编码格式异常：", exception.getCause());
        }
    }

    public boolean existFile(String filePath) {
        return instance().existFile(filePath);
    }

    public boolean delFile(String filePath) {
        return instance().delFile(filePath);
    }

    public boolean delDir(String dirPath) {
        return instance().delDir(dirPath);
    }

    public boolean mkDir(String dirPath) {
        return instance().mkdir(dirPath);
    }

    public List<FTPFile> lsFiles(String dirPath) {
        FTPFile[] ftpFiles = instance().lsFiles(dirPath);
        return Arrays.asList(ftpFiles);
    }

    public boolean uploadFile(String path, String fileName, File file) {
        return instance().upload(path, fileName, file);
    }

    public boolean uploadFile(String path, String fileName, InputStream inputStream) {
        return instance().upload(path, fileName, inputStream);
    }

    public void download(String path, String fileName, OutputStream outputStream) {
        instance().download(path, fileName, outputStream);
    }

    public boolean close() {
        try {
            if (ftp != null) {
                ftp.close();
            }
            return true;
        } catch (Exception exception) {
            logger.error("error close ftp: " + exception.getCause());
            return false;
        }
    }
}
