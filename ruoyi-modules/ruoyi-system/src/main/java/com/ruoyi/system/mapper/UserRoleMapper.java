package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.UserRole;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface UserRoleMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UserRole selectUserRoleById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userRole 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserRole> selectUserRoleList(UserRole userRole);

    /**
     * 新增【请填写功能名称】
     * 
     * @param userRole 【请填写功能名称】
     * @return 结果
     */
    public int insertUserRole(UserRole userRole);

    /**
     * 修改【请填写功能名称】
     * 
     * @param userRole 【请填写功能名称】
     * @return 结果
     */
    public int updateUserRole(UserRole userRole);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserRoleById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserRoleByIds(Long[] ids);

    UserRole selectByCode(String roleCode);
}
