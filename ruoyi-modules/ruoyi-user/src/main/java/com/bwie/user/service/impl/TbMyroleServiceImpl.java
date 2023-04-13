package com.bwie.user.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bwie.user.mapper.TbMyroleMapper;
import com.bwie.user.domain.TbMyrole;
import com.bwie.user.service.ITbMyroleService;

/**
 * 角色Service业务层处理
 * 
 * @author xs
 * @date 2023-01-15
 */
@Service
public class TbMyroleServiceImpl implements ITbMyroleService 
{
    @Autowired
    private TbMyroleMapper tbMyroleMapper;

    /**
     * 查询角色
     * 
     * @param roleId 角色主键
     * @return 角色
     */
    @Override
    public TbMyrole selectTbMyroleByRoleId(Long roleId)
    {
        return tbMyroleMapper.selectTbMyroleByRoleId(roleId);
    }

    /**
     * 查询角色列表
     * 
     * @param tbMyrole 角色
     * @return 角色
     */
    @Override
    public List<TbMyrole> selectTbMyroleList(TbMyrole tbMyrole)
    {
        return tbMyroleMapper.selectTbMyroleList(tbMyrole);
    }

    /**
     * 新增角色
     * 
     * @param tbMyrole 角色
     * @return 结果
     */
    @Override
    public int insertTbMyrole(TbMyrole tbMyrole)
    {
        tbMyrole.setCreateTime(DateUtils.getNowDate());
        return tbMyroleMapper.insertTbMyrole(tbMyrole);
    }

    /**
     * 修改角色
     * 
     * @param tbMyrole 角色
     * @return 结果
     */
    @Override
    public int updateTbMyrole(TbMyrole tbMyrole)
    {
        tbMyrole.setUpdateTime(DateUtils.getNowDate());
        return tbMyroleMapper.updateTbMyrole(tbMyrole);
    }

    /**
     * 批量删除角色
     * 
     * @param roleIds 需要删除的角色主键
     * @return 结果
     */
    @Override
    public int deleteTbMyroleByRoleIds(Long[] roleIds)
    {
        return tbMyroleMapper.deleteTbMyroleByRoleIds(roleIds);
    }

    /**
     * 删除角色信息
     * 
     * @param roleId 角色主键
     * @return 结果
     */
    @Override
    public int deleteTbMyroleByRoleId(Long roleId)
    {
        return tbMyroleMapper.deleteTbMyroleByRoleId(roleId);
    }
}
