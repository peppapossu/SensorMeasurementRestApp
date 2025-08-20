package ru.kir.sm.sensormeasurementrestapp.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;

@RequiredArgsConstructor
public class TwoLevelCacheManager implements CacheManager {

    private final CacheManager localCacheManager;
    private final CacheManager remoteCacheManager;

    @Override
    public Cache getCache(String name) {
        Cache localCache = localCacheManager.getCache(name);
        Cache remoteCache = remoteCacheManager.getCache(name);
        return new TwoLevelCache(localCache, remoteCache);
    }

    @Override
    public Collection<String> getCacheNames() {
        return remoteCacheManager.getCacheNames();
    }
}