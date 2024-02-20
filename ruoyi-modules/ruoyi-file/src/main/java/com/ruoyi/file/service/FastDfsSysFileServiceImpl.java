package com.ruoyi.file.service;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ruoyi.file.domain.FileSaveResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * FastDFS 文件存储
 *
 * @author ruoyi
 */
@Service
public class FastDfsSysFileServiceImpl implements ISysFileService {
    /**
     * 域名或本机访问地址
     */
    @Value("${fdfs.domain}")
    public String domain;

//    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * FastDfs文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     */
    @Override
    public FileSaveResult uploadFile(MultipartFile file) throws Exception {
//        InputStream inputStream = file.getInputStream();
//        StorePath storePath = storageClient.uploadFile(inputStream, file.getSize(),
//                FileTypeUtils.getExtension(file), null);
//        IoUtils.closeQuietly(inputStream);
//        return domain + "/" + storePath.getFullPath();
        return FileSaveResult.fail("Not implemented yet!");
    }
}
