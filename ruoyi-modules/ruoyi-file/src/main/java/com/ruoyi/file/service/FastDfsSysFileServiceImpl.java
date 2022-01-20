package com.ruoyi.file.service;

import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.exception.file.FileException;
import com.ruoyi.file.config.FastDfsConfig;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.ProtoCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

/**
 * FastDFS 文件存储
 *
 * @author ruoyi
 */
@Service
public class FastDfsSysFileServiceImpl implements ISysFileService
{
    @Autowired
    private FastDfsConfig fastDfsConfig;

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
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);
        // 形如： http://47.99.175.191:8888/group1/M00/00/00/rBzzjWD-ec2ADLS9AAJiu1rRenk51.jpeg
        return fastDfsConfig.getDomain() + "/" + storePath.getFullPath();
    }

    @Override
    public String presignedUrl(String fileUrl) {
        if (StringUtils.isBlank(fastDfsConfig.getTokenSecretKey())) {
            throw new FileException(HttpStatus.ERROR + "", new String[] {"防盗链生成token的密钥为空，请检查：tokenSecretKey"});
        }
        String signKey = "?token=";
        if (fileUrl.contains(signKey)) {
            return fileUrl;
        }
        String tokenSecretKey = fastDfsConfig.getTokenSecretKey();

        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        String keyPath = storePath.getPath();
        //时间戳 单位为秒
        int ts = (int) (System.currentTimeMillis() / 1000);

        String token;
        try {
            token = ProtoCommon.getToken(keyPath, ts, tokenSecretKey);
        } catch (Exception e) {
            throw new FileException(HttpStatus.ERROR + "", new String[] {"FastDFS获取token异常"});
        }
        // 形如： http://47.99.175.191:8888/group1/M00/00/00/rBzzjWD-ec2ADLS9AAJiu1rRenk51.jpeg
        return fastDfsConfig.getDomain() + "/" + storePath.getFullPath() + "?token=" + token + "&ts=" + ts;
    }
}
