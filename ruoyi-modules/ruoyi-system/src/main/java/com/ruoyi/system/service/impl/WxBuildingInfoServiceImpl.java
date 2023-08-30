package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.BuildingInfoDetail;
import com.ruoyi.system.domain.BuildingLabel;
import com.ruoyi.system.domain.FeatureLabel;
import com.ruoyi.system.domain.WxBuildingInfo;
import com.ruoyi.system.domain.vo.BuildingInfoRequest;
import com.ruoyi.system.domain.vo.BuildingInfoResponse;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IWxBuildingInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 球场管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
@Service
public class WxBuildingInfoServiceImpl extends ServiceImpl<WxBuildingInfoMapper, WxBuildingInfo> implements IWxBuildingInfoService
{
    @Autowired
    private WxBuildingInfoMapper wxBuildingInfoMapper;
    @Autowired
    private BuildingInfoDetailMapper buildingInfoDetailMapper;
    @Autowired
    private BuildingLabelMapper buildingLabelMapper;
    @Autowired
    private FeatureLabelMapper featureLabelMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
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

    @Override
    public List<WxBuildingInfo> findNearbyBuilding(WxBuildingInfo entity) {
        return wxBuildingInfoMapper.findNearbyBuilding(entity);
    }

    @Override
    public List<WxBuildingInfo> getBuildingByCity(WxBuildingInfo entity) {
        entity.setStatus(2);
        entity.setIsDeleted(0);
        return wxBuildingInfoMapper.selectWxBuildingInfoList(entity);
    }

    @Override
    public BuildingInfoResponse findAllInfoById(Long id) {
        BuildingInfoResponse response=new BuildingInfoResponse();
        //查询场馆信息
        WxBuildingInfo info=wxBuildingInfoMapper.selectWxBuildingInfoById(id);
        BeanUtils.copyProperties(info,response);
        response.setStatus(info.getStatus());
        //查询场馆详情
        BuildingInfoDetail detail=buildingInfoDetailMapper.selectOneByBuildingId(id);
        if(!ObjectUtils.isEmpty(detail)){
            BeanUtils.copyProperties(detail,response);
        }
        response.setId(id);
        //查询场馆特征标签信息
        BuildingLabel buildingLabel = new BuildingLabel();
        buildingLabel.setBuildingId(id);
        List<BuildingLabel> buildingLabelList=buildingLabelMapper.selectBuildingLabelList(buildingLabel);
        if(buildingLabelList!=null&&buildingLabelList.size()>0){
            List<FeatureLabel> labels=new ArrayList<>();
            for(BuildingLabel blabel:buildingLabelList){
                FeatureLabel label=featureLabelMapper.selectFeatureLabelById(blabel.getFeatureLabelId());
                if(label!=null){
                    labels.add(label);
                }
            }
            response.setLabels(labels);
        }
        return response;
    }

    @Override
    public List<BuildingInfoResponse> getAllBuildingByCondition(BuildingInfoRequest entity) {
        return wxBuildingInfoMapper.getAllBuildingByCondition(entity);
    }

    @Override
    public List<WxBuildingInfo> getAuditPage(WxBuildingInfo buildingInfo) {
        LoginUser user = SecurityUtils.getLoginUser();
       // System.out.println("user="+ JSON.toJSONString(user));
        // 查询当前登录的用户的系统角色
        Set<String> userRoles = user.getRoles();//userRoleMapper.selectUserRoleList(UserRole.builder().userId(user.getUserid()).build());
        if(!StringUtils.isEmpty(userRoles)&&userRoles.size()>0){
            if(userRoles.contains("admin")){
                //查询所有
                buildingInfo.setCreatedId(null);
            }else {
                //查询自己上传的球馆
                buildingInfo.setCreatedId(user.getUserid());
            }
        }else{
            //查询自己上传的球馆
            buildingInfo.setCreatedId(user.getUserid());
        }
        List<WxBuildingInfo> list= wxBuildingInfoMapper.getAuditPage(buildingInfo);
        return list;
    }
}
