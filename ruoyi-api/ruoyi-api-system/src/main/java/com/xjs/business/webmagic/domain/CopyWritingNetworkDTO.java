package com.xjs.business.webmagic.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文案网数据实体DTO
 * @author xiejs
 * @since 2022-02-16
 */
@Data
public class CopyWritingNetworkDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /** 文案标签 */
    private String type;

    /** 文案主题 */
    private String theme;

    /** 文案内容 */
    private String content;

    /** 创建时间 */
    private Date createTime;

}
