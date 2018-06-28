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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zenlin.es.common.entity.ElasticsearchEntity;

import com.zenlin.es.common.entity.camera.CameraGroup;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.assertj.core.util.Preconditions;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ElasticsearchUtil<T> {

    @Autowired
    private RestHighLevelClient highLevelClient;

    static ObjectMapper objectMapper = new ObjectMapper();

    public ElasticsearchUtil() {
        highLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
    }

    private String insert(ElasticsearchEntity entity) {
        String index = entity.getIndex();
        String type = entity.getType();
        String id = entity.getId();
        Object data = entity.getData();
        Preconditions.checkArgument(StringUtils.isNotEmpty(index), " index is empty");
        Preconditions.checkArgument(StringUtils.isNotEmpty(type), " type is empty");
        JsonElement requestData = new Gson().toJsonTree(data);
        if (null != requestData && !requestData.isJsonNull() && !requestData.isJsonPrimitive() && !requestData.isJsonArray()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                byte[] bytes = objectMapper.writeValueAsBytes(data);
                IndexRequest indexRequest = new IndexRequest(index, type, id);
                indexRequest.source(bytes, XContentType.JSON);
                try {
                    IndexResponse indexResponse = highLevelClient.index(indexRequest);
                    return indexResponse.getId();
                } catch (IOException e) {
                    throw new ElasticsearchException(e.getMessage());
                }
            } catch (JsonProcessingException e) {
                throw new ElasticsearchException(e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("请求参数必须是key/value结构数据体");
        }
    }

    private void insertBatch(ElasticsearchEntity entity) {
        String index = entity.getIndex();
        String type = entity.getType();
        String id = entity.getId();
        Object list = entity.getData();
        Preconditions.checkArgument(StringUtils.isNotEmpty(index), " index is empty");
        Preconditions.checkArgument(StringUtils.isNotEmpty(type), " type is empty");
        JsonElement requestData = new Gson().toJsonTree(list);
        if (requestData != null && !requestData.isJsonNull() && requestData.isJsonArray()) {
            BulkRequest request = new BulkRequest();
            JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
            for (Object o : jsonArray) {
                try {
                    IndexRequest indexRequest = new IndexRequest(index, type, id);
                    byte[] bytes = objectMapper.writeValueAsBytes(o);
                    IndexRequest source = indexRequest.source(bytes, XContentType.JSON);
                    request.add(source);
                } catch (JsonProcessingException e) {
                    throw new ElasticsearchException(e.getMessage());
                }
            }
            try {
                BulkResponse bulkResponse = highLevelClient.bulk(request);
            } catch (IOException e) {
                throw new ElasticsearchException(e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("请求参数必须是Array结构数据体");
        }
    }

    @Test
    public void testInsert() {
        ElasticsearchEntity entity = new ElasticsearchEntity();
        entity.setIndex("camera");
        entity.setType("cameraGroup");
        JSONObject object = new JSONObject();
        object.put("cameraGroupNo", "5");
        JSONObject object1 = new JSONObject();
        object1.put("cameraGroupNo", "6");
        List list = new ArrayList();
        list.add(object);
        list.add(object1);
        entity.setData(list);
        insertBatch(entity);
    }
}

