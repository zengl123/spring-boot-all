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
    /**
     * 登陆
     *
     * @param userName
     * @param password
     * @return
     */
    ServerResponse login(String userName, String password);

    /**
     * 注册
     *
     * @param ecUser
     * @return
     */
    ServerResponse register(EcUser ecUser);

    /**
     * 数据校验
     *
     * @param type
     * @param value
     * @return
     */
    ServerResponse checkValid(String type, String value);

    /**
     * 获取用户问题信息
     *
     * @param userName
     * @return
     */
    ServerResponse selectQuestion(String userName);

    ServerResponse checkAnswer(String userName, String question, String answer);
}
