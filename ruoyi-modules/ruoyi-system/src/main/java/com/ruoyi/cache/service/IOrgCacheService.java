package com.ruoyi.cache.service;

import java.util.Optional;

import com.ruoyi.cache.domain.CacheSysDept;
import com.ruoyi.cache.domain.CacheSysUser;

/**
 * 组织架构的缓存类
 * 一级缓存，本地ecache缓存. 二级缓存:redis缓存.
 * @author Condy
 *
 */
public interface IOrgCacheService {

	/**
	 * 把员工信息存储到redis服务器上.
	 * @param staffInfo
	 * @return
	 */
	public CacheSysUser saveSysUser(CacheSysUser cacheSysUser);

	/**
	 * 删除员工
	 * @param userId
	 */
	public void deleteSysUser(Long userId);

	/**
	 * 从redis上获取员工信息,先读取ecache缓存，没有的话到redis服务器读取.
	 * @param staffId
	 * @return CacheStaffInfo 取不到staffId对应的信息时会返回null
	 */
	public Optional<CacheSysUser> getSysUser(long userId);

	/**
	 * 从redis服务器中获取部门信息.
	 * @param deptId
	 * @return 如果取不到部门信息会返回null
	 */
	public Optional<CacheSysDept> getDeptInfo(long deptId);

	/**
	 * 将部门数据存储到redis缓存中
	 * @param cacheDeptInfo
	 * @return
	 */
	public CacheSysDept saveDeptInfo(CacheSysDept cacheDeptInfo);
	/**
	 * 删除部分
	 * @param deptId
	 */
	public void deleteDeptInfo(Long deptId);




}