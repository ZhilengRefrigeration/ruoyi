package com.ruoyi.system.service;

import com.mybatisflex.core.service.IService;
import com.ruoyi.system.domain.CompetitionResult;
import com.ruoyi.system.domain.vo.CompetitionResultVo;
import com.ruoyi.system.domain.vo.CompetitionVsRecordVo;

import java.util.List;

/**
 * 赛会中-赛程结果记录Service接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface ICompetitionResultService  extends IService<CompetitionResult>
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
     * 批量删除赛会中-赛程结果记录
     * 
     * @param ids 需要删除的赛会中-赛程结果记录主键集合
     * @return 结果
     */
    public int deleteCompetitionResultByIds(Long[] ids);

    /**
     * 删除赛会中-赛程结果记录信息
     * 
     * @param id 赛会中-赛程结果记录主键
     * @return 结果
     */
    public int deleteCompetitionResultById(Long id);

    public int batchUpdateCompetitionResult(List<CompetitionResult> list);

    int editData(CompetitionVsRecordVo obj);

    List<CompetitionResultVo> findByCompetitionVsId(Long competitionId, Long id);

    Boolean add(CompetitionResult competitionResult);

    Boolean edit(CompetitionResult competitionResult);
}
