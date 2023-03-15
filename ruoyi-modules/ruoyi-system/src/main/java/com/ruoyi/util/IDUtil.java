package com.ruoyi.util;

import java.util.Random;

/**
 * 自动生成ID的工具类
 * @author Condy
 *
 */
public class IDUtil {
	static private SnowflakeIdWorker id;
	static {
		Random random=new Random();
		int workId=random.nextInt(32);
		int datacenterId=random.nextInt(32);
		id=new SnowflakeIdWorker(workId,datacenterId);
	}
	/**
	 * 获取18位的唯一主键
	 * @return
	 */
	static public  long getId(){
		try {
			return id.nextId();
		}catch(RuntimeException e) {
			//时间被调整回去了，可能是时间同步引起的
			if(e.toString().contains("Clock moved backwards")) {
				try {Thread.sleep(50);} catch (InterruptedException e1) {}
				return id.nextId();
			} else {
				throw e;
			}
		}
		
	}
	
	/**
	 * 返回18位字符串的唯一ID
	 * @return
	 */
	static public  String getStrId(){
		return id.nextId()+"";
	}

}
