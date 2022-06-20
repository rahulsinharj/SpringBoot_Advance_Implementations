package com.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching														// to enable caching
public class BeanConfig extends CachingConfigurerSupport
{
	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager()				// This CacheManager is from EhCache - which serves as a configuration for our cache. 
	{
		// Below are 2 CACHES we have made available for us to make use of.
		
		CacheConfiguration tenSecondCache = new CacheConfiguration();
		tenSecondCache.setName("ten-second-cache");
		tenSecondCache.setMemoryStoreEvictionPolicy("LRU");			// for setting "Cache Replacement Policies"  ; "LRU" is bydefault ; We have more Provided MemoryStore Eviction Algorithms : eg- Least Recently Used (LRU) , Least Frequently Used (LFU), First In First Out (FIFO)  
		tenSecondCache.maxEntriesLocalHeap(1000); 			 		// Max num of cache entries you can store in local heap memory ; when you set it at the cache manager level - then a local pool will available to the caches under that cache manager. 
		tenSecondCache.setTimeToLiveSeconds(10);					// cache life span : eg. 10sec		
		
		
		CacheConfiguration twentySecondCache = new CacheConfiguration();		// This is the second CACHE that we have created in our Application, which my services are going to make use of. 
		twentySecondCache.setName("twenty-second-cache");
		twentySecondCache.setMemoryStoreEvictionPolicy("LRU");
		twentySecondCache.maxEntriesLocalHeap(1000);
		twentySecondCache.setTimeToLiveSeconds(20);
	
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(tenSecondCache);							// Adding all caches
		config.addCache(twentySecondCache);
		
		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	@Bean
	@Override
	public CacheManager cacheManager() {							// This CacheManager is from Spring Framework
		return new EhCacheCacheManager(ehCacheManager());
	}

}

/*	MORE READ ABOUT Cache Algorithms ::
 -------------------
  	https://en.wikipedia.org/wiki/Cache_replacement_policies
  	https://www.ehcache.org/documentation/2.8/apis/cache-eviction-algorithms.html
  	https://www.ehcache.org/documentation/2.7/configuration/cache-size.html
  
*/

