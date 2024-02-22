package com.ruoyi.file.service;

import com.ruoyi.file.config.MinioConfig;
import com.ruoyi.file.domain.FileResult;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO 未完成，还需要改造：保存文件记录
 * Minio 文件存储
 *
 * @author ruoyi
 */
@Service
public class MinioSysFileServiceImpl implements ISysFileService {
    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioClient client;

    /**
     * Minio文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     */
    @Override
    public FileResult uploadFile(MultipartFile file) throws Exception {
//        String fileName = FileUploadUtils.extractFilename(file, null);
//        InputStream inputStream = file.getInputStream();
//        PutObjectArgs args = PutObjectArgs.builder()
//                .bucket(minioConfig.getBucketName())
//                .object(fileName)
//                .stream(inputStream, file.getSize(), -1)
//                .contentType(file.getContentType())
//                .build();
//        client.putObject(args);
//        IoUtils.closeQuietly(inputStream);
//        String requestUrl = minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
//        return FileResult.success(requestUrl, null);
        return FileResult.fail("Not implemented yet!");
    }

    @Override
    public FileResult deleteFiles(String[] fileIds) throws Exception {
        return FileResult.fail("Not implemented yet!");
    }
}
