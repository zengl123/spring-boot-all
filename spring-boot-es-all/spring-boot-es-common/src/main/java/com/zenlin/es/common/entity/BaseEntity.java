package com.zenlin.es.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import com.zenlin.common.enums.DateTimePattern;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
public class BaseEntity {
    @JSONField(name = "create_time")
    @SerializedName(value = "create_time")
    private String createTime;
    private String modifyTime = LocalDateTime.now().withNano(0).format(DateTimeFormatter.ofPattern(DateTimePattern.YYYY_MM_DD_HH_MM_SS.getValue()));
}
