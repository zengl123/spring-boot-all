package com.zenlin.mysql.web.exception;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.mysql.web.exception
 * @ClassName BusinessException
 * @Author ZENLIN
 * @Date 2018-08-29 0:15
 * @Description TODO
 * @Version
 * @Modified By
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 7493711492820795133L;

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
