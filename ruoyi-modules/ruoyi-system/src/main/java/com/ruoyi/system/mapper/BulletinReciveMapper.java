package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BulletinRecive;

/**
 * 公告接收者Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
public interface BulletinReciveMapper 
{
	
	/**
	 * 批量更新已阅读
	 * @param bulletinRecive
	 * @return
	 */
	public int batchUpdateRead(String[] reciveIds);
	/**
	 * 只查询出bulletinId和接收的reciveUserId
	 * @param bulletinIds
	 * @return
	 */
	public List<BulletinRecive> selectBulletinReciveUserIdByBulletinIds(String[] bulletinIds);
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
    public List<BulletinRecive> selectBulletinReciveList(BulletinRecive bulletinRecive);

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
     * 删除公告接收者
     * 
     * @param reciveId 公告接收者主键
     * @return 结果
     */
    public int deleteBulletinReciveByReciveId(String reciveId);

    /**
     * 批量删除公告接收者
     * 
     * @param reciveIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBulletinReciveByReciveIds(String[] reciveIds);
}
