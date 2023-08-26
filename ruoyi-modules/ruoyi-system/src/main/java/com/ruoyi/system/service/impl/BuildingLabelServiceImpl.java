package com.ruoyi.system.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.ruoyi.system.domain.BuildingLabel;
import com.ruoyi.system.mapper.BuildingLabelMapper;
import com.ruoyi.system.service.IBuildingLabelService;
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
public class BuildingLabelServiceImpl extends ServiceImpl<BuildingLabelMapper, BuildingLabel> implements IBuildingLabelService
{
    @Autowired
    private BuildingLabelMapper buildingLabelMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public BuildingLabel selectBuildingLabelById(Long id)
    {
        return buildingLabelMapper.selectBuildingLabelById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param buildingLabel 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BuildingLabel> selectBuildingLabelList(BuildingLabel buildingLabel)
    {
        return buildingLabelMapper.selectBuildingLabelList(buildingLabel);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param buildingLabel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBuildingLabel(BuildingLabel buildingLabel)
    {
        return buildingLabelMapper.insertBuildingLabel(buildingLabel);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param buildingLabel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBuildingLabel(BuildingLabel buildingLabel)
    {
        return buildingLabelMapper.updateBuildingLabel(buildingLabel);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBuildingLabelByIds(Long[] ids)
    {
        return buildingLabelMapper.deleteBuildingLabelByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBuildingLabelById(Long id)
    {
        return buildingLabelMapper.deleteBuildingLabelById(id);
    }
}
