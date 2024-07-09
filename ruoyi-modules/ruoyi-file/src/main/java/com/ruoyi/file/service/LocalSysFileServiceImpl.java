package com.ruoyi.file.service;

import com.ruoyi.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.file.utils.FileUploadUtils;

import java.net.InetAddress;

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
     * 本机文件上传服务端口
     */
    @Value("${file.port}")
    public String port;
    /**
     * 本机文件上传服务端口
     */
    @Value("${file.tls}")
    public String tls;
    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.path}")
    private String localFilePath;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String name = FileUploadUtils.upload(localFilePath, file);
        String url=String.format("%s://%s:%s/%s%s",tls,domain==null||StringUtils.isEmpty(domain)? InetAddress.getLocalHost().getHostAddress() :domain,port,localFilePrefix,name);
//     为什么要采用此修改：若依的本地上传服务需要结合nginx 反代域名使用  如果不使用域名反代 则有可能在多开文件服务的情况文件被负载均衡到其他服务器情况,
//       直接使用localaddress适用于内网部署多文件上传服务 现在考虑当置空file.path时使用该命令 防止prefix忘记配置 / 手动添加
        return url;
    }
}
