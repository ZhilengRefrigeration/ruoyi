package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BuildingInfoDetail;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-06
 */
public interface BuildingInfoDetailMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public BuildingInfoDetail selectBuildingInfoDetailById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param buildingInfoDetail 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BuildingInfoDetail> selectBuildingInfoDetailList(BuildingInfoDetail buildingInfoDetail);

    /**
     * 新增【请填写功能名称】
     * 
     * @param buildingInfoDetail 【请填写功能名称】
     * @return 结果
     */
    public int insertBuildingInfoDetail(BuildingInfoDetail buildingInfoDetail);

    /**
     * 修改【请填写功能名称】
     * 
     * @param buildingInfoDetail 【请填写功能名称】
     * @return 结果
     */
    public int updateBuildingInfoDetail(BuildingInfoDetail buildingInfoDetail);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBuildingInfoDetailById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBuildingInfoDetailByIds(Long[] ids);

    BuildingInfoDetail selectOneByBuildingId(Long id);
}
