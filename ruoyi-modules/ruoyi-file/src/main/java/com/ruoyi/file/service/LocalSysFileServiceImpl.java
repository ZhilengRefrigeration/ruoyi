package com.ruoyi.file.service;

import com.ruoyi.file.constants.FileStorageType;
import com.ruoyi.file.domain.FileSaveResult;
import com.ruoyi.file.domain.FileUploadResult;
import com.ruoyi.file.domain.SysFile;
import com.ruoyi.file.mapper.SysFileMapper;
import com.ruoyi.file.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 本地文件存储
 *
 * @author ruoyi
 */
@Primary
@Service
public class LocalSysFileServiceImpl implements ISysFileService {
    /**
     * 资源映射路径 前缀
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    /**
     * 域名或本机访问地址
     */
    @Value("${file.domain}")
    public String domain;

    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.path}")
    private String localFilePath;

    @Autowired
    private SysFileMapper sysFileMapper;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     */
    @Transactional
    @Override
    public FileSaveResult uploadFile(MultipartFile file) throws Exception {
        // 保存文件到本地
        FileUploadResult uploadResult = FileUploadUtils.upload(localFilePath, file);
        String savedPathFileName = uploadResult.getSavedPathFileName();
        String requestUrl = domain + localFilePrefix + savedPathFileName;
        // 保存文件记录
        SysFile record = getSysFile(uploadResult, requestUrl);
        sysFileMapper.insertSelective(record);
        // 返回访问地址
        return FileSaveResult.success(requestUrl, uploadResult);
    }

    private SysFile getSysFile(FileUploadResult uploadResult, String requestUrl) {
        SysFile record = new SysFile();
        record.setFileId(uploadResult.getFileId()); // 文件ID
        record.setSavedName(uploadResult.getSavedFileName()); // 保存的文件名
        record.setOriginalName(uploadResult.getOriginalFilename()); // 原文件名
        record.setFilePath(uploadResult.getSavedPath()); // 本地保存的文件路径
        record.setExtension(uploadResult.getExtension()); // 文件扩展名
        record.setStorageType(FileStorageType.LOCAL.name()); // 存储类型：本地文件存储
        record.setRequestUrl(requestUrl); // 获取文件的URL
        record.setFileSize(uploadResult.getFileSize()); // 文件大小(Byte)
        return record;
    }
}
