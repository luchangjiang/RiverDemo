package com.river.RedisDemo.controller;

import com.river.RedisDemo.config.CacheConfig;
import com.river.RedisDemo.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
*
* @ClassName   类名：DemoController 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2019年3月7日
* @author      创建人：oKong
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2019年3月7日   oKong   创建该类功能。
*
***********************************************************************
*</p>
*/
@RestController
@Slf4j
public class DemoController {
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/redis/{key}")
	@Cacheable(value = "redis",key="#key",cacheManager= CacheConfig.CacheManagerNames.REDIS_CACHE_MANAGER)
	public String cacheRedisTest(@PathVariable("key") String key) {
		log.info("redis,key={}", key);
		return key;
	}
	@RequestMapping("/ehcache/{key}")
	@Cacheable(value = "oKongCache",key="#key",cacheManager=CacheConfig.CacheManagerNames.EHCACHE_CACHE_MANAGER)
	public String cacheEhcacheTest(@PathVariable("key") String key) {
		log.info("ehcache,key={}", key);
		return key;
	}
	
	@RequestMapping("/default/{key}")
	@Cacheable(value = "default",key="#key")
	public String cacheDefaultTest(@PathVariable("key") String key) {
		log.info("default,key={}", key);
		return key;
	}

	@RequestMapping("/redisService/{value}")
	public String redisServiceTest(@PathVariable("value") String value) {
		redisService.set("lucj", value);
		return redisService.get("lucj").orElse("");
	}
}
