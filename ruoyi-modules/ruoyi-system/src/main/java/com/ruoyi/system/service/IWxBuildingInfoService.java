package com.ruoyi.system.service;

import com.mybatisflex.core.service.IService;
import com.ruoyi.system.domain.WxBuildingInfo;
import com.ruoyi.system.domain.vo.BuildingInfoRequest;
import com.ruoyi.system.domain.vo.BuildingInfoResponse;

import java.util.List;

/**
 * 球场管理Service接口
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public interface IWxBuildingInfoService extends IService<WxBuildingInfo>
{
    /**
     * 查询球场管理
     * 
     * @param id 球场管理主键
     * @return 球场管理
     */
    public WxBuildingInfo selectWxBuildingInfoById(Long id);

    /**
     * 查询球场管理列表
     * 
     * @param wxBuildingInfo 球场管理
     * @return 球场管理集合
     */
    public List<WxBuildingInfo> selectWxBuildingInfoList(WxBuildingInfo wxBuildingInfo);

    /**
     * 新增球场管理
     * 
     * @param wxBuildingInfo 球场管理
     * @return 结果
     */
    public int insertWxBuildingInfo(WxBuildingInfo wxBuildingInfo);

    /**
     * 修改球场管理
     * 
     * @param wxBuildingInfo 球场管理
     * @return 结果
     */
    public int updateWxBuildingInfo(WxBuildingInfo wxBuildingInfo);

    /**
     * 批量删除球场管理
     * 
     * @param ids 需要删除的球场管理主键集合
     * @return 结果
     */
    public int deleteWxBuildingInfoByIds(Long[] ids);

    /**
     * 删除球场管理信息
     * 
     * @param id 球场管理主键
     * @return 结果
     */
    public int deleteWxBuildingInfoById(Long id);

    List<WxBuildingInfo> findNearbyBuilding(WxBuildingInfo entity);

    List<WxBuildingInfo> getBuildingByCity(WxBuildingInfo entity);

    BuildingInfoResponse findAllInfoById(Long id);

    List<BuildingInfoResponse> getAllBuildingByCondition(BuildingInfoRequest entity);

    List<WxBuildingInfo> getAuditPage(WxBuildingInfo buildingInfo);
}
