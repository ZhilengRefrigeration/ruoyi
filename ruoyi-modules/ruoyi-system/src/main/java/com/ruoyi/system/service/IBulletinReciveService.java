package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.BulletinInfo;
import com.ruoyi.system.domain.BulletinRecive;

/**
 * 公告接收者Service接口
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
public interface IBulletinReciveService 
{
    /**
     * 查询公告接收者
     * 
     * @param reciveId 公告接收者主键
     * @return 公告接收者
     */
    public BulletinRecive selectBulletinReciveByReciveId(String reciveId);

    /**
     * 查询公告接收者列表
     * 
     * @param bulletinRecive 公告接收者
     * @return 公告接收者集合
     */
    public List<BulletinInfo> selectBulletinReciveList(BulletinRecive bulletinRecive);

    /**
     * 新增公告接收者
     * 
     * @param bulletinRecive 公告接收者
     * @return 结果
     */
    public int insertBulletinRecive(BulletinRecive bulletinRecive);

    /**
     * 修改公告接收者
     * 
     * @param bulletinRecive 公告接收者
     * @return 结果
     */
    public int updateBulletinRecive(BulletinRecive bulletinRecive);

    /**
     * 批量删除公告接收者
     * 
     * @param reciveIds 需要删除的公告接收者主键集合
     * @return 结果
     */
    public int deleteBulletinReciveByReciveIds(String[] reciveIds);

    /**
     * 删除公告接收者信息
     * 
     * @param reciveId 公告接收者主键
     * @return 结果
     */
    public int deleteBulletinReciveByReciveId(String reciveId);

    /**
     * 批量更新已阅读
     * @param reciveIds
     * @return
     */
	int batchRead(String[] reciveIds);

	/**
	 * 批量物理删除公告接收者
	 * 
	 * @param reciveIds 需要删除的公告接收者主键
	 * @return 结果
	 */
	int deletePhysicalBulletinReciveByReciveIds(String[] reciveIds);
}
