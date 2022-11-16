package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CompetitionResult;
import com.ruoyi.system.domain.vo.CompetitionResultVo;
import org.apache.ibatis.annotations.Param;

/**
 * 赛会中-赛程结果记录Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface CompetitionResultMapper 
{
    /**
     * 查询赛会中-赛程结果记录
     * 
     * @param id 赛会中-赛程结果记录主键
     * @return 赛会中-赛程结果记录
     */
    public CompetitionResult selectCompetitionResultById(Long id);

    /**
     * 查询赛会中-赛程结果记录列表
     * 
     * @param competitionResult 赛会中-赛程结果记录
     * @return 赛会中-赛程结果记录集合
     */
    public List<CompetitionResult> selectCompetitionResultList(CompetitionResult competitionResult);

    /**
     * 新增赛会中-赛程结果记录
     * 
     * @param competitionResult 赛会中-赛程结果记录
     * @return 结果
     */
    public int insertCompetitionResult(CompetitionResult competitionResult);

    /**
     * 修改赛会中-赛程结果记录
     * 
     * @param competitionResult 赛会中-赛程结果记录
     * @return 结果
     */
    public int updateCompetitionResult(CompetitionResult competitionResult);

    /**
     * 删除赛会中-赛程结果记录
     * 
     * @param id 赛会中-赛程结果记录主键
     * @return 结果
     */
    public int deleteCompetitionResultById(Long id);

    /**
     * 批量删除赛会中-赛程结果记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompetitionResultByIds(Long[] ids);

    public List<CompetitionResultVo> findByCompetitionVsId(@Param("competitionId") Long competitionId, @Param("competitionVsId") Long competitionVsId);
}
