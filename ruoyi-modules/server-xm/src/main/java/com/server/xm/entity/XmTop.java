package com.server.xm.entity;

import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.server.xm.entity.vo.XmTopVo;

import java.util.Date;
import java.io.Serializable;
import java.util.UUID;

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
    private String type;
    /**
     * 节点等级
     */
    private String nodeLevel;
    private String parentId;
    /**
     * 是否删除，1已删除，0未删除
     */
    private String delFlag;

    public XmTop(){}
    public XmTop(XmTopVo xmTopVo,String level,String parentId){
        BeanUtils.copyProperties(xmTopVo,this);
//        if(null == xmTopVo.getId()){
//            this.setId(UUID.randomUUID().toString());
//        }
        this.setName(xmTopVo.getTopic());
        this.setNodeLevel(level);
        this.setParentId(parentId);
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
        return type;
    }

    public void setTypeId(String typeId) {
        this.type = typeId;
    }

    public String getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(String nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public String getDelFlag() {
        return null != this.delFlag ? this.delFlag : "0";
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
