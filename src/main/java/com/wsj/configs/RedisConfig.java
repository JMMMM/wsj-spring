package com.wsj.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by jimmy on 2017/6/22.
 */
@EnableRedisHttpSession
public class RedisConfig implements EnvironmentAware{
    @Autowired
    private Environment environment ;

    @Override
    public void setEnvironment(Environment environment) {

    }
    @Bean
    public JedisConnectionFactory initJedisFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(environment.getProperty("spring.redis.hostName","127.0.0.1"));
        jedisConnectionFactory.setPort(Integer.valueOf(environment.getProperty("spring.redis.port","6379")));
        jedisConnectionFactory.setTimeout(Integer.valueOf(environment.getProperty("spring.redis.timeout","15000")));
        jedisConnectionFactory.setPassword(environment.getProperty("spring.redis.password",""));
        jedisConnectionFactory.setDatabase(Integer.valueOf(environment.getProperty("spring.redis.database","0")));
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPoolConfig(initJedisPoolConfig());
        return jedisConnectionFactory;
    }

    @Bean
    public JedisPoolConfig initJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.valueOf(environment.getProperty("spring.redisPool.maxTotal","10")));
        jedisPoolConfig.setMaxIdle(Integer.valueOf(environment.getProperty("spring.redisPool.maxIdle","10")));
        jedisPoolConfig.setMinIdle(Integer.valueOf(environment.getProperty("spring.redisPool.minIdle","2")));
        jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(environment.getProperty("spring.redisPool.maxWaitMillis","15000")));
        jedisPoolConfig.setMinEvictableIdleTimeMillis(Integer.valueOf(environment.getProperty("spring.redisPool.minEvictableIdleTimeMillis","300000")));
        jedisPoolConfig.setNumTestsPerEvictionRun(Integer.valueOf(environment.getProperty("spring.redisPool.numTestsPerEvictionRun","3")));
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(Integer.valueOf(environment.getProperty("spring.redisPool.timeBetweenEvictionRunsMillis","60000")));
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestWhileIdle(true);
        return jedisPoolConfig;
    }

    @Bean
    public RedisTemplate initRedisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate initStringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(initJedisFactory());
        return stringRedisTemplate;
    }


}
