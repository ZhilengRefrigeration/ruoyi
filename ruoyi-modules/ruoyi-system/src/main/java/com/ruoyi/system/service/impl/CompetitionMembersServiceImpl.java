package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionMembersMapper;
import com.ruoyi.system.domain.CompetitionMembers;
import com.ruoyi.system.service.ICompetitionMembersService;

/**
 * 比赛参与人员Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class CompetitionMembersServiceImpl implements ICompetitionMembersService 
{
    @Autowired
    private CompetitionMembersMapper competitionMembersMapper;

    /**
     * 查询比赛参与人员
     * 
     * @param id 比赛参与人员主键
     * @return 比赛参与人员
     */
    @Override
    public CompetitionMembers selectCompetitionMembersById(Long id)
    {
        return competitionMembersMapper.selectCompetitionMembersById(id);
    }

    /**
     * 查询比赛参与人员列表
     * 
     * @param competitionMembers 比赛参与人员
     * @return 比赛参与人员
     */
    @Override
    public List<CompetitionMembers> selectCompetitionMembersList(CompetitionMembers competitionMembers)
    {
        return competitionMembersMapper.selectCompetitionMembersList(competitionMembers);
    }

    /**
     * 新增比赛参与人员
     * 
     * @param competitionMembers 比赛参与人员
     * @return 结果
     */
    @Override
    public int insertCompetitionMembers(CompetitionMembers competitionMembers)
    {
        return competitionMembersMapper.insertCompetitionMembers(competitionMembers);
    }

    /**
     * 修改比赛参与人员
     * 
     * @param competitionMembers 比赛参与人员
     * @return 结果
     */
    @Override
    public int updateCompetitionMembers(CompetitionMembers competitionMembers)
    {
        return competitionMembersMapper.updateCompetitionMembers(competitionMembers);
    }

    /**
     * 批量删除比赛参与人员
     * 
     * @param ids 需要删除的比赛参与人员主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionMembersByIds(Long[] ids)
    {
        return competitionMembersMapper.deleteCompetitionMembersByIds(ids);
    }

    /**
     * 删除比赛参与人员信息
     * 
     * @param id 比赛参与人员主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionMembersById(Long id)
    {
        return competitionMembersMapper.deleteCompetitionMembersById(id);
    }
}
