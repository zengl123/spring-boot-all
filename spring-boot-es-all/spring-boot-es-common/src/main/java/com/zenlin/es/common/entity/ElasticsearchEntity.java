package com.zenlin.es.common.entity;

import lombok.Data;


/**
 * @Project spring-boot-all
 * @Package com.zenlin.es.common.entity
 * @ClassName ElasticsearchEntity
 * @Author ZENLIN
 * @Date 2018-06-07 23:03
 * @Description TODO
 * @Version
 * @Modified By
 */
@Data
public class ElasticsearchEntity {
    private String id;
    private String index;
    private String type;
    private Object data;
}
