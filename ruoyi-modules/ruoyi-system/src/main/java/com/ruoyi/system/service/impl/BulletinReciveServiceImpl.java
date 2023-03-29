package com.ruoyi.system.service.impl;

import java.util.Collections;
import java.util.List;

import com.ruoyi.cache.service.IOrgCacheService;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.security.utils.SecurityUtils;

import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.BulletinInfoMapper;
import com.ruoyi.system.mapper.BulletinReciveMapper;
import com.ruoyi.system.domain.BulletinInfo;
import com.ruoyi.system.domain.BulletinRecive;
import com.ruoyi.system.service.IBulletinReciveService;

/**
 * 公告接收者Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
@Service
public class BulletinReciveServiceImpl implements IBulletinReciveService 
{
    @Autowired
    private BulletinReciveMapper bulletinReciveMapper;
    @Autowired
    private BulletinInfoMapper bulletinInfoMapper;
    @Autowired
    private IOrgCacheService orgCacheService;

    @Transactional
    /**
     * 批量更新已阅读
     * @param reciveIds
     * @return
     */
    @Override
    public int batchRead(String[] reciveIds) {
    	return bulletinReciveMapper.batchUpdateRead(reciveIds);
    }
    /**
     * 查询公告接收者
     * 
     * @param reciveId 公告接收者主键
     * @return 公告接收者
     */
    @Override
    public BulletinRecive selectBulletinReciveByReciveId(String reciveId)
    {
        return bulletinReciveMapper.selectBulletinReciveByReciveId(reciveId);
    }

    /**
     * 查询公告接收者列表
     * 
     * @param bulletinRecive 公告接收者
     * @return 公告接收者
     */
    @Override
    public List<BulletinInfo> selectBulletinReciveList(BulletinRecive bulletinRecive)
    {
    	bulletinRecive.setReciveUserId(SecurityUtils.getUserId());
    	List<BulletinRecive> reciveList= bulletinReciveMapper.selectBulletinReciveList(bulletinRecive);
    	if(reciveList.isEmpty()) {
    		return Collections.emptyList();
    	}
    	reciveList.forEach(recive->{
    		orgCacheService.getSysUser(recive.getReciveUserId()).ifPresent(cacheSysUser->{
    			recive.setCreateBy(cacheSysUser.getUserName());
    			recive.setReciveDeptId(cacheSysUser.getDeptId());
    		});
    	});
    	NutMap map=NutMap.NEW();
    	String[] bulletinIds=new String[reciveList.size()];
    	for (int i = 0; i < bulletinIds.length; i++) {
    		BulletinRecive recive=reciveList.get(i);
    		map.addv2(recive.getBulletinId(), recive);
    		bulletinIds[i]=recive.getBulletinId();
		}
    	List<BulletinInfo> infoList=bulletinInfoMapper.selectBulletinInfoListbyBulletinIds(bulletinIds);
    	infoList.forEach(info->{
    		info.setBulletinReciveList(map.getList(info.getBulletinId(), BulletinRecive.class,Collections.emptyList()));
    	});
        return infoList;
    }

    /**
     * 新增公告接收者
     * 
     * @param bulletinRecive 公告接收者
     * @return 结果
     */
    @Override
    public int insertBulletinRecive(BulletinRecive bulletinRecive)
    {
        bulletinRecive.setCreateTime(DateUtils.getNowDate());
        return bulletinReciveMapper.insertBulletinRecive(bulletinRecive);
    }

    /**
     * 修改公告接收者
     * 
     * @param bulletinRecive 公告接收者
     * @return 结果
     */
    @Override
    public int updateBulletinRecive(BulletinRecive bulletinRecive)
    {
        bulletinRecive.setUpdateTime(DateUtils.getNowDate());
        bulletinRecive.setUpdateUserId(SecurityUtils.getUserId());
        return bulletinReciveMapper.updateBulletinRecive(bulletinRecive);
    }
    
    /**
     * 批量物理删除公告接收者
     * 
     * @param reciveIds 需要删除的公告接收者主键
     * @return 结果
     */
    @Override
    public int deletePhysicalBulletinReciveByReciveIds(String[] reciveIds)
    {
    	return bulletinReciveMapper.deleteBulletinReciveByReciveIds(reciveIds);
    }

    
    /**
     * 批量逻辑删除公告接收者
     * 
     * @param reciveIds 需要删除的公告接收者主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteBulletinReciveByReciveIds(String[] reciveIds)
    {
    	int iCount=0;
    	for (String reciveId : reciveIds) {
    		BulletinRecive bulletinRecive=new BulletinRecive();
    		bulletinRecive.setReciveId(reciveId);
    		bulletinRecive.setSts("B");
    		bulletinRecive.setUpdateTime(DateUtils.getNowDate());
    		bulletinRecive.setUpdateUserId(SecurityUtils.getUserId());
    		iCount+=bulletinReciveMapper.updateBulletinRecive(bulletinRecive);
		}
        return iCount;
    }

    /**
     * 删除公告接收者信息
     * 
     * @param reciveId 公告接收者主键
     * @return 结果
     */
    @Override
    public int deleteBulletinReciveByReciveId(String reciveId)
    {
        return bulletinReciveMapper.deleteBulletinReciveByReciveId(reciveId);
    }
}
