package com.ruoyi.file.domain;

import com.ruoyi.common.core.utils.StringUtils;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Alan Scipio
 * created on 2024/2/19
 */
@Data
public class FileResult implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private boolean success;

    private String message;

    private int count;

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

    public static FileResult success(String requestUrl, FileUploadResult uploadResult) {
        FileResult result = new FileResult();
        result.setSuccess(true);
        result.setRequestUrl(requestUrl);
        result.setUploadResult(uploadResult);
        return result;
    }

    public static FileResult success(String requestUrl) {
        return success(requestUrl, null);
    }

    public static FileResult success() {
        return success(null, null);
    }

    public static FileResult fail(String message) {
        FileResult result = new FileResult();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    public String getMessage(String defaultMessage) {
        return StringUtils.isBlank(message) ? defaultMessage : message;
    }
}
