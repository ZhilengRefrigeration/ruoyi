package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionTeamGroupMapper;
import com.ruoyi.system.domain.CompetitionTeamGroup;
import com.ruoyi.system.service.ICompetitionTeamGroupService;

/**
 * 赛会中-分组Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class CompetitionTeamGroupServiceImpl implements ICompetitionTeamGroupService 
{
    @Autowired
    private CompetitionTeamGroupMapper competitionTeamGroupMapper;

    /**
     * 查询赛会中-分组
     * 
     * @param id 赛会中-分组主键
     * @return 赛会中-分组
     */
    @Override
    public CompetitionTeamGroup selectCompetitionTeamGroupById(Long id)
    {
        return competitionTeamGroupMapper.selectCompetitionTeamGroupById(id);
    }

    /**
     * 查询赛会中-分组列表
     * 
     * @param competitionTeamGroup 赛会中-分组
     * @return 赛会中-分组
     */
    @Override
    public List<CompetitionTeamGroup> selectCompetitionTeamGroupList(CompetitionTeamGroup competitionTeamGroup)
    {
        return competitionTeamGroupMapper.selectCompetitionTeamGroupList(competitionTeamGroup);
    }

    /**
     * 新增赛会中-分组
     * 
     * @param competitionTeamGroup 赛会中-分组
     * @return 结果
     */
    @Override
    public int insertCompetitionTeamGroup(CompetitionTeamGroup competitionTeamGroup)
    {
        return competitionTeamGroupMapper.insertCompetitionTeamGroup(competitionTeamGroup);
    }

    /**
     * 修改赛会中-分组
     * 
     * @param competitionTeamGroup 赛会中-分组
     * @return 结果
     */
    @Override
    public int updateCompetitionTeamGroup(CompetitionTeamGroup competitionTeamGroup)
    {
        return competitionTeamGroupMapper.updateCompetitionTeamGroup(competitionTeamGroup);
    }

    /**
     * 批量删除赛会中-分组
     * 
     * @param ids 需要删除的赛会中-分组主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionTeamGroupByIds(Long[] ids)
    {
        return competitionTeamGroupMapper.deleteCompetitionTeamGroupByIds(ids);
    }

    /**
     * 删除赛会中-分组信息
     * 
     * @param id 赛会中-分组主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionTeamGroupById(Long id)
    {
        return competitionTeamGroupMapper.deleteCompetitionTeamGroupById(id);
    }
}
