package com.ruoyi.system.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.ruoyi.system.domain.BuildingInfoDetail;
import com.ruoyi.system.mapper.BuildingInfoDetailMapper;
import com.ruoyi.system.service.IBuildingInfoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-06
 */
@Service
public class BuildingInfoDetailServiceImpl extends ServiceImpl<BuildingInfoDetailMapper, BuildingInfoDetail>  implements IBuildingInfoDetailService
{
    @Autowired
    private BuildingInfoDetailMapper buildingInfoDetailMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public BuildingInfoDetail selectBuildingInfoDetailById(Long id)
    {
        return buildingInfoDetailMapper.selectBuildingInfoDetailById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param buildingInfoDetail 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BuildingInfoDetail> selectBuildingInfoDetailList(BuildingInfoDetail buildingInfoDetail)
    {
        return buildingInfoDetailMapper.selectBuildingInfoDetailList(buildingInfoDetail);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param buildingInfoDetail 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBuildingInfoDetail(BuildingInfoDetail buildingInfoDetail)
    {
        return buildingInfoDetailMapper.insertBuildingInfoDetail(buildingInfoDetail);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param buildingInfoDetail 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBuildingInfoDetail(BuildingInfoDetail buildingInfoDetail)
    {
        return buildingInfoDetailMapper.updateBuildingInfoDetail(buildingInfoDetail);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBuildingInfoDetailByIds(Long[] ids)
    {
        return buildingInfoDetailMapper.deleteBuildingInfoDetailByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBuildingInfoDetailById(Long id)
    {
        return buildingInfoDetailMapper.deleteBuildingInfoDetailById(id);
    }
}
