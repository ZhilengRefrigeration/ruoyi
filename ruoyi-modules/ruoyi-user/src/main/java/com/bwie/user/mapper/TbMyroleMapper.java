package com.bwie.user.mapper;

import java.util.List;
import com.bwie.user.domain.TbMyrole;

/**
 * 角色Mapper接口
 * 
 * @author xs
 * @date 2023-01-15
 */
public interface TbMyroleMapper 
{
    /**
     * 查询角色
     * 
     * @param roleId 角色主键
     * @return 角色
     */
    public TbMyrole selectTbMyroleByRoleId(Long roleId);

    /**
     * 查询角色列表
     * 
     * @param tbMyrole 角色
     * @return 角色集合
     */
    public List<TbMyrole> selectTbMyroleList(TbMyrole tbMyrole);

    /**
     * 新增角色
     * 
     * @param tbMyrole 角色
     * @return 结果
     */
    public int insertTbMyrole(TbMyrole tbMyrole);

    /**
     * 修改角色
     * 
     * @param tbMyrole 角色
     * @return 结果
     */
    public int updateTbMyrole(TbMyrole tbMyrole);

    /**
     * 删除角色
     * 
     * @param roleId 角色主键
     * @return 结果
     */
    public int deleteTbMyroleByRoleId(Long roleId);

    /**
     * 批量删除角色
     * 
     * @param roleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbMyroleByRoleIds(Long[] roleIds);
}
