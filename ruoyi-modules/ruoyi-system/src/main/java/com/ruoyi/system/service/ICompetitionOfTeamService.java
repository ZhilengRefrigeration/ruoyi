package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.CompetitionOfTeam;
import com.ruoyi.system.domain.vo.CompetitionOfTeamVo;

import java.util.List;

/**
 * 赛会中-参赛队伍Service接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface ICompetitionOfTeamService extends IService<CompetitionOfTeam>
{
    /**
     * 查询赛会中-参赛队伍
     * 
     * @param id 赛会中-参赛队伍主键
     * @return 赛会中-参赛队伍
     */
    public CompetitionOfTeam selectCompetitionOfTeamById(Long id);

    /**
     * 查询赛会中-参赛队伍列表
     * 
     * @param competitionOfTeam 赛会中-参赛队伍
     * @return 赛会中-参赛队伍集合
     */
    public List<CompetitionOfTeamVo> selectCompetitionOfTeamList(CompetitionOfTeam competitionOfTeam);

    /**
     * 新增赛会中-参赛队伍
     * 
     * @param competitionOfTeam 赛会中-参赛队伍
     * @return 结果
     */
    public int insertCompetitionOfTeam(CompetitionOfTeam competitionOfTeam);

    /**
     * 修改赛会中-参赛队伍
     * 
     * @param competitionOfTeam 赛会中-参赛队伍
     * @return 结果
     */
    public int updateCompetitionOfTeam(CompetitionOfTeam competitionOfTeam);
    /**
     * 修改赛会中-参赛队伍
     *
     * @param list 赛会中-参赛队伍
     * @return 结果
     */
    public int batchUpdateCompetitionOfTeam(List<CompetitionOfTeam> list);

    /**
     * 批量删除赛会中-参赛队伍
     * 
     * @param ids 需要删除的赛会中-参赛队伍主键集合
     * @return 结果
     */
    public int deleteCompetitionOfTeamByIds(Long[] ids);

    /**
     * 删除赛会中-参赛队伍信息
     * 
     * @param id 赛会中-参赛队伍主键
     * @return 结果
     */
    public int deleteCompetitionOfTeamById(Long id);

    int removeTeamGroup(Long[] ids);

    int intoTeamGroup(String competitionGroup,List<Long> ids);

    List<CompetitionOfTeamVo> getJoinCompetitionTeam(CompetitionOfTeam entity);

    List<CompetitionOfTeamVo> findCompetitionTeamGroupList(CompetitionOfTeamVo entity);

    CompetitionOfTeam selectOneByTeamName(String teamName);

    Boolean edit(CompetitionOfTeam entity);

    List<CompetitionOfTeamVo> getJoinCompetitionGroupTeam(CompetitionOfTeam ofTeam);

    List<CompetitionOfTeamVo> getMyJoinCompetitionTeam(CompetitionOfTeamVo entity);
}
