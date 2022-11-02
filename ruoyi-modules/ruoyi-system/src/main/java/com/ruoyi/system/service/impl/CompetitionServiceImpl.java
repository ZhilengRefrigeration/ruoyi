package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionMapper;
import com.ruoyi.system.domain.Competition;
import com.ruoyi.system.service.ICompetitionService;

/**
 * 比赛信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class CompetitionServiceImpl implements ICompetitionService 
{
    @Autowired
    private CompetitionMapper competitionMapper;

    /**
     * 查询比赛信息
     * 
     * @param id 比赛信息主键
     * @return 比赛信息
     */
    @Override
    public Competition selectCompetitionById(Long id)
    {
        return competitionMapper.selectCompetitionById(id);
    }

    /**
     * 查询比赛信息列表
     * 
     * @param competition 比赛信息
     * @return 比赛信息
     */
    @Override
    public List<Competition> selectCompetitionList(Competition competition)
    {
        return competitionMapper.selectCompetitionList(competition);
    }

    /**
     * 新增比赛信息
     * 
     * @param competition 比赛信息
     * @return 结果
     */
    @Override
    public int insertCompetition(Competition competition)
    {
        return competitionMapper.insertCompetition(competition);
    }

    /**
     * 修改比赛信息
     * 
     * @param competition 比赛信息
     * @return 结果
     */
    @Override
    public int updateCompetition(Competition competition)
    {
        return competitionMapper.updateCompetition(competition);
    }

    /**
     * 批量删除比赛信息
     * 
     * @param ids 需要删除的比赛信息主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionByIds(Long[] ids)
    {
        return competitionMapper.deleteCompetitionByIds(ids);
    }

    /**
     * 删除比赛信息信息
     * 
     * @param id 比赛信息主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionById(Long id)
    {
        return competitionMapper.deleteCompetitionById(id);
    }
}
