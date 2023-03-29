package com.ruoyi.cache.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.LoopException;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.ruoyi.cache.annotation.OrgCacheKey;
import com.ruoyi.cache.annotation.OrgCacheTypeNum;
import com.ruoyi.cache.annotation.OrgCacheValue;
import com.ruoyi.cache.domain.BaseCache;
import com.ruoyi.cache.domain.CacheSysDept;
import com.ruoyi.cache.domain.CacheSysUser;
import com.ruoyi.common.core.utils.SpringUtils;

/**
 * @OrgCacheKey
 * @OrgCacheValue的注解的处理类
 * @author 
 *
 * @param <T>
 */
public class CacheCallBack<T> implements Each<T> {
	private Log log=Logs.get();
	private  class InnerCacheCallBack {
		//@OrgCacheKey的注解的属性
		private Field orgCacheKeyField;
		private OrgCacheKey orgCacheKey;// @OrgCacheKey对象
		//与OrgCacheKey的key相关的OrgCacheValue注解对象及对象的Field
		private Map<OrgCacheValue,Field> orgCacheValueFieldMap=new HashMap<OrgCacheValue,Field>();
		//CacheStaffInfo,CacheDeptInfo,CacheCopanyInfo的放射封装类
		private Mirror<?> cacheBaseMirror;
		public Field getOrgCacheKeyField() {
			return orgCacheKeyField;
		}
		public void setOrgCacheKeyField(Field orgCacheKeyField) {
			this.orgCacheKeyField = orgCacheKeyField;
		}
		
		public Mirror<?> getCacheBaseMirror() {
			return cacheBaseMirror;
		}
		public void setCacheBaseMirror(Mirror<?> cacheBaseMirror) {
			this.cacheBaseMirror = cacheBaseMirror;
		}
		public OrgCacheKey getOrgCacheKey() {
			return orgCacheKey;
		}
		public void setOrgCacheKey(OrgCacheKey orgCacheKey) {
			this.orgCacheKey = orgCacheKey;
		}
		
		public void addOrgCacheValueField(OrgCacheValue orgCacheValue,Field orgCacheValueField){
			orgCacheValueFieldMap.put(orgCacheValue,orgCacheValueField);
		}
		public Map<OrgCacheValue, Field> getOrgCacheValueFieldMap() {
			return orgCacheValueFieldMap;
		}
		
	}

	/**
	 * 采用默认的orgCacheService类
	 */
	public CacheCallBack(){
		this.orgCacheService=SpringUtils.getBean(IOrgCacheService.class);
	}
	/**
	 * 通过传递参数的方式.
	 * @param orgCacheService
	 */
	public CacheCallBack(IOrgCacheService orgCacheService){
		this.orgCacheService=orgCacheService;
	}
	private IOrgCacheService orgCacheService;
	private Mirror<?> elemirror=null;
	private Map<String,InnerCacheCallBack> innerCacheCallBackMap=new HashMap<String,InnerCacheCallBack>();
	
	/**
	 * 初始化循环映射的参数.
	 * @param ele
	 */
	private void init(T ele){
		elemirror = Mirror.me(ele.getClass());//获取ele对象的反射类
		//获取含有:OrgCacheKey的属性对象,初始化InnerCacheCallBack对象.
		Field[] orgCacheKeyField=elemirror.getFields(OrgCacheKey.class);
		for (int i = 0; i < orgCacheKeyField.length; i++) {
			InnerCacheCallBack innerCacheCallBack=new InnerCacheCallBack();
			innerCacheCallBack.setOrgCacheKeyField(orgCacheKeyField[i]);
			innerCacheCallBack.setOrgCacheKey(orgCacheKeyField[i].getAnnotation(OrgCacheKey.class));
			innerCacheCallBackMap.put(innerCacheCallBack.getOrgCacheKey().id(), innerCacheCallBack);
			OrgCacheTypeNum typeNum=innerCacheCallBack.getOrgCacheKey().type();
			switch(typeNum){
				case CacheUserInfo:
					innerCacheCallBack.setCacheBaseMirror( Mirror.me(CacheSysUser.class));
					break;
				case CacheDeptInfo:
					innerCacheCallBack.setCacheBaseMirror( Mirror.me(CacheSysDept.class));
					break;
				default:
					log.warnf("无法识别的枚举类型:%s",typeNum.toString());
					continue;
			}
		}
		Field[] aOrgCacheValueField=elemirror.getFields(OrgCacheValue.class);
		for (int i = 0; i < aOrgCacheValueField.length; i++) {
			OrgCacheValue orgCacheValue=aOrgCacheValueField[i].getAnnotation(OrgCacheValue.class);
			if(innerCacheCallBackMap.containsKey(orgCacheValue.id())){
				innerCacheCallBackMap.get(orgCacheValue.id()).addOrgCacheValueField(orgCacheValue,aOrgCacheValueField[i]);
			} else{
				log.warnf("找不到id:%s对应的Cache对象.请检查的@OrgCacheValue注解 id值，类:%s,属性:%s注解", 
						orgCacheValue.id(),ele.getClass(),aOrgCacheValueField[i].getName());
			}
		}
	}
	@Override
	public void invoke(int index, T ele, int length) throws ExitLoop, ContinueLoop, LoopException {
		if(elemirror==null){
			init(ele);
		} 
		for (Iterator<Map.Entry<String, InnerCacheCallBack>> iterator = innerCacheCallBackMap.entrySet().iterator(); iterator.hasNext();) {
			/**
			 * 获取BaseCache对象.
			 */
			Map.Entry<String, InnerCacheCallBack> type =  iterator.next();
			InnerCacheCallBack innerCacheCallBack=type.getValue();
			Mirror<?> cacheBaseMirror=innerCacheCallBack.getCacheBaseMirror();
			Object orgCacheKeyFieldValue=cacheBaseMirror.getValue(ele, innerCacheCallBack.getOrgCacheKeyField());
			if(orgCacheKeyFieldValue==null)//获取的orgCacheKeyFieldValue 对应的值为null 就不需要在进入后续的循环了.
				continue;
			OrgCacheTypeNum typeNum=innerCacheCallBack.getOrgCacheKey().type();
			BaseCache baseCache=null;
			switch(typeNum){
				case CacheUserInfo:
					baseCache=orgCacheService.getSysUser((Long)orgCacheKeyFieldValue).orElse(null);
					if(baseCache==null){
						log.warnf("userId:%s获取到CacheStaffInfo的缓存数据是空。请检查Redis服务器.",orgCacheKeyFieldValue);
						continue;
					}
					break;
				case CacheDeptInfo:
					baseCache=orgCacheService.getDeptInfo((Long)orgCacheKeyFieldValue).orElse(null);
					if(baseCache==null){
						log.warnf("deptId:%s获取到CacheDeptInfo的缓存数据是空。请检查Redis服务器.",orgCacheKeyFieldValue);
						continue;
					}
					break;
				default:
					log.warnf("无法识别的枚举类型:%s",typeNum.toString());
					continue;
					
			}
			/**
			 * 将BaseCache对象的值设置到ele对象里面.
			 */
			for (Iterator<Map.Entry<OrgCacheValue,Field>> iterator2 = innerCacheCallBack.getOrgCacheValueFieldMap().entrySet().iterator(); iterator2.hasNext();) {
				Map.Entry<OrgCacheValue,Field> type2 =  iterator2.next();
				OrgCacheValue orgCacheValue=type2.getKey();
				Field orgCacheValueField=type2.getValue();
				Object orgCacheValueFieldValue=cacheBaseMirror.getValue(baseCache, orgCacheValue.value());
				elemirror.setValue(ele, orgCacheValueField, orgCacheValueFieldValue);
				
			}
			
			
		}
		
		
	}

}
