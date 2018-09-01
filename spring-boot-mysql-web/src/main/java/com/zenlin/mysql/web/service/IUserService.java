package com.zenlin.mysql.web.service;

import com.zenlin.mysql.web.common.ServerResponse;
import com.zenlin.mysql.web.domain.pojo.EcUser;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.mysql.web.service
 * @ClassName IUserService
 * @Author ZENLIN
 * @Date 2018-08-27 21:44
 * @Description TODO
 * @Version
 * @Modified By
 */
public interface IUserService {
    ServerResponse insert(EcUser ecUser);
}
