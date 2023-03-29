package com.ruoyi.system.mapper;

import java.util.List;


import com.ruoyi.system.domain.BulletinInfo;
import com.ruoyi.system.domain.BulletinRecive;

/**
 * 公告栏Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
public interface BulletinInfoMapper 
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
           * 根据bulletinId查询公告栏列表
     * @param bulletinIds
     * @return
     */
    public List<BulletinInfo> selectBulletinInfoListbyBulletinIds(String[] bulletinIds);
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
     * 删除公告栏
     * 
     * @param bulletinId 公告栏主键
     * @return 结果
     */
    public int deleteBulletinInfoByBulletinId(String bulletinId);

    /**
     * 批量删除公告栏
     * 
     * @param bulletinIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBulletinInfoByBulletinIds(String[] bulletinIds);

    /**
     * 批量删除公告接收者
     * 
     * @param bulletinIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBulletinReciveByBulletinIds(String[] bulletinIds);
    
    /**
     * 批量新增公告接收者
     * 
     * @param bulletinReciveList 公告接收者列表
     * @return 结果
     */
    public int batchBulletinRecive(List<BulletinRecive> bulletinReciveList);
    

    /**
     * 通过公告栏主键删除公告接收者信息
     * 
     * @param bulletinId 公告栏ID
     * @return 结果
     */
    public int deleteBulletinReciveByBulletinId(String bulletinId);
}
