package com.bwie.user.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bwie.user.mapper.TbRolePermMapper;
import com.bwie.user.domain.TbRolePerm;
import com.bwie.user.service.ITbRolePermService;

/**
 * 角色权限Service业务层处理
 * 
 * @author xs
 * @date 2023-01-15
 */
@Service
public class TbRolePermServiceImpl implements ITbRolePermService 
{
    @Autowired
    private TbRolePermMapper tbRolePermMapper;

    /**
     * 查询角色权限
     * 
     * @param rolePermId 角色权限主键
     * @return 角色权限
     */
    @Override
    public TbRolePerm selectTbRolePermByRolePermId(Long rolePermId)
    {
        return tbRolePermMapper.selectTbRolePermByRolePermId(rolePermId);
    }

    /**
     * 查询角色权限列表
     * 
     * @param tbRolePerm 角色权限
     * @return 角色权限
     */
    @Override
    public List<TbRolePerm> selectTbRolePermList(TbRolePerm tbRolePerm)
    {
        return tbRolePermMapper.selectTbRolePermList(tbRolePerm);
    }

    /**
     * 新增角色权限
     * 
     * @param tbRolePerm 角色权限
     * @return 结果
     */
    @Override
    public int insertTbRolePerm(TbRolePerm tbRolePerm)
    {
        tbRolePerm.setCreateTime(DateUtils.getNowDate());
        return tbRolePermMapper.insertTbRolePerm(tbRolePerm);
    }

    /**
     * 修改角色权限
     * 
     * @param tbRolePerm 角色权限
     * @return 结果
     */
    @Override
    public int updateTbRolePerm(TbRolePerm tbRolePerm)
    {
        tbRolePerm.setUpdateTime(DateUtils.getNowDate());
        return tbRolePermMapper.updateTbRolePerm(tbRolePerm);
    }

    /**
     * 批量删除角色权限
     * 
     * @param rolePermIds 需要删除的角色权限主键
     * @return 结果
     */
    @Override
    public int deleteTbRolePermByRolePermIds(Long[] rolePermIds)
    {
        return tbRolePermMapper.deleteTbRolePermByRolePermIds(rolePermIds);
    }

    /**
     * 删除角色权限信息
     * 
     * @param rolePermId 角色权限主键
     * @return 结果
     */
    @Override
    public int deleteTbRolePermByRolePermId(Long rolePermId)
    {
        return tbRolePermMapper.deleteTbRolePermByRolePermId(rolePermId);
    }
}
