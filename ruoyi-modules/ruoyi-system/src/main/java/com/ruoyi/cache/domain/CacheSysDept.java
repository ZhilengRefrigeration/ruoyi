package com.ruoyi.cache.domain;

import com.ruoyi.system.api.domain.SysDept;

public class CacheSysDept extends BaseCache{
	/**
	 * @OrgCacheValue的 value类型
	 */
	public final static String ANNOTAION_DEPT_ID="deptId";
	public final static String ANNOTAION_PARENT_ID="parentId";
	public final static String ANNOTAION_DEPT_NAME="deptName";
	public final static String ANNOTAION_PARENT_NAME="parentName";
	
	 /** 部门ID */
    private Long deptId;

    /** 父部门ID */
    private Long parentId;
    
    /** 部门名称 */
    private String deptName;
    
    /** 父部门名称 */
    private String parentName;
    
    public CacheSysDept(SysDept dept) {
    	this.deptId=dept.getDeptId();
    	this.parentId=dept.getParentId();
    	this.deptName=dept.getDeptName();
    	this.parentName=dept.getParentName();
    }
    
    public CacheSysDept() {
    	
    }

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
    

}
