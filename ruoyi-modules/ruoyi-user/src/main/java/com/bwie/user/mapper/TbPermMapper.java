package com.bwie.user.mapper;

import java.util.List;
import com.bwie.user.domain.TbPerm;

/**
 * 权限Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
public interface TbPermMapper 
{
    /**
     * 查询权限
     * 
     * @param permId 权限主键
     * @return 权限
     */
    public TbPerm selectTbPermByPermId(Long permId);

    /**
     * 查询权限列表
     * 
     * @param tbPerm 权限
     * @return 权限集合
     */
    public List<TbPerm> selectTbPermList(TbPerm tbPerm);

    /**
     * 新增权限
     * 
     * @param tbPerm 权限
     * @return 结果
     */
    public int insertTbPerm(TbPerm tbPerm);

    /**
     * 修改权限
     * 
     * @param tbPerm 权限
     * @return 结果
     */
    public int updateTbPerm(TbPerm tbPerm);

    /**
     * 删除权限
     * 
     * @param permId 权限主键
     * @return 结果
     */
    public int deleteTbPermByPermId(Long permId);

    /**
     * 批量删除权限
     * 
     * @param permIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbPermByPermIds(Long[] permIds);
}
