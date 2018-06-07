package com.zenlin.es.common.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.es.common.config
 * @ClassName ElasticsearchConfig
 * @Author ZENLIN
 * @Date 2018-06-07 21:53
 * @Description TODO
 * @Version
 * @Modified By
 */
@Configuration
public class ElasticsearchRestHighConfig extends AbstractFactoryBean<RestHighLevelClient> {
    @Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private int port;
    @Value("${elasticsearch.schema}")
    private String schema;
    @Value("${elasticsearch.connectTimeOut}")
    private int connectTimeOut;
    @Value("${elasticsearch.socketTimeOut}")
    private int socketTimeOut;
    @Value("${elasticsearch.connectionRequestTimeOut}")
    private int connectionRequestTimeOut;
    @Value("${elasticsearch.maxConnectNum}")
    private int maxConnectNum;
    @Value("${elasticsearch.maxConnectPerRoute}")
    private int maxConnectPerRoute;
    @Value("${elasticsearch.uniqueConnectTimeConfig}")
    private boolean uniqueConnectTimeConfig;
    @Value("${elasticsearch.uniqueConnectNumConfig}")
    private boolean uniqueConnectNumConfig;

    private RestClientBuilder builder;
    private RestHighLevelClient client;


    @Bean
    public RestHighLevelClient RestHighLevelClient() {
        builder = RestClient.builder(new HttpHost(host, port, schema));
        if (uniqueConnectTimeConfig) {
            setConnectTimeOutConfig();
        }
        if (uniqueConnectNumConfig) {
            setMultipleConnectConfig();
        }
        client = new RestHighLevelClient(builder);
        return client;
    }

    /**
     * 主要关于异步httpclient的连接延时配置
     */
    public void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeOut);
            requestConfigBuilder.setSocketTimeout(socketTimeOut);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
            return requestConfigBuilder;
        });
    }

    /**
     * 主要关于异步httpclient的连接数配置
     */
    public void setMultipleConnectConfig() {
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            return httpClientBuilder;
        });
    }

    @Override
    public Class<?> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    protected RestHighLevelClient createInstance() {
        return RestHighLevelClient();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void destroy() {
        try {
            if (client != null) {
                client.close();
            }
        } catch (final Exception e) {
            logger.error("Error closing ElasticSearch client: ", e);
        }
    }
}
