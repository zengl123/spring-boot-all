package com.zenlin.mysql.web.dao;

import com.zenlin.mysql.web.domain.pojo.EcUser;
import org.apache.ibatis.annotations.Param;
import org.mybatis.generator.api.dom.java.BaseMapper;


/**
 * @author zengling
 * @description
 * @date 2018/08/27 23:43
 */
public interface EcUserMapper extends BaseMapper<EcUser> {
    /**
     * 条件查询
     *
     * @return
     */
    EcUser findByParams();

    /**
     * 校验用户名
     *
     * @param userName
     * @return
     */
    int checkUserName(String userName);

    /**
     * 校验email
     *
     * @param email
     * @return
     */
    int checkEmail(String email);

    /**
     * 校验手机号码
     *
     * @param phone
     * @return
     */
    int checkPhone(String phone);

    /**
     * 检验问题答案
     *
     * @param userName
     * @param question
     * @param answer
     * @return
     */
    int checkAnswer(@Param(value = "userName") String userName,@Param(value = "question") String question, @Param(value = "answer") String answer);

    /**
     * 校验
     *
     * @param userName
     * @param password
     * @return
     */
    EcUser selectLogin(@Param(value = "userName") String userName, @Param(value = "password") String password);

    /**
     * 根据用户名获取问题
     *
     * @param userName
     * @return
     */
    String selectQuestionByUserName(String userName);

}