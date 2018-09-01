package com.zenlin.mysql.web.service.impl;

import com.zenlin.mysql.web.common.ServerResponse;
import com.zenlin.mysql.web.dao.EcUserMapper;
import com.zenlin.mysql.web.domain.pojo.EcUser;
import com.zenlin.mysql.web.service.IUserService;
import org.mybatis.generator.api.dom.java.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.mysql.web.service.impl
 * @ClassName UserServiceImpl
 * @Author ZENLIN
 * @Date 2018-08-27 21:45
 * @Description TODO
 * @Version
 * @Modified By
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {
    @Autowired
    private EcUserMapper mapper;

    @Override
    protected BaseMapper getMapper() {
        return mapper;
    }

    @Override
    public ServerResponse insert(EcUser ecUser) {
        return super.save(ecUser);
    }

}
