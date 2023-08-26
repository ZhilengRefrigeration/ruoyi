package com.ruoyi.system.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.ruoyi.system.domain.UserRole;
import com.ruoyi.system.mapper.UserRoleMapper;
import com.ruoyi.system.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService
{
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public UserRole selectUserRoleById(Long id)
    {
        return userRoleMapper.selectUserRoleById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userRole 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UserRole> selectUserRoleList(UserRole userRole)
    {
        return userRoleMapper.selectUserRoleList(userRole);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param userRole 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertUserRole(UserRole userRole)
    {
        return userRoleMapper.insertUserRole(userRole);
    }

    @Override
    public List<UserRole> selectRoleByUserId(Long userid) {
        return userRoleMapper.selectRoleByUserId(userid);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param userRole 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUserRole(UserRole userRole)
    {
        return userRoleMapper.updateUserRole(userRole);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserRoleByIds(Long[] ids)
    {
        return userRoleMapper.deleteUserRoleByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserRoleById(Long id)
    {
        return userRoleMapper.deleteUserRoleById(id);
    }
}
