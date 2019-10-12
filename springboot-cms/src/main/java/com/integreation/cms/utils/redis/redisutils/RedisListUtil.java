package com.integreation.cms.utils.redis.redisutils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ZhangGang on 2018/11/24.
 */
@Slf4j
@Component
public class RedisListUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private final String prefix = "LIST:";

    /**
     * 插入一条内存表数据尾部(定长队列)
     *
     * @param table
     * @param object
     * @return
     */
    public <T> Long fixedPush(String table, T object, long fixedLength) {
        Long ret = redisTemplate.opsForList().rightPush(prefix + table, object);
        Long nowLength = size(table);
        if (nowLength > fixedLength) {
            redisTemplate.opsForList().trim(prefix + table, nowLength - fixedLength, -1);
        }
        return ret;
    }

    /**
     * @param table redis表名
     * @return List<T> 数据集合
     * @Description 功能：获取 list 所有元素(0 表示第一个, -1 表示所有)
     **/
    public <T> List<T> getTable(String table) {
        try {
            return (List<T>) redisTemplate.opsForList().range(prefix + table, 0L, -1L);
        } catch (Exception var3) {
            log.error(var3.getMessage());
            return null;
        }
    }

    /**
     * @param table  redis表名
     * @param object value
     * @return Long
     * @Description 功能：从左开始添加数据到table
     **/
    public <T> Long pushLeft(String table, T object) {
        try {
            return redisTemplate.opsForList().leftPush(prefix + table, object);
        } catch (Exception var4) {
            log.error(var4.getMessage());
        }
        return 0L;
    }

    /**
     * @param table redis表名
     * @return T 数据
     * @Description 功能：从最左开始获取table数据
     **/
    public <T> T popByLeft(String table) {
        try {
            return (T) redisTemplate.opsForList().leftPop(prefix + table);
        } catch (Exception var4) {
            log.error(var4.getMessage());
        }
        return null;

    }

    /**
     * @param table  redis表名
     * @param object value
     * @return Long
     * @Description 功能：从右开始添加数据到table
     **/
    public <T> Long pushRight(String table, T object) {
        try {
            return redisTemplate.opsForList().rightPush(prefix + table, object);
        } catch (Exception var4) {
            log.error(var4.getMessage());
        }
        return 0L;
    }

    /**
     * @param table redis表名
     * @return boolean
     * @Description 功能：删除LIST 下的table的最后一条数据（即最右的一条数据）
     **/
    public <T> T popByRight(String table) {
        try {
            return (T) redisTemplate.opsForList().leftPop(prefix + table);
        } catch (Exception var4) {
            log.error(var4.getMessage());
        }
        return null;
    }

    /**
     * @param table redis表名
     * @param n     条数
     * @return List<T> 数据集合
     * @Description 功能：从最左开始获取table数据  ，n条
     **/
    public <T> List<T> getListAndDelete(String table, long n) {
        try {
            ArrayList ex = new ArrayList();
            for (int i = 0; (long) i < n; ++i) {
                Object obj = redisTemplate.opsForList().leftPop(prefix + table);
                if (obj == null) {
                    break;
                }
                ex.add(obj);
            }
            return ex;
        } catch (Exception var7) {
            log.error(var7.getMessage());
            return null;
        }
    }

    /**
     * @param table      redis表名
     * @param objectList value集合
     * @return Long 集合长度
     * @Description 功能：从右开始添加List数据到table
     **/
    public <T> Long setRightList(String table, List<T> objectList) {
        long num = 0L;

        try {
            for (Iterator var6 = objectList.iterator(); var6.hasNext(); ++num) {
                Object ex = (Object) var6.next();
                redisTemplate.opsForList().rightPush(prefix + table, ex);
            }
        } catch (Exception var7) {
            log.error(var7.getMessage());
        }
        return num;
    }

    /**
     * @param table redis表名
     * @return boolean
     * @Description 功能：删除LIST 下的table 数据
     **/
    public boolean deleteTable(String table) {
        boolean result = false;
        try {
            redisTemplate.delete(prefix + table);
            result = true;
        } catch (Exception var4) {
            log.error(var4.getMessage());
        }
        return result;
    }

    /**
     * @param table redis表名
     * @return Long  数据集大小
     * @Description 功能：LIST 下的table 数据集大小
     **/
    public Long size(String table) {

        return redisTemplate.opsForList().size(prefix + table);

    }

    /**
     * @param pattern redis关键字
     * @return Set<String> key值集合
     * @Description 功能：获取pattern 模板下的keys集合
     **/
    public Set<String> tables(String pattern) {

        return redisTemplate.keys(prefix + pattern);


    }

    /**
     * @param pattern redis关键字
     * @return Set<String> key值集合
     * @Description 功能：获取pattern 模板下的keys集合
     **/
    public Set<String> tablesByPattern(String pattern) {

        return redisTemplate.keys(pattern);


    }


    /*
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。
     * count> 0：删除等于从左到右移动的值的第一个元素；
     * count< 0：删除等于从右到左移动的值的第一个元素；
     * count = 0：删除等于value的所有元素。
     * */

    public long removeCount(String key, Object value) {
        if (StringUtils.isNotBlank(key)) {
            return redisTemplate.opsForList().remove(prefix + key, 1, value);
        } else {
            return 0;
        }
    }
}
