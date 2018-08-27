package com.zenlin.es.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.zenlin.common.entity.RestMessage;
import com.zenlin.common.utils.ResultUtil;
import com.zenlin.es.common.entity.ElasticsearchEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.assertj.core.util.Preconditions;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

@Component
public class ElasticsearchUtil<T> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    static ObjectMapper objectMapper = new ObjectMapper();

    public ElasticsearchUtil() {
        restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
    }

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    public RestMessage insert(ElasticsearchEntity entity) {
        String index = entity.getIndex();
        String type = entity.getType();
        String id = entity.getId();
        Object data = entity.getData();
        data = JSON.toJSON(data);
        Preconditions.checkArgument(StringUtils.isNotEmpty(index), " index is empty");
        Preconditions.checkArgument(StringUtils.isNotEmpty(type), " type is empty");
        JsonElement requestData = new Gson().toJsonTree(data);
        if (null != requestData && !requestData.isJsonNull() && !requestData.isJsonPrimitive() && !requestData.isJsonArray()) {
            try {
                byte[] bytes = objectMapper.writeValueAsBytes(data);
                IndexRequest indexRequest = new IndexRequest(index, type, id);
                indexRequest.source(bytes, XContentType.JSON);
                try {
                    IndexResponse indexResponse = restHighLevelClient.index(indexRequest);
                    return ResultUtil.success(indexResponse.getId());
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

    /**
     * 批量新增
     *
     * @param entity
     * @return
     */
    public RestMessage insertBatch(ElasticsearchEntity entity) {
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
                BulkResponse bulkResponse = restHighLevelClient.bulk(request);
                BulkItemResponse[] items = bulkResponse.getItems();
                List<String> collect = Arrays.stream(items).map(object -> object.getId()).collect(Collectors.toList());
                return ResultUtil.success(collect);
            } catch (IOException e) {
                throw new ElasticsearchException(e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("请求参数必须是Array结构数据体");
        }
    }

    public boolean exists(String index, String type, String id) {
        GetRequest getRequest = new GetRequest(index, type, id);
        try {
            return restHighLevelClient.exists(getRequest);
        } catch (IOException e) {
            throw new ElasticsearchException(e.getMessage());
        }
    }

    /**
     * @return
     */
    public RestMessage deleteBatch() {
        return null;
    }

    public RestMessage deleteById() {
        return null;
    }

    /**
     * 根据索引删除索引
     *
     * @param index
     * @return
     */
    public RestMessage deleteByIndex(String index) {
        IndicesClient indices = restHighLevelClient.indices();
        DeleteIndexRequest deleteRequest = new DeleteIndexRequest();
        deleteRequest.indices(index);
        try {
            DeleteIndexResponse deleteResponse = indices.delete(deleteRequest);
            return ResultUtil.success(deleteResponse.isAcknowledged());
        } catch (ElasticsearchStatusException e) {
            return ResultUtil.error("index不存在");
        } catch (IOException e) {
            return ResultUtil.error();
        }
    }

    /**
     * 根据id删除文档
     *
     * @param id
     * @return
     */
    public RestMessage deleteById(String index, String type, String id) {
        boolean exists = exists(index, type, id);
        if (exists) {
            try {
                DeleteRequest request = new DeleteRequest(index, type, id);
                DeleteResponse deleteResponse = restHighLevelClient.delete(request);
                return ResultUtil.success("删除成功", deleteResponse.getId());
            } catch (IOException e) {
                throw new ElasticsearchException(e.getMessage());
            }
        } else {
            return ResultUtil.error("id不存在");
        }
    }

    private String index = ".monitoring-es-6-2018.07.09";
    private String type = "doc";

    @Test
    public void deleteByIdTest() {
        String id = "tuTCYl4wSAyTbe7dQzQBXg:fuEcp2enTT-5_Cge-NvJjA:camera:1:p";
        RestMessage restMessage = deleteById(index, type, id);
        System.out.println("restMessage = " + restMessage);
    }
}

