package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.BuildingLabel;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface IBuildingLabelService extends IService<BuildingLabel>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public BuildingLabel selectBuildingLabelById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param buildingLabel 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BuildingLabel> selectBuildingLabelList(BuildingLabel buildingLabel);

    /**
     * 新增【请填写功能名称】
     * 
     * @param buildingLabel 【请填写功能名称】
     * @return 结果
     */
    public int insertBuildingLabel(BuildingLabel buildingLabel);

    /**
     * 修改【请填写功能名称】
     * 
     * @param buildingLabel 【请填写功能名称】
     * @return 结果
     */
    public int updateBuildingLabel(BuildingLabel buildingLabel);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteBuildingLabelByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBuildingLabelById(Long id);
}
