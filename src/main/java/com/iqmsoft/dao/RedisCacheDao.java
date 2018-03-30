package com.iqmsoft.dao;

public interface RedisCacheDao {
    boolean saveObject(String key, Object value);
    Object getObject(String key);
    boolean deleteKey(String key);
}
