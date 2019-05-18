package com.aak1247.common;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DBServiceImpl extends AbstractDBService {
    private volatile long count = 0;
    private volatile ConcurrentHashMap<String, Object> cache;

    public DBServiceImpl() {
        cache = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized boolean insert(String key, Object value) {
        var res = cache.containsKey(key);
        if (!res) ++count;
        cache.putIfAbsent(key, value);
        return res;
    }

    @Override
    public synchronized boolean remove(String key, Object value) {
        var res = cache.containsKey(key);
        if (res) {
            --count;
            cache.remove(key);
        }
        return res;
    }

    @Override
    public synchronized boolean update(String key, Object value) {
        var res = cache.containsKey(key);
        if (res) {
            cache.put(key, value);
        }
        return res;
    }

    @Override
    public synchronized boolean save(String key, Object value) {
        if (!cache.containsKey(key)) count++;
        cache.put(key, value);
        return true;
    }

    @Override
    public Object find(String key) {
        return cache.get(key);
    }

    @Override
    public Long size() {
        return count;
    }

    @Override
    public List<Object> findAll() {
        return cache.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
