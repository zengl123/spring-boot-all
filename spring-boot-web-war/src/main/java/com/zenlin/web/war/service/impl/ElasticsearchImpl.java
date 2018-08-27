package com.zenlin.web.war.service.impl;

import com.zenlin.common.entity.RestMessage;
import com.zenlin.es.common.entity.ElasticsearchEntity;
import com.zenlin.es.common.utils.ElasticsearchUtil;
import com.zenlin.web.war.service.ElasticsearchService;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.web.war.service.impl
 * @ClassName ElasticsearchImpl
 * @Author ZENLIN
 * @Date 2018-07-05 22:34
 * @Description TODO
 * @Version
 * @Modified By
 */
public class ElasticsearchImpl implements ElasticsearchService {

    ElasticsearchUtil elasticsearchUtil = new ElasticsearchUtil();

    @Override
    public RestMessage insert(ElasticsearchEntity entity) {
        return elasticsearchUtil.insert(entity);
    }

    @Override
    public RestMessage insertBatch(ElasticsearchEntity entity) {
        return elasticsearchUtil.insertBatch(entity);
    }
}
