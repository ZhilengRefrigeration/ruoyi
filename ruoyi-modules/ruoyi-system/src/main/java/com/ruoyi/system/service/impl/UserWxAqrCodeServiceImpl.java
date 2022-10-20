package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
import com.ruoyi.system.api.feign.WxAppletsFeign;
import com.ruoyi.system.domain.vo.UserWxAqrCodeVo;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserWxAqrCodeMapper;
import com.ruoyi.system.domain.UserWxAqrCode;
import com.ruoyi.system.service.IUserWxAqrCodeService;

import javax.annotation.Resource;

/**
 * 微信用户小程序二维码Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-18
 */
@Service
public class UserWxAqrCodeServiceImpl implements IUserWxAqrCodeService 
{
    @Autowired
    private UserWxAqrCodeMapper userWxAqrCodeMapper;
    @Resource
    private WxAppletsFeign wxAppletsFeign;

    /**
     * 查询微信用户小程序二维码
     * 
     * @param id 微信用户小程序二维码主键
     * @return 微信用户小程序二维码
     */
    @Override
    public UserWxAqrCode selectUserWxAqrCodeById(Long id)
    {
        return userWxAqrCodeMapper.selectUserWxAqrCodeById(id);
    }

    /**
     * 查询微信用户小程序二维码列表
     * 
     * @param userWxAqrCode 微信用户小程序二维码
     * @return 微信用户小程序二维码
     */
    @Override
    public List<UserWxAqrCodeVo> selectUserWxAqrCodeList(UserWxAqrCodeVo userWxAqrCode)
    {
        return userWxAqrCodeMapper.selectUserWxAqrCodeList(userWxAqrCode);
    }

    /**
     * 新增微信用户小程序二维码
     * 
     * @param userWxAqrCode 微信用户小程序二维码
     * @return 结果
     */
    @Override
    public int insertUserWxAqrCode(UserWxAqrCode userWxAqrCode)
    {
        return userWxAqrCodeMapper.insertUserWxAqrCode(userWxAqrCode);
    }

    /**
     * 修改微信用户小程序二维码
     * 
     * @param userWxAqrCode 微信用户小程序二维码
     * @return 结果
     */
    @Override
    public int updateUserWxAqrCode(UserWxAqrCode userWxAqrCode)
    {
        return userWxAqrCodeMapper.updateUserWxAqrCode(userWxAqrCode);
    }

    /**
     * 批量删除微信用户小程序二维码
     * 
     * @param ids 需要删除的微信用户小程序二维码主键
     * @return 结果
     */
    @Override
    public int deleteUserWxAqrCodeByIds(Long[] ids)
    {
        return userWxAqrCodeMapper.deleteUserWxAqrCodeByIds(ids);
    }

    /**
     * 删除微信用户小程序二维码信息
     * 
     * @param id 微信用户小程序二维码主键
     * @return 结果
     */
    @Override
    public int deleteUserWxAqrCodeById(Long id)
    {
        return userWxAqrCodeMapper.deleteUserWxAqrCodeById(id);
    }

    @Override
    public Boolean genAqrCode(Integer genNumbers, UserWxAqrCode userWxAqrCode) {
        String accessToken = wxAppletsFeign.getAccessToken();
        userWxAqrCode.setCreatedBy(SecurityUtils.getUsername());
        userWxAqrCode.setCreatedTime(new Date());
        int id = userWxAqrCodeMapper.insertUserWxAqrCode(userWxAqrCode);
        System.out.println("id = "+userWxAqrCode.getId()+"   accessToken = "+ accessToken);
        WxAppletsCodeVo wxAppletsCodeVo = new WxAppletsCodeVo();
        if(StringUtils.isEmpty(userWxAqrCode.getScene())) {
            StringBuffer sceneBuffer = new StringBuffer();
            sceneBuffer.append("id=");
            sceneBuffer.append(userWxAqrCode.getId());
            sceneBuffer.append("&tid=");
            sceneBuffer.append(userWxAqrCode.getTeamId());
            sceneBuffer.append("&uid=");
            sceneBuffer.append(userWxAqrCode.getUserId());
            wxAppletsCodeVo.setScene(sceneBuffer.toString());
        }else {
            wxAppletsCodeVo.setScene(userWxAqrCode.getScene());
        }
        if(StringUtils.isEmpty(userWxAqrCode.getScene())) {
            wxAppletsCodeVo.setPage("pages/index2/index2");
        }else {
            wxAppletsCodeVo.setPage(userWxAqrCode.getPage());
        }
        if(userWxAqrCode.getWidth() == null) {
            wxAppletsCodeVo.setWidth(50);
        }else {
            wxAppletsCodeVo.setWidth(userWxAqrCode.getWidth());
        }
        wxAppletsCodeVo = wxAppletsFeign.getWxacodeunlimit(wxAppletsCodeVo,accessToken);
        //更新二维码表
        userWxAqrCode.setCodeImgUrl(wxAppletsCodeVo.getCodeImgUrl());
        userWxAqrCode.setScene(wxAppletsCodeVo.getScene());
        userWxAqrCode.setWidth(wxAppletsCodeVo.getWidth());
        userWxAqrCodeMapper.updateUserWxAqrCode(userWxAqrCode);
        return Boolean.TRUE;
    }
}
