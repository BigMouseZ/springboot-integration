package com.integreation.cms.utils.redis.redisutils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by ZhangGang on 2018/11/24.
 */
@Slf4j
@Component
public class RedisMapUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param table  表名
     * @param key    key值
     * @param object value值
     * @return T
     * @Description 功能：通过table设置某key值的value
     */
    public <T> boolean setByKey(String table, String key, T object) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().put(table, key, object);
            result = true;
        } catch (Exception var6) {
            var6.printStackTrace();
            log.error(var6.getMessage());
        }
        return result;
    }

    /**
     * @param table 表名
     * @param key   key值
     * @return T
     * @Description 功能： 通过table获取其中key值的value
     */
    public <T> T getByKey(String table, String key) {
        try {
            if(StringUtils.isNoneBlank(key)){
                return (T) redisTemplate.opsForHash().get(table, key);
            }
        } catch (Exception var4) {
            log.error(var4.getMessage());
        }
        return null;
    }


    /**
     * @param table 表名
     * @param key   key值
     * @return boolean
     * @Description 功能： 某table中存在某key
     */
    public Boolean hasKey(String table, String key) {
        try {
            return redisTemplate.opsForHash().hasKey(table, key);
        } catch (Exception var4) {
            log.error(var4.getMessage());
        }
        return false;
    }

    /**
     * @param table      表名
     * @param objectList 数据集合
     * @param keyName    通过keyName获取不同的key值
     * @return boolean
     * @Description 功能： 批量设置某table数据
     */
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
            log.error(var11.getMessage());
        }
        return result;
    }

    /**
     * @param table     表名
     * @param objectMap map数据集合
     * @return Map<T> boolean
     * @Description 功能：批量设置某table数据
     */
    public <T> boolean batchSetMap(String table, HashMap<String, T> objectMap) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(table, objectMap);
            result = true;
        } catch (Exception var5) {
            log.error(var5.getMessage());
        }
        return result;
    }

    /**
     * @param table 表名
     * @return Map<T> 数据集
     * @Description 功能：获取Map数据集
     */
    public <T> T getTableMap(String table) {
        try {
            return (T) redisTemplate.opsForHash().entries(table);
        } catch (Exception var3) {
            log.error(var3.getMessage());
            return null;
        }
    }

    /**
     * @param table 表名
     * @return List<T> 数据集
     * @Description 功能：获取table 的List数据集
     */
    public <T> T getTableList(String table) {
        try {
            return (T) redisTemplate.opsForHash().values(table);
        } catch (Exception var3) {
            log.error(var3.getMessage());
            return null;
        }
    }

    /**
     * @param table 表名
     * @return Set<T>  keys集合
     * @Description 功能：获取table 的keys集合
     */
    public <T> Set<T> getTableKeys(String table) {
        try {
            Set ex = redisTemplate.opsForHash().keys(table);
            return ex;
        } catch (Exception var3) {
            log.error("",var3);
        }
        return null;
    }

    /**
     * @param table 表名
     * @param key   表中的key值
     * @return boolean
     * @Description 功能：删除table 的key数据
     */
    public boolean deleteByKey(String table, String key) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().delete(table, key);
            result = true;
        } catch (Exception var3) {
            log.error("",var3);
        }
        return result;
    }

    /**
     * @param table 表名
     * @return boolean
     * @Description 功能：删除table
     */
    public boolean deleteTable(String table) {
        boolean result = false;
        try {
            redisTemplate.delete(table);
            result = true;
        } catch (Exception var3) {
            log.error("",var3);
        }
        return result;
    }
    /**
     * @param pattern 模板 eg：DICTIONARIES:*
     * @Description 功能：获取pattern 模板下的tables集合进行删除
     **/
    public boolean deleteTablesByPattern(String pattern) {
        boolean result = false;
        try {
            Set<String> tables =  redisTemplate.keys(pattern);
            if(tables!=null){
                tables.forEach(one->{
                    redisTemplate.delete(one);
                });
            }
            result = true;
        } catch (Exception var3) {
            log.error("",var3);
        }
        return result;
    }
    /**
     * @param table 表名
     * @return Long 长度
     * @Description 功能：某个内存表包含数据条数
     */
    public Long size(String table) {
        try {
            return redisTemplate.opsForHash().size(table);
        } catch (Exception var3) {
            log.error("",var3);
        }
        return 0L;
    }
}
