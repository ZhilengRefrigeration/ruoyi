package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxBuildingInfoMapper;
import com.ruoyi.system.domain.WxBuildingInfo;
import com.ruoyi.system.service.IWxBuildingInfoService;

/**
 * 球场管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
@Service
public class WxBuildingInfoServiceImpl implements IWxBuildingInfoService 
{
    @Autowired
    private WxBuildingInfoMapper wxBuildingInfoMapper;

    /**
     * 查询球场管理
     * 
     * @param id 球场管理主键
     * @return 球场管理
     */
    @Override
    public WxBuildingInfo selectWxBuildingInfoById(Long id)
    {
        return wxBuildingInfoMapper.selectWxBuildingInfoById(id);
    }

    /**
     * 查询球场管理列表
     * 
     * @param wxBuildingInfo 球场管理
     * @return 球场管理
     */
    @Override
    public List<WxBuildingInfo> selectWxBuildingInfoList(WxBuildingInfo wxBuildingInfo)
    {
        return wxBuildingInfoMapper.selectWxBuildingInfoList(wxBuildingInfo);
    }

    /**
     * 新增球场管理
     * 
     * @param wxBuildingInfo 球场管理
     * @return 结果
     */
    @Override
    public int insertWxBuildingInfo(WxBuildingInfo wxBuildingInfo)
    {
        return wxBuildingInfoMapper.insertWxBuildingInfo(wxBuildingInfo);
    }

    /**
     * 修改球场管理
     * 
     * @param wxBuildingInfo 球场管理
     * @return 结果
     */
    @Override
    public int updateWxBuildingInfo(WxBuildingInfo wxBuildingInfo)
    {
        return wxBuildingInfoMapper.updateWxBuildingInfo(wxBuildingInfo);
    }

    /**
     * 批量删除球场管理
     * 
     * @param ids 需要删除的球场管理主键
     * @return 结果
     */
    @Override
    public int deleteWxBuildingInfoByIds(Long[] ids)
    {
        return wxBuildingInfoMapper.deleteWxBuildingInfoByIds(ids);
    }

    /**
     * 删除球场管理信息
     * 
     * @param id 球场管理主键
     * @return 结果
     */
    @Override
    public int deleteWxBuildingInfoById(Long id)
    {
        return wxBuildingInfoMapper.deleteWxBuildingInfoById(id);
    }
}
