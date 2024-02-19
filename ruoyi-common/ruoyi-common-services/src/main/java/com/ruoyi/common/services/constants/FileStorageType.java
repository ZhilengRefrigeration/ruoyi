package com.ruoyi.common.services.constants;

import com.ruoyi.common.core.constant.IEnum;
import lombok.Getter;

/**
 * @author Alan Scipio
 * created on 2024/2/19
 */
@Getter
public enum FileStorageType implements IEnum {

    LOCAL(1, "本地文件存储"),

    FAST_DFS(2, "FastDFS文件存储"),

    MINIO(3, "MinIO文件存储"),

    ;

    private final int code;
    private final String name;

    FileStorageType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
