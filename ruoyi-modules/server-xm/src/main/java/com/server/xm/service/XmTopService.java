package com.server.xm.service;
import com.server.xm.entity.XmTop;
import com.server.xm.entity.vo.XmTopVo;

import java.util.List;

/**
 * Xmind列表(XmTop)表服务接口
 *
 * @author makejava
 * @since 2022-10-23 12:50:29
 */
public interface XmTopService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    XmTopVo queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<XmTop> queryAllByLimit(int offset, int limit);

    /**
     * 查询所有，不分页
     * @param xmTop
     * @return
     */
    List<XmTop> queryAll(XmTop xmTop);

    /**
     * 新增数据
     *
     * @param xmTop 实例对象
     * @return 实例对象
     */
    void insert(XmTopVo xmTopVo);

    /**
     * 修改数据
     *
     * @param xmTop 实例对象
     * @return 实例对象
     */
    XmTop update(XmTop xmTop);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
