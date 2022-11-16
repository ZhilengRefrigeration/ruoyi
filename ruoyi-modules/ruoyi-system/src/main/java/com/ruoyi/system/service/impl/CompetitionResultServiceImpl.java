package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.vo.CompetitionResultVo;
import com.ruoyi.system.domain.vo.CompetitionVsRecordVo;
import com.ruoyi.system.mapper.CompetitionTeamVsTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionResultMapper;
import com.ruoyi.system.domain.CompetitionResult;
import com.ruoyi.system.service.ICompetitionResultService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 赛会中-赛程结果记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class CompetitionResultServiceImpl implements ICompetitionResultService 
{
    @Resource
    private CompetitionResultMapper competitionResultMapper;
    @Resource
    private CompetitionTeamVsTeamMapper competitionTeamVsTeamMapper;

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

    @Override
    public int batchUpdateCompetitionResult(List<CompetitionResult> list) {
        for (CompetitionResult result:list){
            if(result.getId()==null){
                competitionResultMapper.insertCompetitionResult(result);
            }else {
                competitionResultMapper.updateCompetitionResult(result);
            }
        }
        return 1;
    }

    @Transactional
    @Override
    public int editData(CompetitionVsRecordVo obj) {
        //编辑比赛总分数据
        competitionTeamVsTeamMapper.updateCompetitionTeamVsTeam(obj.getTeamVsTeamVo());
        if(obj.getMainTeam().getId()==null) {
            CompetitionResultVo team = obj.getMainTeam();
            team.setCompetitionId(obj.getTeamVsTeamVo().getCompetitionId());
            team.setCompetitionVsId(obj.getTeamVsTeamVo().getId());
            team.setTeamId(obj.getTeamVsTeamVo().getMainTeamId());
            team.setTeamName(obj.getTeamVsTeamVo().getMainTeamName());
            competitionResultMapper.insertCompetitionResult(team);
        }else {
            competitionResultMapper.updateCompetitionResult(obj.getMainTeam());
        }
        if(obj.getGuestTeam().getId()==null) {
            CompetitionResultVo team = obj.getGuestTeam();
            team.setCompetitionId(obj.getTeamVsTeamVo().getCompetitionId());
            team.setCompetitionVsId(obj.getTeamVsTeamVo().getId());
            team.setTeamId(obj.getTeamVsTeamVo().getGuestTeamId());
            team.setTeamName(obj.getTeamVsTeamVo().getGuestTeamName());
            competitionResultMapper.insertCompetitionResult(team);
        }else {
            competitionResultMapper.updateCompetitionResult(obj.getGuestTeam());
        }
        return 1;
    }
}
