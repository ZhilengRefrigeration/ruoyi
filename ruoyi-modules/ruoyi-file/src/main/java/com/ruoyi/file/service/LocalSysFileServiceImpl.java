package com.ruoyi.file.service;

import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.file.constants.FileStorageType;
import com.ruoyi.file.domain.FileResult;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    public FileResult uploadFile(MultipartFile file) throws Exception {
        // 保存文件到本地
        FileUploadResult uploadResult = FileUploadUtils.upload(localFilePath, file);
        String savedPathFileName = uploadResult.getSavedPathFileName();
        String requestUrl = domain + localFilePrefix + savedPathFileName;
        // 保存文件记录
        SysFile record = buildRecord(uploadResult, requestUrl);
        sysFileMapper.insertSelective(record);
        // 返回访问地址
        return FileResult.success(requestUrl, uploadResult);
    }

    /**
     * 本地文件删除
     *
     * @param fileIds 文件id
     * @return 删除结果
     */
    @Transactional
    @Override
    public FileResult deleteFiles(String[] fileIds) {
        // 查询文件记录
        List<SysFile> fileList = selectFilesById(sysFileMapper, fileIds);
        // 删除文件
        List<String> warningList = new ArrayList<>();
        for (SysFile sysFile : fileList) {
            File file = new File(sysFile.getFilePath());
            if (file.exists()) {
                if (file.delete()) {
                    sysFileMapper.deleteByPrimaryKey(sysFile.getFileId());
                } else {
                    throw new ServiceException("Delete file failed: [" + sysFile.getFilePath() + "]", HttpStatus.ERROR);
                }
            } else {
                warningList.add(sysFile.getFilePath());
            }
        }
        // 组装返回结果
        FileResult result = FileResult.success();
        if (!warningList.isEmpty()) {
            result.setMessage("Files not exists: " + warningList);
        } else {
            result.setMessage("Delete file success");
        }
        result.setCount(fileList.size() - warningList.size());
        return result;
    }

    private SysFile buildRecord(FileUploadResult uploadResult, String requestUrl) {
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
