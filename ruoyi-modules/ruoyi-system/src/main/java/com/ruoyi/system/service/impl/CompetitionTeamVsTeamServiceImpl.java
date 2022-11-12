package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.vo.CompetitionTeamVsTeamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionTeamVsTeamMapper;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import com.ruoyi.system.service.ICompetitionTeamVsTeamService;

/**
 * 赛会中-球队VS球队关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class CompetitionTeamVsTeamServiceImpl implements ICompetitionTeamVsTeamService 
{
    @Autowired
    private CompetitionTeamVsTeamMapper competitionTeamVsTeamMapper;

    /**
     * 查询赛会中-球队VS球队关系
     * 
     * @param id 赛会中-球队VS球队关系主键
     * @return 赛会中-球队VS球队关系
     */
    @Override
    public CompetitionTeamVsTeamVo selectCompetitionTeamVsTeamById(Long id)
    {
        return competitionTeamVsTeamMapper.selectCompetitionTeamVsTeamById(id);
    }

    /**
     * 查询赛会中-球队VS球队关系列表
     * 
     * @param competitionTeamVsTeam 赛会中-球队VS球队关系
     * @return 赛会中-球队VS球队关系
     */
    @Override
    public List<CompetitionTeamVsTeamVo> selectCompetitionTeamVsTeamList(CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        return competitionTeamVsTeamMapper.selectCompetitionTeamVsTeamList(competitionTeamVsTeam);
    }

    /**
     * 新增赛会中-球队VS球队关系
     * 
     * @param competitionTeamVsTeam 赛会中-球队VS球队关系
     * @return 结果
     */
    @Override
    public int insertCompetitionTeamVsTeam(CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        return competitionTeamVsTeamMapper.insertCompetitionTeamVsTeam(competitionTeamVsTeam);
    }

    /**
     * 修改赛会中-球队VS球队关系
     * 
     * @param competitionTeamVsTeam 赛会中-球队VS球队关系
     * @return 结果
     */
    @Override
    public int updateCompetitionTeamVsTeam(CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        return competitionTeamVsTeamMapper.updateCompetitionTeamVsTeam(competitionTeamVsTeam);
    }

    /**
     * 批量删除赛会中-球队VS球队关系
     * 
     * @param ids 需要删除的赛会中-球队VS球队关系主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionTeamVsTeamByIds(Long[] ids)
    {
        return competitionTeamVsTeamMapper.deleteCompetitionTeamVsTeamByIds(ids);
    }

    /**
     * 删除赛会中-球队VS球队关系信息
     * 
     * @param id 赛会中-球队VS球队关系主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionTeamVsTeamById(Long id)
    {
        return competitionTeamVsTeamMapper.deleteCompetitionTeamVsTeamById(id);
    }
}
