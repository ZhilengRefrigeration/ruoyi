package com.ruoyi.file.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Alan Scipio
 * created on 2024/2/19
 */
@Data
public class FileSaveResult implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private boolean success;

    private String message;

    /**
     * 文件请求地址
     */
    private String requestUrl;

    /**
     * 文件上传结果
     */
    private FileUploadResult uploadResult;

    public String getFileId() {
        return uploadResult == null ? "" : uploadResult.getFileId();
    }

    public static FileSaveResult success(String requestUrl, FileUploadResult uploadResult) {
        FileSaveResult result = new FileSaveResult();
        result.setSuccess(true);
        result.setRequestUrl(requestUrl);
        result.setUploadResult(uploadResult);
        return result;
    }

    public static FileSaveResult fail(String message) {
        FileSaveResult result = new FileSaveResult();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

}
