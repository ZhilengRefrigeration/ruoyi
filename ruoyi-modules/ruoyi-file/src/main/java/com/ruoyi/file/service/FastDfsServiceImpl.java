package com.ruoyi.file.service;

import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.ruoyi.common.core.exception.CustomException;
import com.ruoyi.file.config.FastDfsConfig;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.ProtoCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;


/**
 * FastDFS 文件存储
 * @author ruoyi
 * @see FastDfsConfig
 */
//@Primary
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

        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);

        // 形如： http://47.99.175.191:8888/group1/M00/00/00/rBzzjWD-ec2ADLS9AAJiu1rRenk51.jpeg
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

    /**
     * FastDFS防盗链 https://www.cnblogs.com/xiaolinstudy/p/9341779.html
     * @param fileUrl 文件访问地址,全路径或者不是全路径都可以,eg: http://47.99.175.191:8888/group1/M00/00/00/rBzzjWD_eaqAFbHyAAJiu1rRenk96.jpeg
     *                过期时间，需要在 fastdf的/etc/fdfs/http.con 配置，无法通过代码直接配置；
     * @return 返回 带有防盗链token的url; eg: http://47.99.175.191:8888/group1/M00/00/00/rBzzjWD_eaqAFbHyAAJiu1rRenk96.jpeg?token=db86f940a963f6a6c10483c55c060a93&ts=1627355677
     */
    @Override
    public String presignedUrl(String fileUrl) {
        if (StringUtils.isBlank(fastDfsConfig.getTokenSecretKey())) {
            throw new CustomException("防盗链生成token的密钥为空，请检查：tokenSecretKey");
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
            throw new CustomException("FastDFS获取token异常");
        }
        // 形如： http://47.99.175.191:8888/group1/M00/00/00/rBzzjWD-ec2ADLS9AAJiu1rRenk51.jpeg
        return fastDfsConfig.getDomain() + "/" + storePath.getFullPath() + "?token=" + token + "&ts=" + ts;
    }
}
