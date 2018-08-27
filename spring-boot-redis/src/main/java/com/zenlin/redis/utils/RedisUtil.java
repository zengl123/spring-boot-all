package com.zenlin.redis.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.redis.utils
 * @ClassName RedisUtil
 * @Author ZENLIN
 * @Date 2018-07-03 21:43
 * @Description TODO
 * @Version
 * @Modified By
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object object) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, object);
    }

    public Object get(String key) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        return opsForValue.get(key);
    }

    public String getString(String key) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        Object value = opsForValue.get(key);
        return String.valueOf(value);
    }

    public Map getMap(String key) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        Object value = opsForValue.get(key);
        return (Map) value;
    }

    public JSONObject getJSONOnject(String key) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        Object value = opsForValue.get(key);
        return (JSONObject) value;
    }

    public Collection getCollection(String key) {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        Object value = opsForValue.get(key);
        return (Collection) value;
    }

    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public void remove() {

    }
}
