package com.zenlin.mysql.web.service.impl;

import com.zenlin.mysql.web.common.Constant;
import com.zenlin.mysql.web.common.ServerResponse;
import com.zenlin.mysql.web.common.TokenCache;
import com.zenlin.mysql.web.dao.EcUserMapper;
import com.zenlin.mysql.web.domain.pojo.EcUser;
import com.zenlin.mysql.web.security.Md5Util;
import com.zenlin.mysql.web.service.IUserService;
import com.zenlin.mysql.web.utils.UuidUtil;
import org.apache.commons.lang3.StringUtils;
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
    public ServerResponse login(String userName, String password) {
        int count = mapper.checkUserName(userName);
        if (0 == count) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        password = Md5Util.Md5EncodeUtf8(password);
        EcUser ecUser = mapper.selectLogin(userName, password);
        if (null == ecUser) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        ecUser.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(ecUser, "登陆成功");
    }

    @Override
    public ServerResponse register(EcUser ecUser) {
        ServerResponse checkValid = checkValid(Constant.USER_NAME, ecUser.getUserName());
        if (!checkValid.isSuccess()) {
            return checkValid;
        }
        checkValid = checkValid(Constant.EMAIL, ecUser.getEmail());
        if (!checkValid.isSuccess()) {
            return checkValid;
        }
        checkValid = checkValid(Constant.PHONE, ecUser.getPhone());
        if (!checkValid.isSuccess()) {
            return checkValid;
        }
        ecUser.setRole(Constant.Role.ROLE_CUSTOMER);
        //对密码进行加密
        ecUser.setPassword(Md5Util.Md5EncodeUtf8(ecUser.getPassword()));
        ServerResponse save = super.save(ecUser);
        if (save.isSuccess()) {
            return ServerResponse.createBySuccessMessager("注册成功");
        } else {
            return ServerResponse.createByErrorMessage("注册失败");
        }
    }

    @Override
    public ServerResponse selectQuestion(String userName) {
        ServerResponse checkValid = checkValid(Constant.USER_NAME, userName);
        if (checkValid.isSuccess()) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String question = mapper.selectQuestionByUserName(userName);
        if (StringUtils.isNotBlank(question)) {
            return ServerResponse.createBySuccess(question);
        } else {
            return ServerResponse.createByErrorMessage("问题未设置");
        }
    }

    @Override
    public ServerResponse checkAnswer(String userName, String question, String answer) {
        int checkAnswer = mapper.checkAnswer(userName, question, answer);
        //正确
        if (checkAnswer > 0) {
            String token = UuidUtil.createId();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + userName, token);
            return ServerResponse.createBySuccess(token);
        } else {
            return ServerResponse.createByErrorMessage("答案错误");
        }
    }

    @Override
    public ServerResponse checkValid(String type, String value) {
        if (StringUtils.isNotBlank(type)) {
            switch (type) {
                case Constant.USER_NAME:
                    int checkUserName = mapper.checkUserName(value);
                    if (checkUserName > 0) {
                        return ServerResponse.createByErrorMessage("用户名已存在");
                    } else {
                        return ServerResponse.createBySuccess();
                    }
                case Constant.EMAIL:
                    int checkEmail = mapper.checkEmail(value);
                    if (checkEmail > 0) {
                        return ServerResponse.createByErrorMessage("邮箱已存在");
                    } else {
                        return ServerResponse.createBySuccess();
                    }
                case Constant.PHONE:
                    int checkPhone = mapper.checkPhone(value);
                    if (checkPhone > 0) {
                        return ServerResponse.createByErrorMessage("手机号码已存在");
                    } else {
                        return ServerResponse.createBySuccess();
                    }
                default:
                    return ServerResponse.createByErrorMessage("校验类型不存在");
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误,校验类型不能为空");
        }
    }

}
