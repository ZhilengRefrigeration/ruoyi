package com.server.xm.entity;

import com.ruoyi.common.core.web.domain.BaseEntity;


/**
 * 样式表(XmNodeStyle)实体类
 *
 * @author makejava
 * @since 2022-10-24 22:33:26
 */
public class XmNodeStyle extends BaseEntity {
    private static final long serialVersionUID = -43953448497497403L;

    private String id;
    /**
     * 内容
     */
    private String direction;
    /**
     * 所属节点ID
     */
    private String hyperLink;
    /**
     * 图标
     */
    private String icons;

    private String memo;
    /**
     * 样式
     */
    private String style;
    /**
     * 标签
     */
    private String tags;
    /**
     * 节点ID
     */
    private String nodeId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getHyperLink() {
        return hyperLink;
    }

    public void setHyperLink(String hyperLink) {
        this.hyperLink = hyperLink;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

}
