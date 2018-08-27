package com.zenlin.web.war.service.impl;

import com.zenlin.redis.utils.RedisUtil;
import com.zenlin.web.war.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.web.war.service.impl
 * @ClassName RedisImpl
 * @Author ZENLIN
 * @Date 2018-07-03 21:32
 * @Description TODO
 * @Version
 * @Modified By
 */
@Service
public class RedisImpl implements RedisService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void set(String key, Object value) {
        redisUtil.set(key, value);
    }
}
