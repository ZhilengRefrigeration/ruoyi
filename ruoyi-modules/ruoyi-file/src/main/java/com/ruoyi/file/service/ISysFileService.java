package com.ruoyi.file.service;

import com.ruoyi.file.domain.FileSaveResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口
 *
 * @author ruoyi
 */
public interface ISysFileService {
    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 保存结果
     */
    FileSaveResult uploadFile(MultipartFile file) throws Exception;

}
