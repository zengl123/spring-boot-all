package com.zenlin.mysql.web.handler;

import com.zenlin.mysql.web.common.ServerResponse;
import com.zenlin.mysql.web.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.mysql.web.handler
 * @ClassName ExceptionHandler
 * @Author ZENLIN
 * @Date 2018-08-29 0:11
 * @Description TODO
 * @Version
 * @Modified By
 */
@RestControllerAdvice
public class SpringExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(SpringExceptionHandler.class);

    /**
     * 自定义业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ServerResponse handlerBusinessException(BusinessException e) {
        LOGGER.error(e.getClass().getName() + ":" + e.getMessage());
        return ServerResponse.createByErrorMessage(e.getMessage());
    }
}
