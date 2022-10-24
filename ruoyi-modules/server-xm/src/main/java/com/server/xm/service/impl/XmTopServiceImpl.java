package com.server.xm.service.impl;


import com.server.xm.entity.XmNodeStyle;
import com.server.xm.entity.XmTop;
import com.server.xm.entity.vo.XmTopVo;
import com.server.xm.mapper.XmNodeStyleMapper;
import com.server.xm.mapper.XmTopMapper;
import com.server.xm.service.XmTopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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
     * @param xmTop 实例对象
     * @return 实例对象
     */
    @Override
    public XmTop insert(XmTop xmTop) {
        this.xmTopMapper.insert(xmTop);
        return xmTop;
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
