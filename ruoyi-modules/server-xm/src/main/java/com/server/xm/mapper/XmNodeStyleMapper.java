package com.server.xm.mapper;

import com.server.xm.entity.XmNodeStyle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 样式表(XmNodeStyle)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-24 22:33:26
 */
public interface XmNodeStyleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    XmNodeStyle queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<XmNodeStyle> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 根据NodeId查询样式
     * @param nids
     * @return
     */
    List<XmNodeStyle> queryByNid(@Param("nids") List<String> nids);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param xmNodeStyle 实例对象
     * @return 对象列表
     */
    List<XmNodeStyle> queryAll(XmNodeStyle xmNodeStyle);

    /**
     * 新增数据
     *
     * @param xmNodeStyle 实例对象
     * @return 影响行数
     */
    int insert(XmNodeStyle xmNodeStyle);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<XmNodeStyle> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<XmNodeStyle> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<XmNodeStyle> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<XmNodeStyle> entities);

    /**
     * 修改数据
     *
     * @param xmNodeStyle 实例对象
     * @return 影响行数
     */
    int update(XmNodeStyle xmNodeStyle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

