package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TrainingInfo;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface TrainingInfoMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TrainingInfo selectTrainingInfoById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param trainingInfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TrainingInfo> selectTrainingInfoList(TrainingInfo trainingInfo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param trainingInfo 【请填写功能名称】
     * @return 结果
     */
    public int insertTrainingInfo(TrainingInfo trainingInfo);

    /**
     * 修改【请填写功能名称】
     * 
     * @param trainingInfo 【请填写功能名称】
     * @return 结果
     */
    public int updateTrainingInfo(TrainingInfo trainingInfo);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTrainingInfoById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTrainingInfoByIds(Long[] ids);
}
