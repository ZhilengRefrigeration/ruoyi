package com.server.xm.entity;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.core.utils.uuid.UUID;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.server.xm.entity.vo.XmTopVo;


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

    public XmNodeStyle(){}
    public XmNodeStyle(XmTopVo xmTopVo){
        this.setId(UUID.randomUUID().toString());
        this.setDirection(xmTopVo.getDirection().toString());
        this.setIcons(JSON.toJSONString(xmTopVo.getIcons()));
        this.setTags(JSON.toJSONString(xmTopVo.getTags()));
        this.setMemo(xmTopVo.getMemo());
        this.setHyperLink(xmTopVo.getHyperLink());
        this.setStyle(JSON.toJSONString(xmTopVo.getStyle()));
        this.setNodeId(xmTopVo.getId());
    }



    public String getId() {
        return this.id;
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
