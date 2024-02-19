package com.ruoyi.file.utils;

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

    private String fileId;

    private String savedFileName;

    private String savedPathFileName;

    private String originalFilename;

    private String extension;

    private Long fileSize;

    private String savedPath;

}
