package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.FeatureLabel;
import com.ruoyi.system.mapper.FeatureLabelMapper;
import com.ruoyi.system.service.IFeatureLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 球馆特征Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-06
 */
@Service
public class FeatureLabelServiceImpl extends ServiceImpl<FeatureLabelMapper, FeatureLabel> implements IFeatureLabelService
{
    @Autowired
    private FeatureLabelMapper featureLabelMapper;

    /**
     * 查询球馆特征
     * 
     * @param id 球馆特征主键
     * @return 球馆特征
     */
    @Override
    public FeatureLabel selectFeatureLabelById(Long id)
    {
        return featureLabelMapper.selectFeatureLabelById(id);
    }

    /**
     * 查询球馆特征列表
     * 
     * @param featureLabel 球馆特征
     * @return 球馆特征
     */
    @Override
    public List<FeatureLabel> selectFeatureLabelList(FeatureLabel featureLabel)
    {
        return featureLabelMapper.selectFeatureLabelList(featureLabel);
    }

    /**
     * 新增球馆特征
     * 
     * @param featureLabel 球馆特征
     * @return 结果
     */
    @Override
    public int insertFeatureLabel(FeatureLabel featureLabel)
    {
        return featureLabelMapper.insertFeatureLabel(featureLabel);
    }

    /**
     * 修改球馆特征
     * 
     * @param featureLabel 球馆特征
     * @return 结果
     */
    @Override
    public int updateFeatureLabel(FeatureLabel featureLabel)
    {
        return featureLabelMapper.updateFeatureLabel(featureLabel);
    }

    /**
     * 批量删除球馆特征
     * 
     * @param ids 需要删除的球馆特征主键
     * @return 结果
     */
    @Override
    public int deleteFeatureLabelByIds(Long[] ids)
    {
        return featureLabelMapper.deleteFeatureLabelByIds(ids);
    }

    /**
     * 删除球馆特征信息
     * 
     * @param id 球馆特征主键
     * @return 结果
     */
    @Override
    public int deleteFeatureLabelById(Long id)
    {
        return featureLabelMapper.deleteFeatureLabelById(id);
    }
}
