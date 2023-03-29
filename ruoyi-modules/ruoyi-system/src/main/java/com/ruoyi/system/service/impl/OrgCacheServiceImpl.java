package com.ruoyi.system.service.impl;

import java.util.Optional;

import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.ruoyi.cache.domain.CacheSysDept;
import com.ruoyi.cache.domain.CacheSysUser;
import com.ruoyi.cache.service.IOrgCacheService;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.domain.SysDept;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.util.IDUtil;
import com.ruoyi.util.IRedisNotifyHandle;
import com.ruoyi.util.RedisNotifyService;
@Component
/**
 * 处理组织架构的缓存
 * @author condy
 *
 */
public class OrgCacheServiceImpl implements IOrgCacheService,IRedisNotifyHandle {

	@Autowired
	private RedisService redisService;
	@Autowired
	private RedisNotifyService redisNotifyService;
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private SysDeptMapper sysDeptMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	
	private final String USER_PRE="RUOYI_USER:";
	private final String DEPT_PRE="RUOYI_DEPT:";
	
	private long selfId=IDUtil.getId();
	private Logger log=LoggerFactory.getLogger(getClass());
	public OrgCacheServiceImpl() {
		RedisNotifyService.register(this);
	}
	
	@Override
	@CachePut(value="org",key="'RUOYI_USER:'+#cacheSysUser.getUserId()")
	public CacheSysUser saveSysUser(CacheSysUser cacheSysUser) {
		if(cacheSysUser==null || cacheSysUser.getUserId()==null) {
			log.warn("保存员工到缓存错误, 员工ID是空的");
			return null;
		}
		redisService.setCacheObject(USER_PRE+cacheSysUser.getUserId(), cacheSysUser);
		NutMap params=NutMap.NEW().addv("type", "RUOYI_USER").addv("userId", cacheSysUser.getUserId());
		params.addv("selfId", selfId);
		redisNotifyService.publish(getRegisterId(), params);
		return cacheSysUser;
	}

	@Override
	@Cacheable(value="org",key="'RUOYI_USER:'+#userId")
	public Optional<CacheSysUser> getSysUser(long userId) {
		CacheSysUser cacheSysUser=redisService.getCacheObject(USER_PRE+userId);
		if(cacheSysUser==null) {
			SysUser sysUser=sysUserMapper.selectUserById(userId);
			if(sysUser!=null) {
				cacheSysUser=new CacheSysUser(sysUser);
				saveSysUser(cacheSysUser);
			}
		}
		return Optional.ofNullable(cacheSysUser);
	}

	//除非，#result是空，就不缓存
	@Override
	@Cacheable(value="org",key="'RUOYI_DEPT:'+#deptId")
	public Optional<CacheSysDept>  getDeptInfo(long deptId) {
		CacheSysDept cacheSysDept=redisService.getCacheObject(DEPT_PRE+deptId);
		if(cacheSysDept==null) {
			SysDept sysDept=sysDeptMapper.selectDeptById(deptId);
			if(sysDept!=null) {
				cacheSysDept=new CacheSysDept(sysDept);
				saveDeptInfo(cacheSysDept);
			}
		}
		return Optional.ofNullable(cacheSysDept);
	}

	@Override
	@CachePut(value="org",key="'RUOYI_DEPT:'+#cacheDeptInfo.getDeptId()")
	public CacheSysDept saveDeptInfo(CacheSysDept cacheDeptInfo) {
		if(cacheDeptInfo==null || cacheDeptInfo.getDeptId()==null) {
			log.warn("保存部门到缓存错误, 部门ID是空的");
			return null;
		}
		redisService.setCacheObject(DEPT_PRE+cacheDeptInfo.getDeptId(), cacheDeptInfo);
		NutMap params=NutMap.NEW().addv("type", "RUOYI_DEPT").addv("deptId", cacheDeptInfo.getDeptId());
		params.addv("selfId", selfId);
		redisNotifyService.publish(getRegisterId(), params);
		return cacheDeptInfo;
	}

	@Override
	public void handle(String methodName,NutMap params) {
		String type=params.getString("type");
		long notifySelfId=params.getLong("selfId");
		if(notifySelfId==selfId) {
			log.info("selfId:{} 是自己发出的，不做任何处理");
			return ;
		}
		if(USER_PRE.substring(0, USER_PRE.length()-1).equals(type)) {
			log.info("把组织架构的staffId:{}的缓存作废",params.getLong("userId"));
			cacheManager.getCache("org").evict(USER_PRE+params.getLong("userId"));
		} else if (DEPT_PRE.substring(0, DEPT_PRE.length()-1).equals(type)) {
			//把某个部门缓存作废.
			log.info("把组织架构的deptId:{}的缓存作废",params.getLong("deptId"));
			cacheManager.getCache("org").evict(DEPT_PRE+params.getLong("deptId"));
		} else {
			log.warn("无法识别的type:{}", type);
		}
	}

	@Override
	public String getRegisterId() {
		return "OrgCacheService";
	}

	@Override
	@CacheEvict(value="org",key="'RUOYI_USER:'+#userId")
	public void deleteSysUser(Long userId) {
		if(userId==null) {
			return ;
		}
		redisService.deleteObject(USER_PRE+userId);
		NutMap params=NutMap.NEW().addv("type", "RUOYI_USER").addv("userId", userId);
		params.addv("selfId", selfId);
		redisNotifyService.publish(getRegisterId(), params);
	}

	@Override
	@CacheEvict(value="org",key="'RUOYI_DEPT:'+#deptId")
	public void deleteDeptInfo(Long deptId) {
		if(deptId==null) {
			return ;
		}
		redisService.deleteObject(DEPT_PRE+deptId);
		NutMap params=NutMap.NEW().addv("type", "RUOYI_DEPT").addv("deptId", deptId);
		params.addv("selfId", selfId);
		redisNotifyService.publish(getRegisterId(), params);
	}

}
