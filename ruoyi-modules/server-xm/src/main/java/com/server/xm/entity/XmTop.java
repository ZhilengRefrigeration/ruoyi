package com.server.xm.entity;

import com.ruoyi.common.core.web.domain.BaseEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * Xmind列表(XmTop)实体类
 *
 * @author makejava
 * @since 2022-10-23 11:52:10
 */
public class XmTop extends BaseEntity {
    private static final long serialVersionUID = 780186867205672228L;

    private String id;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 所属分类
     */
    private String typeId;
    /**
     * 节点等级
     */
    private String nodeLevel;
    /**
     * 是否删除，1已删除，0未删除
     */
    private String delFlag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(String nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}
