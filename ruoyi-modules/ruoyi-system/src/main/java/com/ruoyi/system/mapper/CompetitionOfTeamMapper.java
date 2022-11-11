package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CompetitionOfTeam;
import com.ruoyi.system.domain.vo.CompetitionOfTeamVo;

/**
 * 赛会中-参赛队伍Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface CompetitionOfTeamMapper 
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
     * 删除赛会中-参赛队伍
     * 
     * @param id 赛会中-参赛队伍主键
     * @return 结果
     */
    public int deleteCompetitionOfTeamById(Long id);

    /**
     * 批量删除赛会中-参赛队伍
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompetitionOfTeamByIds(Long[] ids);

    int removeTeamGroup(Long[] ids);

    int intoTeamGroup(String competitionGroup, List<Long> ids);
}
