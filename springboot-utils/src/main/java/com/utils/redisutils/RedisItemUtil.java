package com.utils.redisutils;

/**
 * Created by ZhangGang on 2018/11/24.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZhangGang on 2018/6/3.
 */
@Component
public class RedisItemUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RedisItemUtil.class);

    /*
    * 判断key值是否从存在
    * */
    public  boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }
    /*
    *通过key直接获取value值
    * */
    public <T> T getByKey(String key) {
        try {
            return (T) redisTemplate.opsForValue().get(key);
        } catch (Exception var4) {
            logger.error(var4.getMessage());
        }
        return null;
    }
    /*
    * 直接通过key存储value
    * */
    public <T> boolean setByKey(String key, T value) {
        boolean result = false;

        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception var5) {
            var5.printStackTrace();
        }
        return result;
    }
    /*
    * 删除key
    * */
    public void deleteByKey(String key) {
        redisTemplate.delete(key);
    }
    /*
    * 删除key,支持多删除
    * */
    public void deleteByKeys(String... keys) {
        String[] var5 = keys;
        int var4 = keys.length;
        for(int var3 = 0; var3 < var4; ++var3) {
            String key = var5[var3];
            redisTemplate.delete(key);
        }
    }
    public void deleteByPattern(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        if(keys.size() > 0) {
            redisTemplate.delete(keys);
        }

    }

    public <T> boolean setByKey(String key, T value, long seconds) {
        boolean result = false;

        try {
            redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
            result = true;
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return result;
    }

    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public boolean expire(String key, long seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }
    public Long setIncrement(String key) {
        Long result = null;

        try {
            result = redisTemplate.opsForValue().increment(key, 1L);
        } catch (Exception var4) {
            logger.error(var4.getMessage());
        }

        return result;
    }

    public Long setIncrement(String key, long n) {
        Long result = null;

        try {
            result = redisTemplate.opsForValue().increment(key, n);
        } catch (Exception var6) {
            logger.error(var6.getMessage());
        }

        return result;
    }

    public Double setIncrement(String key, double n) {
        Double result = null;

        try {
            result = redisTemplate.opsForValue().increment(key, n);
        } catch (Exception var6) {
            logger.error(var6.getMessage());
        }

        return result;
    }

    public long getIncrement(final String key) {
        return ((Long)this.redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = RedisItemUtil.this.redisTemplate.getStringSerializer();
                byte[] rowkey = serializer.serialize(key);
                byte[] rowval = connection.get(rowkey);

                try {
                    String e = (String)serializer.deserialize(rowval);
                    return Long.valueOf(Long.parseLong(e));
                } catch (Exception var6) {
                    return Long.valueOf(0L);
                }
            }
        })).longValue();
    }
}