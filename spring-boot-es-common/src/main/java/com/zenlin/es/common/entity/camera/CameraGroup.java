package com.zenlin.es.common.entity.camera;

import com.zenlin.es.common.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.es.common.entity.camera
 * @ClassName CameraGroup
 * @Author ZENLIN
 * @Date 2018-05-19 20:28
 * @Description TODO
 * @Version
 * @Modified By
 */
@Data
@Document(indexName = "cameragroup",type = "cameraGroup")
public class CameraGroup extends BaseEntity {
    @Id
    private Long id;
    private String cameraGroupNo;
    private String cameraGroupName;
    private String cameraGroupParentNo;
    private String cameraGroupParentName;
    private String series;
}
