package com.mh.base.common.cache;

public class CacheManagerFactory {

	public static CacheManager getCacheManager() {
		return BuiltInMemoryCache.getInstance();
	}

}
