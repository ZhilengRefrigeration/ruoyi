package com.bwie.user.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bwie.user.mapper.TbUserRoleMapper;
import com.bwie.user.domain.TbUserRole;
import com.bwie.user.service.ITbUserRoleService;

/**
 * 用户类型Service业务层处理
 * 
 * @author xs
 * @date 2023-01-15
 */
@Service
public class TbUserRoleServiceImpl implements ITbUserRoleService 
{
    @Autowired
    private TbUserRoleMapper tbUserRoleMapper;

    /**
     * 查询用户类型
     * 
     * @param userRoleId 用户类型主键
     * @return 用户类型
     */
    @Override
    public TbUserRole selectTbUserRoleByUserRoleId(Long userRoleId)
    {
        return tbUserRoleMapper.selectTbUserRoleByUserRoleId(userRoleId);
    }

    /**
     * 查询用户类型列表
     * 
     * @param tbUserRole 用户类型
     * @return 用户类型
     */
    @Override
    public List<TbUserRole> selectTbUserRoleList(TbUserRole tbUserRole)
    {
        return tbUserRoleMapper.selectTbUserRoleList(tbUserRole);
    }

    /**
     * 新增用户类型
     * 
     * @param tbUserRole 用户类型
     * @return 结果
     */
    @Override
    public int insertTbUserRole(TbUserRole tbUserRole)
    {
        tbUserRole.setCreateTime(DateUtils.getNowDate());
        return tbUserRoleMapper.insertTbUserRole(tbUserRole);
    }

    /**
     * 修改用户类型
     * 
     * @param tbUserRole 用户类型
     * @return 结果
     */
    @Override
    public int updateTbUserRole(TbUserRole tbUserRole)
    {
        tbUserRole.setUpdateTime(DateUtils.getNowDate());
        return tbUserRoleMapper.updateTbUserRole(tbUserRole);
    }

    /**
     * 批量删除用户类型
     * 
     * @param userRoleIds 需要删除的用户类型主键
     * @return 结果
     */
    @Override
    public int deleteTbUserRoleByUserRoleIds(Long[] userRoleIds)
    {
        return tbUserRoleMapper.deleteTbUserRoleByUserRoleIds(userRoleIds);
    }

    /**
     * 删除用户类型信息
     * 
     * @param userRoleId 用户类型主键
     * @return 结果
     */
    @Override
    public int deleteTbUserRoleByUserRoleId(Long userRoleId)
    {
        return tbUserRoleMapper.deleteTbUserRoleByUserRoleId(userRoleId);
    }
}
