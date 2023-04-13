package com.bwie.user.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bwie.user.mapper.TbPermMapper;
import com.bwie.user.domain.TbPerm;
import com.bwie.user.service.ITbPermService;

/**
 * 权限Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
@Service
public class TbPermServiceImpl implements ITbPermService 
{
    @Autowired
    private TbPermMapper tbPermMapper;

    /**
     * 查询权限
     * 
     * @param permId 权限主键
     * @return 权限
     */
    @Override
    public TbPerm selectTbPermByPermId(Long permId)
    {
        return tbPermMapper.selectTbPermByPermId(permId);
    }

    /**
     * 查询权限列表
     * 
     * @param tbPerm 权限
     * @return 权限
     */
    @Override
    public List<TbPerm> selectTbPermList(TbPerm tbPerm)
    {
        return tbPermMapper.selectTbPermList(tbPerm);
    }

    /**
     * 新增权限
     * 
     * @param tbPerm 权限
     * @return 结果
     */
    @Override
    public int insertTbPerm(TbPerm tbPerm)
    {
        tbPerm.setCreateTime(DateUtils.getNowDate());
        return tbPermMapper.insertTbPerm(tbPerm);
    }

    /**
     * 修改权限
     * 
     * @param tbPerm 权限
     * @return 结果
     */
    @Override
    public int updateTbPerm(TbPerm tbPerm)
    {
        tbPerm.setUpdateTime(DateUtils.getNowDate());
        return tbPermMapper.updateTbPerm(tbPerm);
    }

    /**
     * 批量删除权限
     * 
     * @param permIds 需要删除的权限主键
     * @return 结果
     */
    @Override
    public int deleteTbPermByPermIds(Long[] permIds)
    {
        return tbPermMapper.deleteTbPermByPermIds(permIds);
    }

    /**
     * 删除权限信息
     * 
     * @param permId 权限主键
     * @return 结果
     */
    @Override
    public int deleteTbPermByPermId(Long permId)
    {
        return tbPermMapper.deleteTbPermByPermId(permId);
    }
}
