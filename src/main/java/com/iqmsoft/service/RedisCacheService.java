package com.iqmsoft.service;


public interface RedisCacheService {
    boolean putSessionObject(String id, Object sessionObject);
    boolean clearSessionObject(String id);
    Object getSessionObject(String id);
}
