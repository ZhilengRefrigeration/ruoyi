package com.ruoyi.file.utils;

import com.ruoyi.common.core.exception.file.FileException;
import com.ruoyi.common.core.exception.file.FileNameLengthLimitExceededException;
import com.ruoyi.common.core.exception.file.FileSizeLimitExceededException;
import com.ruoyi.common.core.exception.file.InvalidExtensionException;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.file.FileTypeUtils;
import com.ruoyi.common.core.utils.file.MimeTypeUtils;
import com.ruoyi.common.core.utils.uuid.Seq;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * 文件上传工具类
 *
 * @author ruoyi
 */
public class FileUploadUtils {
    /**
     * 默认大小 100M
     */
    public static final long DEFAULT_MAX_SIZE = 100 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件上传结果
     */
    public static FileUploadResult upload(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (FileException fe) {
            throw new IOException(fe.getDefaultMessage(), fe);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir          相对应用的基目录
     * @param file             上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException       如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException                          比如读写文件出错时
     * @throws InvalidExtensionException            文件校验异常
     */
    public static FileUploadResult upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException, InvalidExtensionException {
        int fileNameLength = Objects.requireNonNull(file.getOriginalFilename()).length();
        if (fileNameLength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        FileUploadResult result = new FileUploadResult();

        assertAllowed(file, allowedExtension, result);

        String fileName = extractFilename(file, result);

        String absPath = getAbsoluteFile(baseDir, fileName).getAbsolutePath();
        file.transferTo(Paths.get(absPath));
        String savedPathFileName = getPathFileName(fileName);

        result.setSavedPathFileName(savedPathFileName);
        result.setSavedPath(absPath);
        return result;
    }

    /**
     * 编码文件名，最终结果举例： .../2024/02/19/20240219154651A001.jpg
     */
    public static String extractFilename(MultipartFile file, FileUploadResult result) {
        String fileId = Seq.getId(Seq.uploadSeqType);
        String savedFileName = StringUtils.format("{}.{}", fileId, FileTypeUtils.getExtension(file));
        if (result != null) {
            result.setFileId(fileId);
            result.setSavedFileName(savedFileName);
        }
        return StringUtils.format("{}/{}", DateUtils.datePath(), savedFileName);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static File getAbsoluteFile(String uploadDir, String fileName) {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.exists()) {
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
        }
        return desc.isAbsolute() ? desc : desc.getAbsoluteFile();
    }

    private static String getPathFileName(String fileName) {
        return "/" + fileName;
    }

    /**
     * 文件大小校验
     *
     * @param file   上传的文件
     * @param result 文件上传结果
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws InvalidExtensionException      文件校验异常
     */
    public static void assertAllowed(MultipartFile file, String[] allowedExtension, FileUploadResult result)
            throws FileSizeLimitExceededException, InvalidExtensionException {
        long size = file.getSize();
        if (size > DEFAULT_MAX_SIZE) {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }
        result.setFileSize(size);

        String fileName = file.getOriginalFilename();
        String extension = FileTypeUtils.getExtension(file);
        result.setExtension(extension);
        result.setOriginalFilename(fileName);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION) {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION) {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION) {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.VIDEO_EXTENSION) {
                throw new InvalidExtensionException.InvalidVideoExtensionException(allowedExtension, extension,
                        fileName);
            } else {
                throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension        上传文件类型
     * @param allowedExtension 允许上传文件类型
     * @return true/false
     */
    public static boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }
}