package com.ruoyi.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.exception.CheckedException;
import com.ruoyi.system.domain.CompetitionSharePermissions;
import com.ruoyi.system.mapper.CompetitionSharePermissionsMapper;
import com.ruoyi.system.service.ICompetitionSharePermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 赛会-权限分享Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-20
 */
@Service
public class CompetitionSharePermissionsServiceImpl extends ServiceImpl<CompetitionSharePermissionsMapper, CompetitionSharePermissions> implements ICompetitionSharePermissionsService
{
    @Autowired
    private CompetitionSharePermissionsMapper competitionSharePermissionsMapper;

    /**
     * 查询赛会-权限分享
     * 
     * @param id 赛会-权限分享主键
     * @return 赛会-权限分享
     */
    @Override
    public CompetitionSharePermissions selectCompetitionSharePermissionsById(Long id)
    {
        return competitionSharePermissionsMapper.selectCompetitionSharePermissionsById(id);
    }

    /**
     * 查询赛会-权限分享列表
     * 
     * @param competitionSharePermissions 赛会-权限分享
     * @return 赛会-权限分享
     */
    @Override
    public List<CompetitionSharePermissions> selectCompetitionSharePermissionsList(CompetitionSharePermissions competitionSharePermissions)
    {
        return competitionSharePermissionsMapper.selectCompetitionSharePermissionsList(competitionSharePermissions);
    }

    /**
     * 新增赛会-权限分享
     * 
     * @param competitionSharePermissions 赛会-权限分享
     * @return 结果
     */
    @Override
    public int insertCompetitionSharePermissions(CompetitionSharePermissions competitionSharePermissions)
    {
        return competitionSharePermissionsMapper.insertCompetitionSharePermissions(competitionSharePermissions);
    }

    /**
     * 修改赛会-权限分享
     * 
     * @param competitionSharePermissions 赛会-权限分享
     * @return 结果
     */
    @Override
    public int updateCompetitionSharePermissions(CompetitionSharePermissions competitionSharePermissions)
    {
        return competitionSharePermissionsMapper.updateCompetitionSharePermissions(competitionSharePermissions);
    }

    /**
     * 批量删除赛会-权限分享
     * 
     * @param ids 需要删除的赛会-权限分享主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionSharePermissionsByIds(Long[] ids)
    {
        return competitionSharePermissionsMapper.deleteCompetitionSharePermissionsByIds(ids);
    }

    /**
     * 删除赛会-权限分享信息
     * 
     * @param id 赛会-权限分享主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionSharePermissionsById(Long id)
    {
        return competitionSharePermissionsMapper.deleteCompetitionSharePermissionsById(id);
    }

    @Transactional
    @Override
    public Boolean shareCompetitionPermissions(CompetitionSharePermissions vo) {
        if(ObjectUtil.isNull(vo.getCompetitionId())){
            throw new CheckedException("competitionId不能为空");
        }if(ObjectUtil.isNull(vo.getUserId())){
            throw new CheckedException("userId不能为空");
        }if(ObjectUtil.isNull(vo.getUserTel())){
            throw new CheckedException("userTel不能为空");
        }
        CompetitionSharePermissions permissions= new CompetitionSharePermissions();
        permissions.setIsDeleted(0);
        permissions.setCompetitionId(vo.getCompetitionId());
        permissions.setUserTel(vo.getUserTel());
        permissions.setUserId(vo.getUserId());
        List<CompetitionSharePermissions> list = competitionSharePermissionsMapper.selectCompetitionSharePermissionsList(permissions);
        if(list.size()>0) {
            throw new CheckedException("手机号授权重复");
        }else {
            competitionSharePermissionsMapper.insertCompetitionSharePermissions(vo);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean delShareCompetitionPermissions(Long[] ids) {
        competitionSharePermissionsMapper.deleteCompetitionSharePermissionsByIds(ids);
        return Boolean.TRUE;
    }

    @Override
    public List<CompetitionSharePermissions> getList(CompetitionSharePermissions competitionSharePermissions) {
        return competitionSharePermissionsMapper.getList(competitionSharePermissions);
    }
}
