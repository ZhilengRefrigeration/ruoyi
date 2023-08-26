package com.ruoyi.system.mapper;

import com.mybatisflex.core.BaseMapper;
import com.ruoyi.system.domain.WxUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 微信用户Mapper接口
 * 
 * @author 吴一博
 * @date 2022-08-30
 */
public interface WxUserMapper extends BaseMapper<WxUser>
{
    /**
     * 查询微信用户
     * 
     * @param id 微信用户主键
     * @return 微信用户
     */
    public WxUser selectWxUserById(Long id);

    /**
     * 查询微信用户列表
     * 
     * @param wxUser 微信用户
     * @return 微信用户集合
     */
    public List<WxUser> selectWxUserList(WxUser wxUser);

    /**
     * 新增微信用户
     * 
     * @param wxUser 微信用户
     * @return 结果
     */
    public int insertWxUser(WxUser wxUser);

    /**
     * 修改微信用户
     * 
     * @param wxUser 微信用户
     * @return 结果
     */
    public int updateWxUser(WxUser wxUser);

    /**
     * 删除微信用户
     * 
     * @param id 微信用户主键
     * @return 结果
     */
    public int deleteWxUserById(Long id);

    /**
     * 批量删除微信用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxUserByIds(Long[] ids);

    WxUser selectByOpenId(String openId);

    List<WxUser> listByIds(@Param("userIds") List<Long> userIds);

    WxUser getUserInfoBy(WxUser wxUser);
}
