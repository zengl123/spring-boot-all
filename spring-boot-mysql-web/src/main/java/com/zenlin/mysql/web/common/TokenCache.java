package com.zenlin.mysql.web.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.mysql.web.common
 * @ClassName TokenCache
 * @Author ZENLIN
 * @Date 2018-09-06 0:00
 * @Description TODO
 * @Version
 * @Modified By
 */
public class TokenCache {

    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    public static final String TOKEN_PREFIX = "token_";

    /**
     * LRU算法
     */
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<>() {
                //默认的数据加载实现,当调用get取值的时候,如果key没有对应的值,就调用这个方法进行加载.
                @Override
                public String load(String s) {
                    return null;
                }
            });

    public static void setKey(String key, String value) {
        localCache.put(key, value);
    }

    public static String getKey(String key) {
        try {
            return localCache.get(key);
        } catch (Exception e) {
            logger.error("localCache get error:{}", e.getMessage());
            return null;
        }
    }
}
