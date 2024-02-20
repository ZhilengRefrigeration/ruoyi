package com.ruoyi.system.api.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 文件信息
 *
 * @author ruoyi
 */
@Data
public class SysFileInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;

}
