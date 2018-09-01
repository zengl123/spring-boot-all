package com.zenlin.mysql.web.dao;

import com.zenlin.mysql.web.common.ServerResponse;
import com.zenlin.mysql.web.domain.pojo.EcUser;
import org.mybatis.generator.api.dom.java.BaseMapper;


/**
 * @author zengling
 * @description
 * @date 2018/08/27 23:43
 */
public interface EcUserMapper extends BaseMapper<EcUser> {
    /**
     * 条件查询
     * @return
     */
    EcUser findByParams();
}