package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.vo.CompetitionOfTeamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionOfTeamMapper;
import com.ruoyi.system.domain.CompetitionOfTeam;
import com.ruoyi.system.service.ICompetitionOfTeamService;

/**
 * 赛会中-参赛队伍Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class CompetitionOfTeamServiceImpl implements ICompetitionOfTeamService 
{
    @Autowired
    private CompetitionOfTeamMapper competitionOfTeamMapper;

    /**
     * 查询赛会中-参赛队伍
     * 
     * @param id 赛会中-参赛队伍主键
     * @return 赛会中-参赛队伍
     */
    @Override
    public CompetitionOfTeam selectCompetitionOfTeamById(Long id)
    {
        return competitionOfTeamMapper.selectCompetitionOfTeamById(id);
    }

    /**
     * 查询赛会中-参赛队伍列表
     * 
     * @param competitionOfTeam 赛会中-参赛队伍
     * @return 赛会中-参赛队伍
     */
    @Override
    public List<CompetitionOfTeamVo> selectCompetitionOfTeamList(CompetitionOfTeam competitionOfTeam)
    {
        return competitionOfTeamMapper.selectCompetitionOfTeamList(competitionOfTeam);
    }

    /**
     * 新增赛会中-参赛队伍
     * 
     * @param competitionOfTeam 赛会中-参赛队伍
     * @return 结果
     */
    @Override
    public int insertCompetitionOfTeam(CompetitionOfTeam competitionOfTeam)
    {
        return competitionOfTeamMapper.insertCompetitionOfTeam(competitionOfTeam);
    }

    /**
     * 修改赛会中-参赛队伍
     * 
     * @param competitionOfTeam 赛会中-参赛队伍
     * @return 结果
     */
    @Override
    public int updateCompetitionOfTeam(CompetitionOfTeam competitionOfTeam)
    {
        return competitionOfTeamMapper.updateCompetitionOfTeam(competitionOfTeam);
    }

    @Override
    public int batchUpdateCompetitionOfTeam(List<CompetitionOfTeam> list) {
        for (int i = 0; i < list.size(); i++) {
            CompetitionOfTeam team = list.get(i);
            competitionOfTeamMapper.updateCompetitionOfTeam(team);
        }
        return 1;
    }

    /**
     * 批量删除赛会中-参赛队伍
     * 
     * @param ids 需要删除的赛会中-参赛队伍主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionOfTeamByIds(Long[] ids)
    {
        return competitionOfTeamMapper.deleteCompetitionOfTeamByIds(ids);
    }

    /**
     * 删除赛会中-参赛队伍信息
     * 
     * @param id 赛会中-参赛队伍主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionOfTeamById(Long id)
    {
        return competitionOfTeamMapper.deleteCompetitionOfTeamById(id);
    }
}
