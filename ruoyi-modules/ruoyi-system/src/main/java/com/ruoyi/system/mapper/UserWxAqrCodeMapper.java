package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.UserWxAqrCode;
import com.ruoyi.system.domain.vo.UserWxAqrCodeVo;

import java.util.List;

/**
 * 微信用户小程序二维码Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-18
 */
public interface UserWxAqrCodeMapper  extends BaseMapper<UserWxAqrCode>
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
     * 删除微信用户小程序二维码
     * 
     * @param id 微信用户小程序二维码主键
     * @return 结果
     */
    public int deleteUserWxAqrCodeById(Long id);

    /**
     * 批量删除微信用户小程序二维码
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserWxAqrCodeByIds(Long[] ids);
}
