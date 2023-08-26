package com.ruoyi.system.mapper;

import com.mybatisflex.core.BaseMapper;
import com.ruoyi.system.domain.FeatureLabel;

import java.util.List;

/**
 * 球馆特征Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-06
 */
public interface FeatureLabelMapper extends BaseMapper<FeatureLabel>
{
    /**
     * 查询球馆特征
     * 
     * @param id 球馆特征主键
     * @return 球馆特征
     */
    public FeatureLabel selectFeatureLabelById(Long id);

    /**
     * 查询球馆特征列表
     * 
     * @param featureLabel 球馆特征
     * @return 球馆特征集合
     */
    public List<FeatureLabel> selectFeatureLabelList(FeatureLabel featureLabel);

    /**
     * 新增球馆特征
     * 
     * @param featureLabel 球馆特征
     * @return 结果
     */
    public int insertFeatureLabel(FeatureLabel featureLabel);

    /**
     * 修改球馆特征
     * 
     * @param featureLabel 球馆特征
     * @return 结果
     */
    public int updateFeatureLabel(FeatureLabel featureLabel);

    /**
     * 删除球馆特征
     * 
     * @param id 球馆特征主键
     * @return 结果
     */
    public int deleteFeatureLabelById(Long id);

    /**
     * 批量删除球馆特征
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFeatureLabelByIds(Long[] ids);
}
