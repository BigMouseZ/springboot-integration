package com.integreation.cms.utils.redis.redisutils;

/**
 * Created by ZhangGang on 2018/11/24.
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZhangGang on 2018/6/3.
 */
@Slf4j
@Component
public class RedisItemUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param key redis关键字
     * @return boolean
     * @Description 功能：判断key值是否从存在
     **/
    public boolean exists(String key) {
        if(StringUtils.isNoneBlank(key)){
            return redisTemplate.hasKey(key);
        }
       return false;
    }

    /**
     * @param key redis关键字
     * @return Object
     * @Description 功能：通过key直接获取value值
     **/
    public <T> T getByKey(String key) {
        try {
            return (T) redisTemplate.opsForValue().get(key);
        } catch (Exception var4) {
            log.error(var4.getMessage());
        }
        return null;
    }

    /**
     * @param key   redis关键字
     * @param value redis值
     * @return boolean
     * @Description 功能：直接通过key存储value
     **/
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

    /**
     * @param key redis关键字
     * @return boolean
     * @Description 功能：删除key
     **/
    public void deleteByKey(String key) {
        redisTemplate.delete(key);
    }


    /**
     * @param keys redis关键字集合
     * @Description 功能：删除key,支持多删除
     **/
    public void deleteByKeys(String... keys) {
        String[] var5 = keys;
        int var4 = keys.length;
        for (int var3 = 0; var3 < var4; ++var3) {
            String key = var5[var3];
            redisTemplate.delete(key);
        }
    }

    /**
     * @param pattern redis模板值
     * @Description 功能：删除key,支持模板删除
     **/
    public void deleteByPattern(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * @param key     redis关键字
     * @param value   redis值
     * @param seconds 存在时长 单位秒
     * @return boolean
     * @Description 功能：直接通过key存储value，有时长
     **/
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

    /**
     * @param pattern redis关键字
     * @return Set<String> key值集合
     * @Description 功能：通过模板获取key集合
     **/
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);

    }


    /**
     * @param key     redis关键字
     * @param seconds 存在时长 单位秒
     * @return boolean
     * @Description 功能：给key值添加时长 or 更新缓存时间
     **/
    public boolean expire(String key, long seconds) {

        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);

    }

}