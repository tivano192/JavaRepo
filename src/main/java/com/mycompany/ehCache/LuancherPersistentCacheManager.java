package com.mycompany.ehCache;

import org.ehcache.Cache;
import org.ehcache.CachePersistenceException;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

import java.io.File;
import java.util.stream.IntStream;

public class LuancherPersistentCacheManager {

    private static PersistentCacheManager persistentCacheManager = null;

    private static String EHCACHE_CONFIG_PATH = "C:\\Users\\loune\\OneDrive\\Bureau\\workspace\\cache_local";
    private static String CACHE_NAME = "myCache";
    private static String CACHE_NAME_2 = "myCache2";

    public static void main(String[] args) throws CachePersistenceException {
        destroyCache();
        buildPersistenceStorageCache(Integer.class, String.class);

        buildPersistentMyCache(CACHE_NAME);
        buildPersistentMyCache(CACHE_NAME_2);

        whenAccessPersistentMyCache(CACHE_NAME);
        whenAccessPersistentMyCache(CACHE_NAME_2);
        persistentCacheManager.close();
    }


    public static void buildPersistenceStorageCache(Class<?> ketType, Class<?> valueType) throws CachePersistenceException {
        persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(new File(EHCACHE_CONFIG_PATH, "myData")))
                .build(true);
        persistentCacheManager.createCache(CACHE_NAME,
                CacheConfigurationBuilder.newCacheConfigurationBuilder(ketType, valueType,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(10, EntryUnit.ENTRIES)
                                .offheap(10, MemoryUnit.MB)
                                .disk(1, MemoryUnit.GB, true)
                ));
        persistentCacheManager.createCache(CACHE_NAME_2, CacheConfigurationBuilder.newCacheConfigurationBuilder(ketType, valueType,
                ResourcePoolsBuilder.newResourcePoolsBuilder()
                        .heap(10, EntryUnit.ENTRIES)
                        .offheap(100, MemoryUnit.MB)
                        .disk(1, MemoryUnit.GB, true)
        ));
    }

    public static void destroyCache() {
        try {
            persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                    .with(CacheManagerBuilder.persistence(new File(EHCACHE_CONFIG_PATH, "myData")))
                    .build(true);
            persistentCacheManager.destroyCache(CACHE_NAME);
            persistentCacheManager.destroyCache(CACHE_NAME_2);
            persistentCacheManager.close();
        } catch (CachePersistenceException e) {
        }
    }

    public static void buildPersistentMyCache(String cacheName) {
        Cache myCache = persistentCacheManager.getCache(cacheName, Integer.class, String.class);
        IntStream.range(1, 5000).boxed().forEach((i) -> {
            myCache.put(i, cacheName+ "-" + i);
        });
    }

    public static void whenAccessPersistentMyCache(String cacheName) {
        Cache myCache = persistentCacheManager.getCache(cacheName, Integer.class, String.class);
        int i = 1;
        while (i < 100) {
            System.out.println(myCache.get(i));
            i++;
        }
    }
}
