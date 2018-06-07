package com.zenlin.es.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.es.common.entity
 * @ClassName BaseEntity
 * @Author ZENLIN
 * @Date 2018-05-19 20:27
 * @Description TODO
 * @Version 1.0
 * @Modified By
 */
@Data
public class BaseEntity implements Serializable {
    private String createTime;
    private String modifyTime;
}
