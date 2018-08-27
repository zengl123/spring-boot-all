package com.zenlin.common.utils;

import com.zenlin.common.entity.RestMessage;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.common.utils
 * @ClassName ResultUtil
 * @Author ZENLIN
 * @Date 2018-07-03 22:04
 * @Description TODO
 * @Version
 * @Modified By
 */
public class ResultUtil {
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "未知错误";

    public static RestMessage success(String message, Object data) {
        return getRestMessage(message, 8200, true, data);
    }

    public static RestMessage success(Object data) {
        return getRestMessage(SUCCESS, 8200, true, data);
    }


    public static RestMessage error(String message) {
        return getRestMessage(message, 8500, false, null);
    }

    public static RestMessage error() {
        return getRestMessage(ERROR, 8500, false, null);
    }

    private static RestMessage getRestMessage(String message, Integer code, boolean success, Object data) {
        RestMessage restMessage = new RestMessage();
        restMessage.setCode(code);
        restMessage.setSuccess(success);
        restMessage.setData(data);
        restMessage.setMessage(message);
        return restMessage;
    }
}
