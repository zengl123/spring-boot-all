package com.zenlin.mysql.web.dao;

import java.util.List;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.mysql.web.dao
 * @ClassName BaseMapper
 * @Author ZENLIN
 * @Date 2018-08-28 0:43
 * @Description TODO
 * @Version
 * @Modified By
 */
public interface BaseMapper<M> {
    int insert(M model);

    int insertBatch(List<M> models);

    int deleteById(String id);

    int deleteByIds(List<String> ids);

    int update(M model);

    int updateBatch(List<M> models);

    M findById(String id);

    List<M> findByIds(List<String> ids);

    List<M> findAll();
}
