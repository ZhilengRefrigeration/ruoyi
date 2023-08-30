package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.BuildingTeamRel;
import com.ruoyi.system.mapper.BuildingTeamRelMapper;
import com.ruoyi.system.service.IBuildingTeamRelService;
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
public class BuildingTeamRelServiceImpl extends ServiceImpl<BuildingTeamRelMapper, BuildingTeamRel> implements IBuildingTeamRelService
{
    @Autowired
    private BuildingTeamRelMapper buildingTeamRelMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public BuildingTeamRel selectBuildingTeamRelById(Long id)
    {
        return buildingTeamRelMapper.selectBuildingTeamRelById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param buildingTeamRel 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BuildingTeamRel> selectBuildingTeamRelList(BuildingTeamRel buildingTeamRel)
    {
        return buildingTeamRelMapper.selectBuildingTeamRelList(buildingTeamRel);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param buildingTeamRel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBuildingTeamRel(BuildingTeamRel buildingTeamRel)
    {
        return buildingTeamRelMapper.insertBuildingTeamRel(buildingTeamRel);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param buildingTeamRel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBuildingTeamRel(BuildingTeamRel buildingTeamRel)
    {
        return buildingTeamRelMapper.updateBuildingTeamRel(buildingTeamRel);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBuildingTeamRelByIds(Long[] ids)
    {
        return buildingTeamRelMapper.deleteBuildingTeamRelByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBuildingTeamRelById(Long id)
    {
        return buildingTeamRelMapper.deleteBuildingTeamRelById(id);
    }
}
