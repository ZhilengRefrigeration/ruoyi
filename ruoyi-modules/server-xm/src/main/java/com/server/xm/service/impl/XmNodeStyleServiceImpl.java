package com.server.xm.service.impl;

import com.server.xm.entity.XmNodeStyle;
import com.server.xm.mapper.XmNodeStyleMapper;
import com.server.xm.service.XmNodeStyleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 样式表(XmNodeStyle)表服务实现类
 *
 * @author makejava
 * @since 2022-10-24 22:33:28
 */
@Service("xmNodeStyleService")
public class XmNodeStyleServiceImpl implements XmNodeStyleService {
    @Resource
    private XmNodeStyleMapper xmNodeStyleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public XmNodeStyle queryById(String id) {
        return this.xmNodeStyleMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<XmNodeStyle> queryAllByLimit(int offset, int limit) {
        return this.xmNodeStyleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param xmNodeStyle 实例对象
     * @return 实例对象
     */
    @Override
    public XmNodeStyle insert(XmNodeStyle xmNodeStyle) {
        this.xmNodeStyleMapper.insert(xmNodeStyle);
        return xmNodeStyle;
    }

    /**
     * 修改数据
     *
     * @param xmNodeStyle 实例对象
     * @return 实例对象
     */
    @Override
    public XmNodeStyle update(XmNodeStyle xmNodeStyle) {
        this.xmNodeStyleMapper.update(xmNodeStyle);
        return this.queryById(xmNodeStyle.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.xmNodeStyleMapper.deleteById(id) > 0;
    }
}
