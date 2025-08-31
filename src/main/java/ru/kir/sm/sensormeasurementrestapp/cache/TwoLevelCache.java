package ru.kir.sm.sensormeasurementrestapp.cache;

import org.springframework.cache.Cache;

import java.util.concurrent.Callable;

public interface TwoLevelCache extends Cache {
    @Override
    default String getName() {
        return "";
    }

    @Override
    default Object getNativeCache() {
        return null;
    }

    @Override
    default ValueWrapper get(Object key) {
        return null;
    }

    @Override
    default <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    default <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    default void put(Object key, Object value) {

    }

    @Override
    default void evict(Object key) {

    }

    @Override
    default void clear() {

    }
}
