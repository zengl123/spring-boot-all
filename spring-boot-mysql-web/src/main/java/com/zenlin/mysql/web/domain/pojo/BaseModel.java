package com.zenlin.mysql.web.domain.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.mysql.web.domain.pojo
 * @ClassName BaseModel
 * @Author ZENLIN
 * @Date 2018-08-27 23:45
 * @Description TODO
 * @Version
 * @Modified By
 */
@Data
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "创建者", hidden = true)
    private String creator = "SYSTEM";
    @ApiModelProperty(value = "修改者", hidden = true)
    private String modifier = "SYSTEM";
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;
    @ApiModelProperty(value = "修改时间", hidden = true)
    private String modifiedTime;
}
