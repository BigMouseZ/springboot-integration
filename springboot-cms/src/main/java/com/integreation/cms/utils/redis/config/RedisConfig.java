package com.integreation.cms.utils.redis.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by ZhangGang on 2018/11/24.
 */
@Configuration
@EnableCaching //开启Springboot缓存，重要！！！
public class RedisConfig extends CachingConfigurerSupport {
    /**
     * RedisTemplate配置
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //使用LettuceConnectionFactory 代替 RedisConnectionFactory
        redisTemplate.setConnectionFactory(connectionFactory);
        //配置序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());//key序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());//value序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());// Hash key序列化
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());// Hash value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
