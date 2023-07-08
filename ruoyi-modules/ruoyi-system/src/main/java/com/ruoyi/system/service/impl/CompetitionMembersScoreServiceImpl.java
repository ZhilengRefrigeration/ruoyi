package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.vo.PersonalCareerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionMembersScoreMapper;
import com.ruoyi.system.domain.CompetitionMembersScore;
import com.ruoyi.system.service.ICompetitionMembersScoreService;

/**
 * 赛会中-赛程-人员得分Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class CompetitionMembersScoreServiceImpl implements ICompetitionMembersScoreService 
{
    @Autowired
    private CompetitionMembersScoreMapper competitionMembersScoreMapper;

    /**
     * 查询赛会中-赛程-人员得分
     * 
     * @param id 赛会中-赛程-人员得分主键
     * @return 赛会中-赛程-人员得分
     */
    @Override
    public CompetitionMembersScore selectCompetitionMembersScoreById(Long id)
    {
        return competitionMembersScoreMapper.selectCompetitionMembersScoreById(id);
    }

    /**
     * 查询赛会中-赛程-人员得分列表
     * 
     * @param competitionMembersScore 赛会中-赛程-人员得分
     * @return 赛会中-赛程-人员得分
     */
    @Override
    public List<CompetitionMembersScore> selectCompetitionMembersScoreList(CompetitionMembersScore competitionMembersScore)
    {
        return competitionMembersScoreMapper.selectCompetitionMembersScoreList(competitionMembersScore);
    }

    /**
     * 新增赛会中-赛程-人员得分
     * 
     * @param competitionMembersScore 赛会中-赛程-人员得分
     * @return 结果
     */
    @Override
    public int insertCompetitionMembersScore(CompetitionMembersScore competitionMembersScore)
    {
        return competitionMembersScoreMapper.insertCompetitionMembersScore(competitionMembersScore);
    }

    /**
     * 修改赛会中-赛程-人员得分
     * 
     * @param competitionMembersScore 赛会中-赛程-人员得分
     * @return 结果
     */
    @Override
    public int updateCompetitionMembersScore(CompetitionMembersScore competitionMembersScore)
    {
        return competitionMembersScoreMapper.updateCompetitionMembersScore(competitionMembersScore);
    }

    /**
     * 批量删除赛会中-赛程-人员得分
     * 
     * @param ids 需要删除的赛会中-赛程-人员得分主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionMembersScoreByIds(Long[] ids)
    {
        return competitionMembersScoreMapper.deleteCompetitionMembersScoreByIds(ids);
    }

    /**
     * 删除赛会中-赛程-人员得分信息
     * 
     * @param id 赛会中-赛程-人员得分主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionMembersScoreById(Long id)
    {
        return competitionMembersScoreMapper.deleteCompetitionMembersScoreById(id);
    }

    @Override
    public PersonalCareerVo getUserScoreByUserId(Long userId) {
        return competitionMembersScoreMapper.getUserScoreByUserId(userId);
    }

    @Override
    public List<CompetitionMembersScore> getHonorList(Long competitionId, Long userId) {
        return competitionMembersScoreMapper.getHonorList(competitionId,userId);
    }
}
