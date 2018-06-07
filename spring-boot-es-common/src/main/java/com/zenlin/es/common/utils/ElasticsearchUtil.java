package com.zenlin.es.common.utils;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.es.common.utils
 * @ClassName ElasticsearchUtil
 * @Author ZENLIN
 * @Date 2018-05-20 18:25
 * @Description TODO
 * @Version
 * @Modified By
 */

import com.google.gson.GsonBuilder;
import com.zenlin.es.common.entity.camera.CameraGroup;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ElasticsearchUtil<T> {
    @Autowired
    private JestClient jestClient;
    @Autowired
    private RestHighLevelClient highLevelClient;
    @Autowired
    private ElasticsearchTemplate template;

    public ElasticsearchUtil() {
        highLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
        template = new ElasticsearchTemplate(getConnection());
        jestClient = getClientConfig();
    }


    private TransportClient getConnection() {
        // 设置集群名称
        Settings settings = Settings.builder().put("cluster.name", "zenlin").build();
        // 创建client
        TransportClient client;
        try {
            client = new PreBuiltTransportClient(settings).addTransportAddresses(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (UnknownHostException e) {
            throw new ElasticsearchException(e.getMessage());
        }
        return client;
    }

    private JestClient getClientConfig() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("127.0.0.1")
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())
                .multiThreaded(true).readTimeout(10000)
                .build());
        return factory.getObject();
    }

    @Test
    public void test() {
//        boolean camera = template.createIndex("camera");
//        Assert.assertTrue(camera);
        boolean index = template.createIndex(CameraGroup.class);
        Assert.assertTrue(index);
    }
}
