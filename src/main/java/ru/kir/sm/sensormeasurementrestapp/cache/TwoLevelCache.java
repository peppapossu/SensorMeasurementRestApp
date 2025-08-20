package ru.kir.sm.sensormeasurementrestapp.cache;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class TwoLevelCache implements Cache {

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
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public CompletableFuture<?> retrieve(Object key) {
        return Cache.super.retrieve(key);
    }

    @Override
    public <T> CompletableFuture<T> retrieve(Object key, Supplier<CompletableFuture<T>> valueLoader) {
        return Cache.super.retrieve(key, valueLoader);
    }

    @Override
    public void put(Object key, Object value) {
        localCache.put(key, value);
        remoteCache.put(key, value);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return Cache.super.putIfAbsent(key, value);
    }

    @Override
    public void evict(Object key) {
        localCache.evict(key);
        remoteCache.evict(key);
    }

    @Override
    public boolean evictIfPresent(Object key) {
        return Cache.super.evictIfPresent(key);
    }

    @Override
    public void clear() {
        localCache.clear();
        remoteCache.clear();
    }

    @Override
    public boolean invalidate() {
        return Cache.super.invalidate();
    }
}
