package com.server.xm.entity.vo;

import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.shaded.com.google.gson.JsonArray;
import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.server.xm.entity.XmNodeStyle;
import com.server.xm.entity.XmTop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class XmTopVo implements Serializable {
    private static final long serialVersionUID = 814901162125497459L;
    private String id;
    private String topic;
    private Integer direction;
    private String hyperLink;
    private String memo;
    private String nodeLevel;
    private List<String> icons;
    private Map<String,Object> style;
    private List<String> tags;
    private List<XmTopVo> children;

    public XmTopVo() {
        if(null == this.id){
            this.setId(UUID.randomUUID().toString());
        }
    }
    public XmTopVo(XmTop top, XmNodeStyle style){
        //基本信息同步
        BeanUtils.copyProperties(top,this);
        this.setTopic(top.getName());
        this.setIcons(JSON.parseObject(style.getIcons(), ArrayList.class));
        this.setStyle(JSON.parseObject(style.getStyle(),Map.class));
        this.setTags(JSON.parseObject(style.getTags(),ArrayList.class));
        this.setDirection(Integer.parseInt(style.getDirection()));
        this.setHyperLink(style.getHyperLink());
        this.setMemo(style.getMemo());
    }




    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getHyperLink() {
        return hyperLink;
    }

    public void setHyperLink(String hyperLink) {
        this.hyperLink = hyperLink;
    }

    public List<String> getIcons() {
        return icons;
    }

    public void setIcons(List<String> icons) {
        this.icons = icons;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Map<String, Object> getStyle() {
        return style;
    }

    public void setStyle(Map<String, Object> style) {
        this.style = style;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<XmTopVo> getChildren() {
        return children;
    }

    public void setChildren(List<XmTopVo> children) {
        this.children = children;
    }

    public String getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(String nodeLevel) {
        this.nodeLevel = nodeLevel;
    }
}
