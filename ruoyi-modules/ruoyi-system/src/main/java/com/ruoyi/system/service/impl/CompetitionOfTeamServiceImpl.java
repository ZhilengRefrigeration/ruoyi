package com.ruoyi.system.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.CompetitionOfTeam;
import com.ruoyi.system.domain.vo.CompetitionOfTeamVo;
import com.ruoyi.system.mapper.CompetitionOfTeamMapper;
import com.ruoyi.system.service.ICompetitionOfTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 赛会中-参赛队伍Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class CompetitionOfTeamServiceImpl extends ServiceImpl<CompetitionOfTeamMapper, CompetitionOfTeam> implements ICompetitionOfTeamService
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

    @Override
    public int removeTeamGroup(Long[] ids) {
        return competitionOfTeamMapper.removeTeamGroup(ids);
    }

    @Override
    public int intoTeamGroup(String competitionGroup, List<Long> ids) {
        return competitionOfTeamMapper.intoTeamGroup(competitionGroup,ids);
    }

    @Override
    public List<CompetitionOfTeamVo> getJoinCompetitionTeam(CompetitionOfTeam entity) {
        return competitionOfTeamMapper.getJoinCompetitionTeam(entity);
    }

    @Override
    public List<CompetitionOfTeamVo> findCompetitionTeamGroupList(CompetitionOfTeamVo entity) {
        return competitionOfTeamMapper.findCompetitionTeamGroupList(entity);
    }

    @Override
    public CompetitionOfTeam selectOneByTeamName(String teamName) {
        return competitionOfTeamMapper.selectOneByTeamName(teamName);
    }

    @Override
    public Boolean edit(CompetitionOfTeam entity) {
        LoginUser user = SecurityUtils.getLoginUser();
        entity.setModifiedBy(String.valueOf(user.getUserid()));
        entity.setLastUpdatedTime(new Date());
        competitionOfTeamMapper.updateCompetitionOfTeam(entity);
        return true;
    }

    @Override
    public List<CompetitionOfTeamVo> getJoinCompetitionGroupTeam(CompetitionOfTeam ofTeam) {
        return competitionOfTeamMapper.getJoinCompetitionGroupTeam(ofTeam);
    }

    @Override
    public List<CompetitionOfTeamVo> getMyJoinCompetitionTeam(CompetitionOfTeamVo entity) {
        LoginUser user = SecurityUtils.getLoginUser();
        entity.setUserId(user.getUserid());
        List<CompetitionOfTeamVo> list=competitionOfTeamMapper.getMyJoinCompetitionTeam(entity);
        return list;
    }
}
