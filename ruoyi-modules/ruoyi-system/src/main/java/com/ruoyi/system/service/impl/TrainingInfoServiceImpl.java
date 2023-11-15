package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.TrainingInfo;
import com.ruoyi.system.mapper.TrainingInfoMapper;
import com.ruoyi.system.service.ITrainingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Service
public class TrainingInfoServiceImpl extends ServiceImpl<TrainingInfoMapper, TrainingInfo> implements ITrainingInfoService
{
    @Autowired
    private TrainingInfoMapper trainingInfoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TrainingInfo selectTrainingInfoById(Long id)
    {
        return trainingInfoMapper.selectTrainingInfoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param trainingInfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TrainingInfo> selectTrainingInfoList(TrainingInfo trainingInfo)
    {
        return trainingInfoMapper.selectTrainingInfoList(trainingInfo);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param trainingInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTrainingInfo(TrainingInfo trainingInfo)
    {
        return trainingInfoMapper.insertTrainingInfo(trainingInfo);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param trainingInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTrainingInfo(TrainingInfo trainingInfo)
    {
        return trainingInfoMapper.updateTrainingInfo(trainingInfo);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTrainingInfoByIds(Long[] ids)
    {
        return trainingInfoMapper.deleteTrainingInfoByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTrainingInfoById(Long id)
    {
        return trainingInfoMapper.deleteTrainingInfoById(id);
    }
}
