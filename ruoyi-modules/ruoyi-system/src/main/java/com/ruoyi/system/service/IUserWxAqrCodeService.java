package com.ruoyi.system.service;

import com.mybatisflex.core.service.IService;
import com.ruoyi.system.domain.UserWxAqrCode;
import com.ruoyi.system.domain.vo.UserWxAqrCodeVo;

import java.util.List;

/**
 * 微信用户小程序二维码Service接口
 * 
 * @author ruoyi
 * @date 2022-10-18
 */
public interface IUserWxAqrCodeService extends IService<UserWxAqrCode>
{
    /**
     * 查询微信用户小程序二维码
     * 
     * @param id 微信用户小程序二维码主键
     * @return 微信用户小程序二维码
     */
    public UserWxAqrCode selectUserWxAqrCodeById(Long id);

    /**
     * 查询微信用户小程序二维码列表
     * 
     * @param userWxAqrCode 微信用户小程序二维码
     * @return 微信用户小程序二维码集合
     */
    public List<UserWxAqrCodeVo> selectUserWxAqrCodeList(UserWxAqrCodeVo userWxAqrCode);

    /**
     * 新增微信用户小程序二维码
     * 
     * @param userWxAqrCode 微信用户小程序二维码
     * @return 结果
     */
    public int insertUserWxAqrCode(UserWxAqrCode userWxAqrCode);

    /**
     * 修改微信用户小程序二维码
     * 
     * @param userWxAqrCode 微信用户小程序二维码
     * @return 结果
     */
    public int updateUserWxAqrCode(UserWxAqrCode userWxAqrCode);

    /**
     * 批量删除微信用户小程序二维码
     * 
     * @param ids 需要删除的微信用户小程序二维码主键集合
     * @return 结果
     */
    public int deleteUserWxAqrCodeByIds(Long[] ids);

    /**
     * 删除微信用户小程序二维码信息
     * 
     * @param id 微信用户小程序二维码主键
     * @return 结果
     */
    public int deleteUserWxAqrCodeById(Long id);

    Boolean genAqrCode(Integer genNumbers, UserWxAqrCode userWxAqrCode);
}
