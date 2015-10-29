package org.luckystars.unsafe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GuavaTests {
	
	private static final String key = "key";
	
	LoadingCache<String, String> cache = CacheBuilder.newBuilder()
			.expireAfterWrite(5L, TimeUnit.SECONDS)
			.build(new CacheLoader<String, String>() {
				public String load(String key) throws Exception {
					return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				};
			});
	
	@Test
	public void cacheTest1() throws  Exception{
		for(;;){
			System.out.println(cache.get(key));
			Thread.currentThread().sleep(1000L);
		}
	}
	
}