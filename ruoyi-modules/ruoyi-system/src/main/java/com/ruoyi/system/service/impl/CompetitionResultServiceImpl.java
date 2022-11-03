package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionResultMapper;
import com.ruoyi.system.domain.CompetitionResult;
import com.ruoyi.system.service.ICompetitionResultService;

/**
 * 赛会中-赛程结果记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class CompetitionResultServiceImpl implements ICompetitionResultService 
{
    @Autowired
    private CompetitionResultMapper competitionResultMapper;

    /**
     * 查询赛会中-赛程结果记录
     * 
     * @param id 赛会中-赛程结果记录主键
     * @return 赛会中-赛程结果记录
     */
    @Override
    public CompetitionResult selectCompetitionResultById(Long id)
    {
        return competitionResultMapper.selectCompetitionResultById(id);
    }

    /**
     * 查询赛会中-赛程结果记录列表
     * 
     * @param competitionResult 赛会中-赛程结果记录
     * @return 赛会中-赛程结果记录
     */
    @Override
    public List<CompetitionResult> selectCompetitionResultList(CompetitionResult competitionResult)
    {
        return competitionResultMapper.selectCompetitionResultList(competitionResult);
    }

    /**
     * 新增赛会中-赛程结果记录
     * 
     * @param competitionResult 赛会中-赛程结果记录
     * @return 结果
     */
    @Override
    public int insertCompetitionResult(CompetitionResult competitionResult)
    {
        return competitionResultMapper.insertCompetitionResult(competitionResult);
    }

    /**
     * 修改赛会中-赛程结果记录
     * 
     * @param competitionResult 赛会中-赛程结果记录
     * @return 结果
     */
    @Override
    public int updateCompetitionResult(CompetitionResult competitionResult)
    {
        return competitionResultMapper.updateCompetitionResult(competitionResult);
    }

    /**
     * 批量删除赛会中-赛程结果记录
     * 
     * @param ids 需要删除的赛会中-赛程结果记录主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionResultByIds(Long[] ids)
    {
        return competitionResultMapper.deleteCompetitionResultByIds(ids);
    }

    /**
     * 删除赛会中-赛程结果记录信息
     * 
     * @param id 赛会中-赛程结果记录主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionResultById(Long id)
    {
        return competitionResultMapper.deleteCompetitionResultById(id);
    }
}
