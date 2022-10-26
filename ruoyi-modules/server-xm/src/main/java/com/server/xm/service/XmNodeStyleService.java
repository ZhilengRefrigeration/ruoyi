package com.server.xm.service;

import com.server.xm.entity.XmNodeStyle;

import java.util.List;

/**
 * 样式表(XmNodeStyle)表服务接口
 *
 * @author makejava
 * @since 2022-10-24 22:33:28
 */
public interface XmNodeStyleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    XmNodeStyle queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<XmNodeStyle> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param xmNodeStyle 实例对象
     * @return 实例对象
     */
    XmNodeStyle insert(XmNodeStyle xmNodeStyle);

    /**
     * 修改数据
     *
     * @param xmNodeStyle 实例对象
     * @return 实例对象
     */
    XmNodeStyle update(XmNodeStyle xmNodeStyle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
