package com.mycompany.ehCache;

import com.mycompany.ehCache.model.CacheNameEnum;
import com.mycompany.ehCache.model.Person;
import org.ehcache.Cache;
import org.ehcache.CachePersistenceException;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

import java.io.File;
import java.util.List;
import java.util.stream.IntStream;

public class TemplateEhCacheManager {

    private static String EHCACHE_CONFIG_PATH = "C:\\Users\\loune\\OneDrive\\Bureau\\workspace\\cache_local";

    private PersistentCacheManager persistentCacheManager = null;

    private List<CacheNameEnum> cacheNamesEnum = null;

    public static TemplateEhCacheManager newTemplateEhCacheManager() {
        return new TemplateEhCacheManager();
    }

    public TemplateEhCacheManager withType(List<CacheNameEnum> cachesType) {
        this.cacheNamesEnum = cachesType;
        return this;
    }

    public TemplateEhCacheManager build() throws CachePersistenceException {
        destroyCache();
        buildPersistentCacheManager();
        // build
        for (CacheNameEnum cacheNameEnum : this.cacheNamesEnum) {
            createCache(cacheNameEnum);
        }
        return this;
    }


    private PersistentCacheManager buildPersistentCacheManager() throws CachePersistenceException {
        persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(new File(EHCACHE_CONFIG_PATH, "myData")))
                .build(true);
        return persistentCacheManager;
    }

    public void createCache(CacheNameEnum cacheName) throws CachePersistenceException {
        getPersistentCacheManager().createCache(cacheName.name,
                CacheConfigurationBuilder.newCacheConfigurationBuilder(cacheName.ketType, cacheName.valueType,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(cacheName.heap, EntryUnit.ENTRIES)
                                .offheap(cacheName.offheap, MemoryUnit.MB)
                                .disk(cacheName.disk, MemoryUnit.MB, true)
                ));
    }

    public Cache getCache(CacheNameEnum cacheName) throws CachePersistenceException {
        return getPersistentCacheManager().getCache(cacheName.name, cacheName.ketType, cacheName.valueType);
    }

    public PersistentCacheManager getPersistentCacheManager() throws CachePersistenceException {
        if (persistentCacheManager == null) {
            buildPersistentCacheManager();
        }
        return persistentCacheManager;
    }

    public void destroyCache() {
        try {
            for (CacheNameEnum cacheNameEnum : this.cacheNamesEnum) {
                getPersistentCacheManager().destroyCache(cacheNameEnum.name);
            }
            getPersistentCacheManager().close();
        } catch (CachePersistenceException e) {
        }
    }

    private void fillPersistentMyCache(CacheNameEnum cacheName) throws CachePersistenceException {
        Cache myCache = getPersistentCacheManager().getCache(cacheName.name, cacheName.ketType, cacheName.valueType);
        IntStream.range(1, 2000000).boxed().forEach((i) -> {
            myCache.put(i, new Person(cacheName + "-" + i, cacheName + "- Fils " + i));
        });
    }

    private void whenAccessPersistentMyCache(CacheNameEnum cacheName) throws CachePersistenceException {
        Cache myCache = getPersistentCacheManager().getCache(cacheName.name, cacheName.ketType, cacheName.valueType);

        IntStream.range(1, 2000000).boxed().forEach((i) -> {
            System.out.println(myCache.get(i));
        });
    }

    public void fill() throws CachePersistenceException {
        for (CacheNameEnum cacheNameEnum : this.cacheNamesEnum) {
            fillPersistentMyCache(cacheNameEnum);
        }
    }

    public void showAll() throws CachePersistenceException {
        for (CacheNameEnum cacheNameEnum : this.cacheNamesEnum) {
            whenAccessPersistentMyCache(cacheNameEnum);
        }
    }
}
