package com.xjs.business.english.api.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiejs
 * @desc  文案实体类
 * @create 2021-12-27
 */
public class CopyWriting implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /** 文案内容 */
    private String content;

    /** 文案来源 */
    private String source;

    private String createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
