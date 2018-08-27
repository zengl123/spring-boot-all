package com.zenlin.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.common.entity
 * @ClassName RestMessage
 * @Author ZENLIN
 * @Date 2018-07-03 21:58
 * @Description TODO
 * @Version
 * @Modified By
 */
@Data
public class RestMessage implements Serializable {
    public static final String SUCCESS = "SUCCESS";
    private int code;
    private String message;
    private boolean success;
    private Object data;
}
