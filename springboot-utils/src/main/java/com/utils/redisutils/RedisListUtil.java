package com.utils.redisutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ZhangGang on 2018/11/24.
 */
@Component
public class RedisListUtil {
    private RedisTemplate<String,Object> redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RedisListUtil.class);
    private String fixedLengthKey = "RedisFixedLength";
    private final String prefix = "LIST:";

    public void setFixedLength(String table, long len) {
        redisTemplate.opsForHash().put(fixedLengthKey, table, len);
    }

    public long getFixedLength(String table) {
        Object o = null;

        try {
            o = redisTemplate.opsForHash().get(fixedLengthKey, table);
            return (Long) o;
        } catch (Exception var4) {
            logger.error(var4.getMessage());
            return 0L;
        }
    }

    public <T> List<T> getTable(String table) {
        try {
            return (List<T>) redisTemplate.opsForList().range("LIST:" + table, 0L, -1L);
        } catch (Exception var3) {
            logger.error(var3.getMessage());
            return null;
        }
    }

    public <T> T getAndDelete(String table) {
        return (T) redisTemplate.opsForList().leftPop("LIST:" + table);
    }

    public <T> List<T> getListAndDelete(String table, long n) {
        try {
            ArrayList ex = new ArrayList();

            for(int i = 0; (long)i < n; ++i) {
                Object obj = redisTemplate.opsForList().leftPop("LIST:" + table);
                if(obj == null) {
                    break;
                }

                ex.add(obj);
            }

            return ex;
        } catch (Exception var7) {
            logger.error(var7.getMessage());
            return null;
        }
    }

    public <T> Long set(String table, T object) {
        try {
            return redisTemplate.opsForList().rightPush("LIST:" + table, object);
        } catch (Exception var4) {
            logger.error(var4.getMessage());
            return 0L;
        }
    }

    public <T> Long setList(String table, List<T> objectList) {
        long num = 0L;

        try {
            for(Iterator var6 = objectList.iterator(); var6.hasNext(); ++num) {
                Object ex = (Object)var6.next();
                redisTemplate.opsForList().rightPush("LIST:" + table, ex);
            }
        } catch (Exception var7) {
            logger.error(var7.getMessage());
        }

        return num;
    }

    public <T> Long fixedPush(String table, T object) {
        try {
            long ex = getFixedLength(table);
            if(ex > 0L) {
                return fixedPush(table, object, ex);
            } else {
                logger.error("未设置队列长度");
                return 0L;
            }
        } catch (Exception var5) {
            logger.error(var5.getMessage());
            return 0L;
        }
    }

    public  <T> Long fixedPush(String table, T object, long fixedLength) {
        Long ret = redisTemplate.opsForList().rightPush("LIST:" + table, object);
        Long nowLength = size(table);
        if(nowLength > fixedLength) {
            redisTemplate.opsForList().trim("LIST:" + table, nowLength - fixedLength, -1L);
        }

        return ret;
    }

    public boolean deleteLastItem(String table) {
        boolean result = false;

        try {
            redisTemplate.opsForList().rightPop("LIST:" + table);
            result = true;
        } catch (Exception var4) {
            logger.error(var4.getMessage());
        }

        return result;
    }

    public boolean deleteTable(String table) {
        boolean result = false;

        try {
            redisTemplate.delete("LIST:" + table);
            result = true;
        } catch (Exception var4) {
            logger.error(var4.getMessage());
        }

        return result;
    }

    public Long size(String table) {
        return redisTemplate.opsForList().size("LIST:" + table);
    }

    public Set<String> tables(String pattern) {
        return redisTemplate.keys("LIST:" + pattern);
    }
}
