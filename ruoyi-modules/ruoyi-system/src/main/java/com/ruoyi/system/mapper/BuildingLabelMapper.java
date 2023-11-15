package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.BuildingLabel;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface BuildingLabelMapper extends BaseMapper<BuildingLabel>
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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBuildingLabelById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBuildingLabelByIds(Long[] ids);
}
