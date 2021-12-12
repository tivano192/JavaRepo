package com.mycompany.ehCache.model;

import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

public  enum CacheNameEnum {
    CACHE_NAME_1("CACHE_NAME_1", Integer.class, Person.class, 10, 10, 100),
    CACHE_NAME_2("CACHE_NAME_2", Integer.class, Person.class,10, 10, 100),
    // ...
    CACHE_NAME_3("CACHE_NAME_3", Integer.class, Person.class, 10, 10, 100);

    public final String name;
    public final Class<?> ketType;
    public final Class<?> valueType;
    public final Integer heap;
    public final Integer offheap;
    public final Integer disk;

    private CacheNameEnum(String name, Class<?> ketType, Class<?> valueType,
                          Integer heap, Integer offheap, Integer disk) {
        this.name = name;
        this.ketType = ketType;
        this.valueType = valueType;
        this.heap = heap;
        this.offheap = offheap;
        this.disk = disk;
    }
}
