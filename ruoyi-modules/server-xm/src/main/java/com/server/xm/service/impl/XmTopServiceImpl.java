package com.server.xm.service.impl;


import com.ruoyi.common.core.web.domain.AjaxResult;
import com.server.xm.entity.XmNode;
import com.server.xm.entity.XmNodeStyle;
import com.server.xm.entity.XmTop;
import com.server.xm.entity.vo.XmTopVo;
import com.server.xm.mapper.XmNodeMapper;
import com.server.xm.mapper.XmNodeStyleMapper;
import com.server.xm.mapper.XmTopMapper;
import com.server.xm.service.XmTopService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Xmind列表(XmTop)表服务实现类
 *
 * @author makejava
 * @since 2022-10-23 12:50:29
 */
@Service("xmTopService")
public class XmTopServiceImpl implements XmTopService {
    @Resource
    private XmTopMapper xmTopMapper;
    @Resource
    private XmNodeStyleMapper styleMapper;
    @Resource
    private XmNodeMapper nodeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public XmTopVo queryById(String id) {
        //查询出节点与父节点数据
        XmTop top =  this.xmTopMapper.queryById(id);
        //查询出二级ID
        List<XmTop> secList = this.xmTopMapper.queryByPid(id);
        List<String> nids = secList.stream().map(e -> e.getId()).collect(Collectors.toList());
        nids.add(top.getId());
        List<XmNodeStyle> styleList = styleMapper.queryByNid(nids);
        Map<String,List<XmNodeStyle>> styleMap = styleList.stream().collect(Collectors.groupingBy(e -> e.getNodeId()));
        //组装父节点
        XmTopVo data = new XmTopVo(top,styleMap.get(top.getId()).get(0));
        //子节点
        List<XmTopVo> children = secList.stream().map(e -> new XmTopVo(e,styleMap.get(e.getId()).get(0))).collect(Collectors.toList());
        data.setChildren(children);
        return data;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<XmTop> queryAllByLimit(int offset, int limit) {
        return this.xmTopMapper.queryAllByLimit(offset, limit);
    }

    @Override
    public List<XmTop> queryAll(XmTop xmTop) {
        return this.xmTopMapper.queryAll(xmTop);
    }

    /**
     * 新增数据
     *
     * @param xmTopVo 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public void insert(XmTopVo xmTopVo) {
        //查询头两个节点,存入top
        List<XmTop> topList = new ArrayList<>();
        XmTop topEntity = new XmTop(xmTopVo,"1","-1");
        topList.add(topEntity);
        List<XmTopVo> secList = xmTopVo.getChildren();
        //保存前两节点style
        List<XmNodeStyle> styleList = new ArrayList<>();
        styleList.add(new XmNodeStyle(xmTopVo));
        if(!CollectionUtils.isEmpty(secList)){
            List<XmTop> secTopList = secList.stream().map(e -> new XmTop(e,"2",topEntity.getId())).collect(Collectors.toList());
            topList.addAll(secTopList);
            //保存节点
            xmTopMapper.insertBatch(topList);
            List<XmNodeStyle> secStyleList = secList.stream().map(e -> new XmNodeStyle(e)).collect(Collectors.toList());
            styleList.addAll(secStyleList);
            //保存syle
            styleMapper.insertBatch(styleList);
            //递归下属节点
            List<XmNode> nodes = new ArrayList<>();
            List<XmNodeStyle> styles = new ArrayList<>();
            secList.stream().forEach(e -> {
                if(!CollectionUtils.isEmpty(e.getChildren())){
                    dealInsertNode(e.getChildren(),nodes,styles,3,e.getId(),topEntity.getId());
                }
            });
            //保存
            nodeMapper.insertBatch(nodes);
            styleMapper.insertBatch(styles);

        }

    }
    private void dealInsertNode(List<XmTopVo> xmTopVoList, List<XmNode> nodeList,List<XmNodeStyle> styleList,Integer level,String parentId,String topId){
        final Integer oldLevel = level;
        List<XmNode> nodes = xmTopVoList.stream().map(e -> new XmNode(e,oldLevel.toString(),parentId,topId)).collect(Collectors.toList());
        nodeList.addAll(nodes);
        List<XmNodeStyle> styles = xmTopVoList.stream().map(e -> new XmNodeStyle(e)).collect(Collectors.toList());
        styleList.addAll(styles);
        Map<String,List<XmTopVo>> map = xmTopVoList.stream().filter(e -> null != e.getChildren()).collect(Collectors.toMap(e ->e.getId(),e->e.getChildren(),(k1,k2) ->k1));
        final Integer newLevel = ++level;
        map.forEach((k,v) -> {
            if(!CollectionUtils.isEmpty(v)){
                dealInsertNode(v,nodeList,styleList,newLevel,k,topId);
            }
        });
    }
    /**
     * 修改数据
     *
     * @param xmTop 实例对象
     * @return 实例对象
     */
    @Override
    public XmTop update(XmTop xmTop) {
        this.xmTopMapper.update(xmTop);
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.xmTopMapper.deleteById(id) > 0;
    }
}
