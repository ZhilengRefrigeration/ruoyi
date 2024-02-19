package com.ruoyi.file.domain;

import com.ruoyi.file.utils.FileUploadResult;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Alan Scipio
 * created on 2024/2/19
 */
@Data
public class FileSaveRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String requestUrl;

    private FileUploadResult uploadResult;

}
