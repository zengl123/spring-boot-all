package com.zenlin.es.common.entity.camera;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import com.zenlin.es.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;


/**
 * @Project spring-boot-all
 * @Package com.zenlin.es.common.entity.camera
 * @ClassName CameraGroup
 * @Author ZENLIN
 * @Date 2018-05-19 20:28
 * @Description 监控组织实体类
 * @Version
 * @Modified By
 */
@Data
public class CameraGroup extends BaseEntity{
    /**
     * 区域编码
     */
    @JSONField(name = "group_no")
    @SerializedName(value = "group_no")
    private String groupNo;
    /**
     * 区域名称
     */
    @JSONField(name = "group_name")
    @SerializedName(value = "group_name")
    private String groupName;
    /**
     * 父区域编码
     */
    @JSONField(name = "parent_no")
    @SerializedName(value = "parent_no")
    private String parentNo;
    /**
     * 父区域名称
     */
    @JSONField(name = "parent_name")
    @SerializedName(value = "parent_name")
    private String parentName;
    /**
     * 是否是父区域(0-不是,1-是)
     */
    @JSONField(name = "is_parent")
    @SerializedName(value = "is_parent")
    private Integer isParent;
    /**
     * 父区域类型(1-中心,2-区域)
     */
    @JSONField(name = "parent_type_no")
    @SerializedName(value = "parent_type_no")
    private Integer parentTypeNo;
    /**
     * 厂家名称编号
     */
    @JSONField(name = "series_no")
    @SerializedName(value = "series_no")
    private String seriesNo;
}
