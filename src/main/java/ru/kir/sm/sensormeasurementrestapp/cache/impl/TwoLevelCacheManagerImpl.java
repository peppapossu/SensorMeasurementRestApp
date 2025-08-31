package ru.kir.sm.sensormeasurementrestapp.cache.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import ru.kir.sm.sensormeasurementrestapp.cache.TwoLevelCache;
import ru.kir.sm.sensormeasurementrestapp.cache.TwoLevelCacheManager;

import java.util.Collection;

@RequiredArgsConstructor
public class TwoLevelCacheManagerImpl implements TwoLevelCacheManager {

    private final CacheManager localCacheManager;
    private final CacheManager remoteCacheManager;

    @Override
    public Cache getCache(String name) {
        Cache localCache = localCacheManager.getCache(name);
        Cache remoteCache = remoteCacheManager.getCache(name);
        return new TwoLevelCacheImpl(localCache, remoteCache);
    }

    @Override
    public Collection<String> getCacheNames() {
        return remoteCacheManager.getCacheNames();
    }
}