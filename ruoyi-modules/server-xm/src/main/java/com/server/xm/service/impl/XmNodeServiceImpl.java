package com.server.xm.service.impl;

import com.server.xm.entity.XmNode;
import com.server.xm.mapper.XmNodeMapper;
import com.server.xm.service.XmNodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Xmind列表(XmNode)表服务实现类
 *
 * @author makejava
 * @since 2022-10-24 22:16:42
 */
@Service("xmNodeService")
public class XmNodeServiceImpl implements XmNodeService {
    @Resource
    private XmNodeMapper xmNodeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public XmNode queryById(String id) {
        return this.xmNodeMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<XmNode> queryAllByLimit(int offset, int limit) {
        return this.xmNodeMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param xmNode 实例对象
     * @return 实例对象
     */
    @Override
    public XmNode insert(XmNode xmNode) {
        this.xmNodeMapper.insert(xmNode);
        return xmNode;
    }

    /**
     * 修改数据
     *
     * @param xmNode 实例对象
     * @return 实例对象
     */
    @Override
    public XmNode update(XmNode xmNode) {
        this.xmNodeMapper.update(xmNode);
        return this.queryById(xmNode.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.xmNodeMapper.deleteById(id) > 0;
    }
}
