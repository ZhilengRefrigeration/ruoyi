package com.ruoyi.common.core.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serial;

/**
 * 扩展的基础实体类
 *
 * @author Alan Scipio
 * created on 2024/2/2
 */
public class ExtBaseEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑删除标志：未删除
     */
    public static final int NOT_DELETE = 0;

    /**
     * 逻辑删除标志：已删除
     */
    public static final int DELETED = 1;

    /**
     * 逻辑删除标志。true：已删除；false：未删除
     */
    private Integer deleteFlag;

    /**
     * 更新次数（乐观锁）
     */
    private Integer updateCount;

    /**
     * 是否已逻辑删除
     *
     * @return true：已删除；false：未删除
     */
    @JsonIgnore
    public boolean isLogicDeleted() {
        return deleteFlag != null && deleteFlag == DELETED;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(Integer updateCount) {
        this.updateCount = updateCount;
    }
}
