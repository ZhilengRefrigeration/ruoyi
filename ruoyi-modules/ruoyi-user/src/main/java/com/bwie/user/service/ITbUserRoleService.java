package com.bwie.user.service;

import java.util.List;
import com.bwie.user.domain.TbUserRole;

/**
 * 用户类型Service接口
 * 
 * @author xs
 * @date 2023-01-15
 */
public interface ITbUserRoleService 
{
    /**
     * 查询用户类型
     * 
     * @param userRoleId 用户类型主键
     * @return 用户类型
     */
    public TbUserRole selectTbUserRoleByUserRoleId(Long userRoleId);

    /**
     * 查询用户类型列表
     * 
     * @param tbUserRole 用户类型
     * @return 用户类型集合
     */
    public List<TbUserRole> selectTbUserRoleList(TbUserRole tbUserRole);

    /**
     * 新增用户类型
     * 
     * @param tbUserRole 用户类型
     * @return 结果
     */
    public int insertTbUserRole(TbUserRole tbUserRole);

    /**
     * 修改用户类型
     * 
     * @param tbUserRole 用户类型
     * @return 结果
     */
    public int updateTbUserRole(TbUserRole tbUserRole);

    /**
     * 批量删除用户类型
     * 
     * @param userRoleIds 需要删除的用户类型主键集合
     * @return 结果
     */
    public int deleteTbUserRoleByUserRoleIds(Long[] userRoleIds);

    /**
     * 删除用户类型信息
     * 
     * @param userRoleId 用户类型主键
     * @return 结果
     */
    public int deleteTbUserRoleByUserRoleId(Long userRoleId);
}
