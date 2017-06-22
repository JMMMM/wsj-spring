package com.wsj.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
/**
 * Created by jimmy on 2017/6/22.
 */
@Configuration
@EnableRedisHttpSession
public class RedisConfig {
}
