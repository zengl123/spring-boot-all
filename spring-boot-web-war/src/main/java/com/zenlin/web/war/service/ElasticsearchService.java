package com.zenlin.web.war.service;

import com.zenlin.common.entity.RestMessage;
import com.zenlin.es.common.entity.ElasticsearchEntity;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.web.war.service
 * @ClassName ElasticsearchService
 * @Author ZENLIN
 * @Date 2018-07-05 22:34
 * @Description TODO
 * @Version
 * @Modified By
 */
public interface ElasticsearchService {
    RestMessage insert(ElasticsearchEntity entity);
    RestMessage insertBatch(ElasticsearchEntity entity);
}
