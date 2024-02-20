package com.ruoyi.file.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Alan Scipio
 * created on 2024/2/19
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileSaveForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 域名或本机访问地址
     */
    public String domain;

    /**
     * 文件存储基础目录
     */
    private String localBaseDir;

    /**
     * 资源映射路径 前缀
     */
    public String localFilePrefix;

    /**
     * 上传的文件
     */
    private MultipartFile file;

}
