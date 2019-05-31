package com.utils.redisutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZhangGang on 2018/11/24.
 */
@Component
public class RedisMapUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RedisMapUtil.class);

    public <T> boolean setByKey(String table, String key, T object) {
        boolean result = false;

        try {
            redisTemplate.opsForHash().put(table, key, object);
            result = true;
        } catch (Exception var6) {
            var6.printStackTrace();
            logger.error(var6.getMessage());
        }

        return result;
    }

    public <T> T getByKey(String table, String key) {
        try {
            return (T) redisTemplate.opsForHash().get(table, key);
        } catch (Exception var4) {
            logger.error(var4.getMessage());
            return null;
        }
    }

    public Boolean hasKey(String table, String key) {
        try {
            return redisTemplate.opsForHash().hasKey(table, key);
        } catch (Exception var4) {
            logger.error(var4.getMessage());
            return null;
        }
    }

    public <T> boolean batchSetList(String table, List<T> objectList, String keyName) {
        boolean result = false;

        try {
            HashMap ex = new HashMap();

            for (T anObjectList : objectList) {
                Object pojoObj = anObjectList;
                String key = null;

                try {
                    key = pojoObj.getClass().getMethod(keyName, new Class[0]).invoke(pojoObj, new Object[0]).toString();
                } catch (Exception var10) {
                    var10.getStackTrace();
                }

                ex.put(key, pojoObj);
            }

            batchSetMap(table, ex);
            result = true;
        } catch (Exception var11) {
            logger.error(var11.getMessage());
        }

        return result;
    }

    public <T> boolean batchSetMap(String table, HashMap<String, T> objectMap) {
        boolean result = false;

        try {
            redisTemplate.opsForHash().putAll(table, objectMap);
            result = true;
        } catch (Exception var5) {
            logger.error(var5.getMessage());
        }

        return result;
    }

    public <T> T getTable(String table) {
        try {
            return (T) redisTemplate.opsForHash().entries(table);
        } catch (Exception var3) {
            logger.error(var3.getMessage());
            return null;
        }
    }

    public <T> T getT(String table) {
        try {
            return (T) redisTemplate.opsForHash().values(table);
        } catch (Exception var3) {
            logger.error(var3.getMessage());
            return null;
        }
    }

    public <T> Set<T> getTableKeys(String table) {
        try {
            Set ex = redisTemplate.opsForHash().keys(table);
            return ex;
        } catch (Exception var3) {
            logger.error(var3.getMessage());
            return null;
        }
    }

    public boolean deleteByKey(String table, String key) {
        boolean result = false;

        try {
            redisTemplate.opsForHash().delete(table, new Object[]{key});
            result = true;
        } catch (Exception var5) {
            logger.error(var5.getMessage());
        }

        return result;
    }

    public Long setHashIncByKey(String table, String key) {
        Long result = null;

        try {
            result = redisTemplate.opsForHash().increment(table, key, 1L);
        } catch (Exception var5) {
            logger.error(var5.getMessage());
        }

        return result;
    }

    public Long setHashIncByKey(String table, String key, long n) {
        Long result = null;

        try {
            result = redisTemplate.opsForHash().increment(table, key, n);
        } catch (Exception var7) {
            logger.error(var7.getMessage());
        }

        return result;
    }

    public Double setHashIncByKey(String table, String key, double n) {
        Double result = null;

        try {
            result = redisTemplate.opsForHash().increment(table, key, n);
        } catch (Exception var7) {
            logger.error(var7.getMessage());
        }

        return result;
    }

    public Long getHashIncByKey(final String table, final String key) {
        try {
            return (Long)this.redisTemplate.execute(new RedisCallback() {
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    try {
                        RedisSerializer e = RedisMapUtil.this.redisTemplate.getStringSerializer();
                        RedisSerializer serialHashKey = RedisMapUtil.this.redisTemplate.getHashKeySerializer();
                        byte[] tableKey = e.serialize(table);
                        byte[] hasKey = serialHashKey.serialize(key);
                        byte[] value = connection.hGet(tableKey, hasKey);
                        String val = (String)e.deserialize(value);
                        return Long.valueOf(val);
                    } catch (Exception var8) {
                        return Long.valueOf(0L);
                    }
                }
            });
        } catch (Exception var4) {
            logger.error(var4.getMessage());
            return null;
        }
    }

    public <T> T setHashIncInit(final String table, final String key, final T n) {
        try {
            return this.redisTemplate.execute((RedisCallback<T>) new RedisCallback() {
                public T doInRedis(RedisConnection connection) throws DataAccessException {
                    RedisSerializer serialString = RedisMapUtil.this.redisTemplate.getStringSerializer();
                    RedisSerializer serialHashKey = RedisMapUtil.this.redisTemplate.getHashKeySerializer();
                    byte[] tableKey = serialString.serialize(table);
                    byte[] hasKey = serialHashKey.serialize(key);
                    long delNum = connection.hDel(tableKey, new byte[][]{hasKey}).longValue();
                    connection.hSet(tableKey, hasKey, serialString.serialize(String.valueOf(n)));
                    return n;
                }
            });
        } catch (Exception var5) {
            this.logger.error(var5.getMessage());
            return null;
        }
    }

    public <T> Integer setHashIncInitTable(final String table, final Map<String, T> incMap) {
        try {
            return (Integer)redisTemplate.execute(new RedisCallback() {
                public Integer doInRedis(RedisConnection connection) throws DataAccessException {
                    RedisSerializer serialString = RedisMapUtil.this.redisTemplate.getStringSerializer();
                    RedisSerializer serialHashKey = RedisMapUtil.this.redisTemplate.getHashKeySerializer();
                    byte[] tableKey = serialString.serialize(table);
                    Iterator var6 = incMap.entrySet().iterator();

                    while(var6.hasNext()) {
                        Map.Entry entry = (Map.Entry)var6.next();
                        String key = (String)entry.getKey();
                        Object value = entry.getValue();
                        byte[] hasKey = serialHashKey.serialize(key);
                        connection.hSet(tableKey, hasKey, serialString.serialize(String.valueOf(value)));
                    }

                    return Integer.valueOf(incMap.size());
                }
            });
        } catch (Exception var4) {
            logger.error(var4.getMessage());
            return null;
        }
    }

    public Map<String, ?> getHashIncTable(final String table) {
        try {
            return (Map)this.redisTemplate.execute(new RedisCallback() {
                public Map<String, ?> doInRedis(RedisConnection connection) throws DataAccessException {
                    RedisSerializer serialString = RedisMapUtil.this.redisTemplate.getStringSerializer();
                    RedisSerializer serialHashKey = RedisMapUtil.this.redisTemplate.getHashKeySerializer();
                    byte[] tableKey = serialString.serialize(table);
                    HashMap retMap = new HashMap();
                    Map mapAll = connection.hGetAll(tableKey);
                    Iterator var8 = mapAll.entrySet().iterator();

                    while(var8.hasNext()) {
                        Map.Entry entry = (Map.Entry)var8.next();
                        String hasKey = (String)serialHashKey.deserialize((byte[])entry.getKey());
                        Object value = serialString.deserialize((byte[])entry.getValue());
                        retMap.put(hasKey, value);
                    }

                    return retMap;
                }
            });
        } catch (Exception var3) {
            logger.error(var3.getMessage());
            return null;
        }
    }

    public boolean deleteTable(String table) {
        boolean result = false;

        try {
            redisTemplate.delete(table);
            result = true;
        } catch (Exception var4) {
            logger.error(var4.getMessage());
        }

        return result;
    }

    public Long size(String table) {
        try {
            return redisTemplate.opsForHash().size(table);
        } catch (Exception var3) {
            logger.error(var3.getMessage());
            return null;
        }
    }

    public boolean expire(String key, Long expireTime) {
        boolean result = false;

        try {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return result;
    }
}
