package com.ruoyi.file.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Alan Scipio
 * created on 2024/2/19
 */
@Data
public class FileUploadResult implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 存储的文件名
     */
    private String savedFileName;

    /**
     * 存储的文件名（带年月日的相对父路径，例如/2024/02/19/xxx.png）
     */
    private String savedPathFileName;

    /**
     * 原始文件名
     */
    private String originalFilename;

    /**
     * 文件扩展名
     */
    private String extension;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 文件存储的绝对路径
     */
    private String savedPath;

}
