package com.aak1247.common;

import java.util.List;

public abstract class AbstractDBService {
    public abstract boolean insert(String key, com.aak1247.todos.entity.Todo value);

    public abstract boolean remove(String key, com.aak1247.todos.entity.Todo value);

    public abstract boolean update(String key, Object value);

    public abstract boolean save(String key, Object value);

    public abstract Object find(String key);

    public abstract List<Object> findAll();

    public abstract Long size();
}
