package com.zhaopin.report.init;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhaopin.report.redis.RedisService;

public class InitRedisData implements InitializingBean {
	private static final Logger logger = Logger.getLogger(InitRedisData.class);
	@Autowired
	private RedisService redisService;
	
 
/*	@Autowired
	private SysDictService sysDictService;*/
	
	public void afterPropertiesSet() throws Exception {
		logger.info("####开始初始化redis缓存数据---->");
//		PageData pd = new PageData();
//		List<SysDict> dictList = sysDictService.querySysDictList(pd);
//		for (SysDict sysDict : dictList) {
//			logger.info("放入数据库字典，key：" + sysDict.getDictCode() + "，value：" + sysDict.getDictValue());
//			redisService.setObject(sysDict.getDictCode(), sysDict);
//			
//			//TODO: 数据库字典系统参数内置参数检查
//		}
 
		
		logger.info("####初始化redis缓存数据结束---->");
	}

}
