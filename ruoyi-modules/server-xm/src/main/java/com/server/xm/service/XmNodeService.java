package com.server.xm.service;

import com.server.xm.entity.XmNode;
import java.util.List;

/**
 * Xmind列表(XmNode)表服务接口
 *
 * @author makejava
 * @since 2022-10-24 22:16:42
 */
public interface XmNodeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    XmNode queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<XmNode> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param xmNode 实例对象
     * @return 实例对象
     */
    XmNode insert(XmNode xmNode);

    /**
     * 修改数据
     *
     * @param xmNode 实例对象
     * @return 实例对象
     */
    XmNode update(XmNode xmNode);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
