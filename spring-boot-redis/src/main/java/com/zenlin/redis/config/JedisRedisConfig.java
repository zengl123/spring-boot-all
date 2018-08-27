package com.zenlin.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.web.war.config
 * @ClassName JedisRedisConfig
 * @Author ZENLIN
 * @Date 2018-06-29 21:57
 * @Description TODO
 * @Version
 * @Modified By
 */
@Configuration
@PropertySource(value = "classpath:redis.properties")
public class JedisRedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.database}")
    private int database;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.timeout}")
    private int timeout;


    @Bean(name = "jedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory() {
        return getJedisConnectionFactory(host, port, database, password, timeout);
    }

    public JedisConnectionFactory getJedisConnectionFactory(String host, int port, int database, String password, int timeout) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofMillis(timeout));
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
    }

}
