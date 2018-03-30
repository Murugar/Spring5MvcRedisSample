package com.iqmsoft.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqmsoft.dao.RedisCacheDao;


@Service
public class RedisCacheServiceImpl implements RedisCacheService {
    private static final Logger logger = Logger.getLogger(RedisCacheServiceImpl.class);

    @Autowired
    private RedisCacheDao redisCacheDao;

   
    public boolean putSessionObject(String id, Object sessionObject) {
        boolean flag = false;
        if (sessionObject != null && id != null && id.trim().length()>0) {
            flag = redisCacheDao.saveObject(id, sessionObject);
           
        } else {
            
        }
        return flag;
    }

    
    public boolean clearSessionObject(String id) {
        boolean flag = false;
        if (id != null && id.trim().length() > 0) {
            flag = redisCacheDao.deleteKey(id);
            
        } else {
            
        }
        return flag;
    }

   
    public Object getSessionObject(String id) {
        Object sessionObject = null;
        if (id != null && id.trim().length() > 0) {
            sessionObject = redisCacheDao.getObject(id);
        } else {
            logger.info("参数含有空值或为Null，获取缓存失败");
        }
        return sessionObject;
    }
}
