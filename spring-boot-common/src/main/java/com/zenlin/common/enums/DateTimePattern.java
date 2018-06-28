package com.zenlin.common.enums;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.common.enums
 * @ClassName DateTimePattern
 * @Author ZENLIN
 * @Date 2018-06-13 19:49
 * @Description TODO
 * @Version
 * @Modified By
 */
public enum DateTimePattern {
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
    YYYY_MM_DD("yyyy-MM-dd");

    private String value;

    DateTimePattern(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
