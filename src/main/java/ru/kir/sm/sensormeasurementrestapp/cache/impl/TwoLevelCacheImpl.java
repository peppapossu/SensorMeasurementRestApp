package ru.kir.sm.sensormeasurementrestapp.cache.impl;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import ru.kir.sm.sensormeasurementrestapp.cache.TwoLevelCache;

import java.util.concurrent.Callable;

@RequiredArgsConstructor
public class TwoLevelCacheImpl implements TwoLevelCache {

    private final Cache localCache;
    private final Cache remoteCache;

    @Override
    public String getName() {
        return localCache.getName();
    }

    @Override
    public Object getNativeCache() {
        return remoteCache.getNativeCache();
    }

    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper value = localCache.get(key);
        if (value != null) {
            return value;
        }
        value = remoteCache.get(key);
        if (value != null) {
            localCache.put(key, value.get());
        }
        return value;
    }

    @Override
    public <T> T get(Object key, @Nullable Class<T> type) {
        T value = localCache.get(key, type);
        if (value != null) {
            return value;
        }
        value = remoteCache.get(key, type);
        if (value != null) {
            localCache.put(key, value);
        }
        return value;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void put(Object key, Object value) {
        localCache.put(key, value);
        remoteCache.put(key, value);
    }

    @Override
    public void evict(Object key) {
        localCache.evict(key);
        remoteCache.evict(key);
    }

    @Override
    public void clear() {
        localCache.clear();
        remoteCache.clear();
    }
}
