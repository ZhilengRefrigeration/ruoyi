package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.BuildingInfoDetail;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-07-06
 */
public interface IBuildingInfoDetailService extends IService<BuildingInfoDetail>
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
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteBuildingInfoDetailByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBuildingInfoDetailById(Long id);
}
