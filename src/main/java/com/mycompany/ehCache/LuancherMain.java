package com.mycompany.ehCache;

import org.ehcache.CachePersistenceException;
import com.mycompany.ehCache.model.CacheNameEnum;

import java.util.List;

public class LuancherMain {

    public static void main(String[] args) throws CachePersistenceException {
        TemplateEhCacheManager ehManger = TemplateEhCacheManager.newTemplateEhCacheManager()
                .withType(List.of(CacheNameEnum.CACHE_NAME_1, CacheNameEnum.CACHE_NAME_2))
                .build();

        ehManger.fill();
        ehManger.showAll();
    }
}
