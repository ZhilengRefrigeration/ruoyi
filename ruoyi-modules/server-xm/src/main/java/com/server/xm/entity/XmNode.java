package com.server.xm.entity;

import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.core.utils.uuid.UUID;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.server.xm.entity.vo.XmTopVo;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.io.Serializable;

/**
 * Xmind列表(XmNode)实体类
 *
 * @author makejava
 * @since 2022-10-24 22:16:40
 */
public class XmNode extends BaseEntity {
    private static final long serialVersionUID = 814901162125497459L;

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
     * 父节点ID
     */
    private String parentId;
    /**
     * 一级节点ID
     */
    private String topId;
    /**
     * 是否删除，1已删除，0未删除
     */
    private String delFlag;
    public XmNode(){}
    public XmNode(XmTopVo xmTopVo,String level,String parentId,String topId){
        BeanUtils.copyProperties(xmTopVo,this);
//        if(null == xmTopVo.getId()){
//            this.setId(UUID.randomUUID().toString());
//        }
        this.setName(xmTopVo.getTopic());
        this.setNodeLevel(level);
        this.setParentId(parentId);
        this.setTopId(topId);
    }

    public String getId() {
        return this.id;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTopId() {
        return topId;
    }

    public void setTopId(String topId) {
        this.topId = topId;
    }

    public String getDelFlag() {
        return null != this.delFlag?this.delFlag : "0";
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}
