package com.server.xm.mapper;


import com.server.xm.entity.XmTop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Xmind列表(XmTop)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-23 12:50:09
 */
public interface XmTopMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    XmTop queryById(String id);

    /**
     * 根据父节点查询二级节点
     * @param pid 一级节点ID
     * @return
     */
    List<XmTop> queryByPid(String pid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<XmTop> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param xmTop 实例对象
     * @return 对象列表
     */
    List<XmTop> queryAll(XmTop xmTop);

    /**
     * 新增数据
     *
     * @param xmTop 实例对象
     * @return 影响行数
     */
    int insert(XmTop xmTop);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<XmTop> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<XmTop> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<XmTop> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<XmTop> entities);

    /**
     * 修改数据
     *
     * @param xmTop 实例对象
     * @return 影响行数
     */
    int update(XmTop xmTop);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

