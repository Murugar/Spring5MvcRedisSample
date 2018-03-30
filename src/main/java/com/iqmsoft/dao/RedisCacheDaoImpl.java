package com.iqmsoft.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.iqmsoft.utils.SerializeUtil;


@Repository
public class RedisCacheDaoImpl implements RedisCacheDao {
    private static final Logger logger = Logger.getLogger(RedisCacheDaoImpl.class);

    static final byte[] ngHisByte = SerializeUtil.serialize("WANDA_NGHIS");

    @Autowired
    private RedisTemplate<?, ?> redisTemplate;

    
    public boolean saveObject(final String key, final Object value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
          
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] keyByte = SerializeUtil.serialize(key);
                byte[] valueByte = SerializeUtil.serialize(value);
                Boolean flag = false;
                if (redisConnection.hExists(ngHisByte, keyByte)) {
                   
                    redisConnection.hDel(ngHisByte, keyByte);
                }
                flag = redisConnection.hSet(ngHisByte, keyByte, valueByte);
                return flag;
            }
        });
        return result;
    }

   
    public Object getObject(final String key) {
        Object result = redisTemplate.execute(new RedisCallback<Object>() {
            
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Object returnObject = null;
                byte[] keyByte = SerializeUtil.serialize(key);
                if (redisConnection.hExists(ngHisByte, keyByte)) {
                   
                    returnObject = redisConnection.hGet(ngHisByte, keyByte);
                } else {
                    
                }
                return returnObject;
            }
        });
        return result;
    }

  
    public boolean deleteKey(final String key) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
           
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] keyByte = SerializeUtil.serialize(key);
                Boolean flag = false;
                if (redisConnection.hExists(ngHisByte, keyByte)) {
                    
                    flag = redisConnection.hDel(ngHisByte, keyByte);
                }
                return flag;
            }
        });
        return result;
    }
}
