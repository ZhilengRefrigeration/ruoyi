package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BulletinInfo;

/**
 * 公告栏Service接口
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
public interface IBulletinInfoService 
{
    /**
     * 查询公告栏
     * 
     * @param bulletinId 公告栏主键
     * @return 公告栏
     */
    public BulletinInfo selectBulletinInfoByBulletinId(String bulletinId);

    /**
     * 查询公告栏列表
     * 
     * @param bulletinInfo 公告栏
     * @return 公告栏集合
     */
    public List<BulletinInfo> selectBulletinInfoList(BulletinInfo bulletinInfo);

    /**
     * 新增公告栏
     * 
     * @param bulletinInfo 公告栏
     * @return 结果
     */
    public int insertBulletinInfo(BulletinInfo bulletinInfo);

    /**
     * 修改公告栏
     * 
     * @param bulletinInfo 公告栏
     * @return 结果
     */
    public int updateBulletinInfo(BulletinInfo bulletinInfo);

    /**
     * 批量删除公告栏
     * 
     * @param bulletinIds 需要删除的公告栏主键集合
     * @param sts B:逻辑删除，D:物理删除
     * @return 结果
     */
    public int deleteBulletinInfoByBulletinIds(String[] bulletinIds,String sts);

    /**
     * 删除公告栏信息
     * 
     * @param bulletinId 公告栏主键
     * @return 结果
     */
    public int deleteBulletinInfoByBulletinId(String bulletinId);

	/**
	 * 批量发送公告
	 * @param bulletinIds
	 * @return
	 */
	int sendBulletinInfo(String[] bulletinIds);
}
