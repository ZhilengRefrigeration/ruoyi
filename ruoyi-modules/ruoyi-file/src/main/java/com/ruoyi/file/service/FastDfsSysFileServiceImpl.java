package com.ruoyi.file.service;

import com.github.tobato.fastdfs.domain.conn.PooledConnectionFactory;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

/**
 * FastDFS 文件存储
 * @author ruoyi
 */
@Service
public class FastDfsSysFileServiceImpl implements ISysFileService
{
    private final Logger logger = LoggerFactory.getLogger(FastDfsSysFileServiceImpl.class);
    /**
     * 域名或本机访问地址
     * FastDFS配置 其他参数见：{@link PooledConnectionFactory}
     */
    @Value("${fdfs.domain}")
    public String domain;

    @Autowired
    private FastFileStorageClient storageClient;

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
       return this.uploadFile(file);
    }

    @Override
    public String uploadFile(MultipartFile file, String modules) throws Exception {
        // fastdsf 这里的 modules 没用
        validateModule(file, modules);

        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);

        /// fileUrl = "http://127.0.0.1:22122/" + storePath.getFullPath();
        return domain + "/" + storePath.getFullPath();
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
    public String listObject() {
        return null;
    }
}
