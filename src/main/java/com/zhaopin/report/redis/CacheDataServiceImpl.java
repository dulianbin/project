package com.zhaopin.report.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value= "redisCacheDataService")
public class CacheDataServiceImpl implements RedisCacheDataService {
	
	private static final Logger logger = Logger.getLogger(CacheDataServiceImpl.class);
	
	@Autowired
	private RedisService redisService;
	
	
	

}
