package com.ruoyi.file.service;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.ruoyi.common.core.exception.CustomException;
import com.ruoyi.file.config.FastDfsConfig;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

import java.util.HashSet;
import java.util.Set;

/**
 * FastDFS 文件存储
 * @author ruoyi
 * @see FastDfsConfig
 */
@Service()
public class FastDfsServiceImpl implements IDfsService
{
    private final Logger logger = LoggerFactory.getLogger(FastDfsServiceImpl.class);

    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private FastDfsConfig fastDfsConfig;

    /**
     * FastDfs文件上传接口
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
        // fastdsf 这里的 modules 没用
        validateModule(file, modules);

        Set<MetaData> metaDataSet = new HashSet<>(1);
        metaDataSet.add(new MetaData("groupName", "group1"));

        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), metaDataSet);

        /// fileUrl = "http://127.0.0.1:22122/" + storePath.getFullPath();
        return fastDfsConfig.getDomain() + "/" + storePath.getFullPath();
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return false;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            return true;
        } catch (FdfsUnsupportStorePathException e) {
            logger.warn(e.getMessage());
        }
        return false;
    }

    @Override
    public String objectsCapacityStr() {
        throw new CustomException("fastdfs-获取文件占用空间功能，敬请期待");
    }

    @Override
    public String presignedUrl(String fileUrl) {
        return fileUrl;
    }
}
