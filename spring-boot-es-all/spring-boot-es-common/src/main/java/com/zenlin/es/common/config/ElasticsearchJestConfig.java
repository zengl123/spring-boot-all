package com.zenlin.es.common.config;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.es.common.config
 * @ClassName ElasticsearchJestConfig
 * @Author ZENLIN
 * @Date 2018-06-07 23:16
 * @Description TODO
 * @Version
 * @Modified By
 */
@Configuration
public class ElasticsearchJestConfig {
    @Value("${spring.elasticsearch.jest.uris}")
    private String jestUrl;

    @Bean
    public JestClient getClientConfig() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(jestUrl)
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())
                .multiThreaded(true).readTimeout(10000)
                .build());
        return factory.getObject();
    }
}
