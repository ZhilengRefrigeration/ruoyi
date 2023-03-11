package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.utils.DateUtils;

import org.apache.commons.lang3.StringUtils;
import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.ruoyi.common.security.utils.SecurityUtils;

import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.BulletinRecive;
import com.ruoyi.system.mapper.BulletinInfoMapper;
import com.ruoyi.system.mapper.BulletinReciveMapper;
import com.ruoyi.system.domain.BulletinInfo;
import com.ruoyi.system.service.IBulletinInfoService;

import ecc.c3.util.IDUtil;

/**
 * 公告栏Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
@Service
public class BulletinInfoServiceImpl implements IBulletinInfoService 
{
	private Logger log=LoggerFactory.getLogger(BulletinInfoServiceImpl.class);
    @Autowired
    private BulletinInfoMapper bulletinInfoMapper;
    @Autowired
    private BulletinReciveMapper bulletinReciveMapper;

    /**
     * 查询公告栏
     * 
     * @param bulletinId 公告栏主键
     * @return 公告栏
     */
    @Override
    public BulletinInfo selectBulletinInfoByBulletinId(String bulletinId)
    {
    	List<BulletinRecive>  reciveUserIdAndbulletinId=bulletinReciveMapper.selectBulletinReciveUserIdByBulletinIds(new String[] {bulletinId});
    	BulletinInfo info= bulletinInfoMapper.selectBulletinInfoByBulletinId(bulletinId);
    	//TODO  把员工换算成名称
    	info.setCreateBy(info.getCreateUserId()+"");
    	info.setUpdateBy(info.getUpdateUserId()+"");
    	List<Long> reciveUserIdList=reciveUserIdAndbulletinId.stream().map(userId->userId.getReciveUserId()).collect(Collectors.toList());
    	info.setReceiveStaffIds(reciveUserIdList);
    	info.setReciveStaffNames(StringUtils.join(reciveUserIdList.iterator(), ","));
    	return info;
    }

    /**
     * 查询公告栏列表
     * 
     * @param bulletinInfo 公告栏
     * @return 公告栏
     */
    @Override
    public List<BulletinInfo> selectBulletinInfoList(BulletinInfo bulletinInfo)
    {
    	bulletinInfo.setCreateUserId(SecurityUtils.getUserId());
    	List<BulletinInfo> list=bulletinInfoMapper.selectBulletinInfoList(bulletinInfo);
    	String[] bulletinIds=list.stream().map(info->info.getBulletinId()).toArray(String[]::new);
    	if(bulletinIds.length>0) {
    		final NutMap reciveUserNameMap=NutMap.NEW();
    		final NutMap reciveBulletinReciveMap=NutMap.NEW();
    		List<BulletinRecive>  reciveUserIdAndbulletinIdList=bulletinReciveMapper.selectBulletinReciveUserIdByBulletinIds(bulletinIds);
    		reciveUserIdAndbulletinIdList.forEach(reciveUserIdAndBulletinId->{
    			//TODO 这里要把reciveUserId换算成用户名
    			reciveUserNameMap.addv2(reciveUserIdAndBulletinId.getBulletinId(), reciveUserIdAndBulletinId.getReciveUserId()+"");
    			reciveBulletinReciveMap.addv2(reciveUserIdAndBulletinId.getBulletinId(), reciveUserIdAndBulletinId);
    		});
    		list.forEach(info->{
    			String reciveStaffNames=String.join(",", reciveUserNameMap.getList(info.getBulletinId(), String.class, Collections.emptyList()));
    			info.setReciveStaffNames(reciveStaffNames);
    			info.setBulletinReciveList(reciveBulletinReciveMap.getList(info.getBulletinId(), BulletinRecive.class,Collections.emptyList()));
    		});
    	}
        return list;
    }

    /**
     * 批量发送公告
     * @param bulletinIds
     * @return
     */
    @Override
    public int sendBulletinInfo(String[] bulletinIds) {
    	int updateCount=0;
    	for (String bulletinId : bulletinIds) {
    		log.info("发送公告,bulletinId:{}",bulletinId);
    		BulletinInfo info=new BulletinInfo();
    		info.setBulletinId(bulletinId);
    		info.setSendTime(DateUtils.getNowDate());
    		info.setUpdateUserId(SecurityUtils.getUserId());
    		info.setUpdateTime(info.getSendTime());
    		info.setSts("A");
    		
    		updateCount+=bulletinInfoMapper.updateBulletinInfo(info);
		}
    	return updateCount;
    }
    /**
     * 推送公告给客户端
     * @param bulletinId
     * @return
     */
    private boolean pushBulletinToClient(String bulletinId) {
    	//TODO  发送公告的业务逻辑还没做，需要获取在线用户，推送给对方
    	log.info("发送公告,bulletinId:{} 业务逻辑还是空的",bulletinId);
    	return true;
    }
    /**
     * 新增公告栏
     * 
     * @param bulletinInfo 公告栏
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBulletinInfo(BulletinInfo bulletinInfo)
    {
    	bulletinInfo.setBulletinId(IDUtil.getStrId());
    	Long userId=SecurityUtils.getUserId();
    	bulletinInfo.setCreateUserId(userId);
    	Date currDate=DateUtils.getNowDate();
    	bulletinInfo.setCreateTime(currDate);
    	bulletinInfo.setUpdateTime(currDate);
    	bulletinInfo.setUpdateUserId(userId);
        if("A".equals(bulletinInfo.getSts())) {
        	bulletinInfo.setSendTime(bulletinInfo.getCreateTime());
        }
    	bulletinInfo.setReadNum(0L);
        int rows = bulletinInfoMapper.insertBulletinInfo(bulletinInfo);
        insertBulletinRecive(bulletinInfo);
        pushBulletinToClient(bulletinInfo.getBulletinId());
        return rows;
    }

    /**
     * 修改公告栏
     * 
     * @param bulletinInfo 公告栏
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBulletinInfo(BulletinInfo bulletinInfo)
    {
        bulletinInfo.setUpdateTime(DateUtils.getNowDate());
        bulletinInfo.setUpdateUserId(SecurityUtils.getUserId());
        if(!bulletinInfo.getReceiveStaffIds().isEmpty()) {
        	 bulletinInfoMapper.deleteBulletinReciveByBulletinId(bulletinInfo.getBulletinId());
             insertBulletinRecive(bulletinInfo);
        }
        return bulletinInfoMapper.updateBulletinInfo(bulletinInfo);
    }

    /**
     * 批量逻辑删除公告栏
     * 
     * @param bulletinIds 需要删除的公告栏主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBulletinInfoByBulletinIds(String[] bulletinIds,String sts)
    {
    	int iCount=0;
    	for (String bulletinId : bulletinIds) {
    		BulletinInfo bulletinInfo=new BulletinInfo();
    		bulletinInfo.setBulletinId(bulletinId);
    		bulletinInfo.setUpdateTime(DateUtils.getNowDate());
            bulletinInfo.setUpdateUserId(SecurityUtils.getUserId());
            bulletinInfo.setSts(sts);
            iCount+=bulletinInfoMapper.updateBulletinInfo(bulletinInfo);
		}
       return iCount;
    }

    /**
     * 逻辑删除公告栏信息
     * 
     * @param bulletinId 公告栏主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBulletinInfoByBulletinId(String bulletinId)
    {
        return deleteBulletinInfoByBulletinIds(new String[] {bulletinId},"B");
    }

    /**
     * 新增公告接收者信息
     * 
     * @param bulletinInfo 公告栏对象
     */
    public void insertBulletinRecive(BulletinInfo bulletinInfo)
    {
    	List<Long> reciveUserIdList=bulletinInfo.getReceiveStaffIds();
    	String bulletinId = bulletinInfo.getBulletinId();
    	List<BulletinRecive> reciveList=new ArrayList<>(reciveUserIdList.size());
    	Date now=DateUtils.getNowDate();
    	bulletinInfo.setBulletinReciveList(reciveList);
    	Long userId=SecurityUtils.getUserId();
    	for (Long reciveUserId : reciveUserIdList) {
    		BulletinRecive bulletinRecive=new BulletinRecive();
    		 bulletinRecive.setBulletinId(bulletinId);
             bulletinRecive.setCreateTime(now);
             bulletinRecive.setCreateUserId(userId);
             bulletinRecive.setUpdateTime(now);
             bulletinRecive.setUpdateUserId(userId);
             bulletinRecive.setReadNum(0L);
             bulletinRecive.setSts("C");
             bulletinRecive.setReciveUserId(reciveUserId);
             bulletinRecive.setReciveId(IDUtil.getStrId());
             reciveList.add(bulletinRecive);
		}
    	 if (reciveList.size() > 0)
         {
             bulletinInfoMapper.batchBulletinRecive(reciveList);
         }
    }
}
