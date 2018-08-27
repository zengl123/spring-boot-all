package com.zenlin.mysql.web.domain.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zengling
 * @description
 * @date 2018/08/27 23:43
 */
@Data
public class EcUser extends BaseModel {
    private static final long serialVersionUID = 2678077201416394558L;
    @ApiModelProperty(value = "用户名", example = "admin", required = true)
    private String username;
    @ApiModelProperty(value = "用户密码", example = "12345", required = true)
    private String password;
    @ApiModelProperty(value = "电子邮箱", required = true)
    private String email;
    @ApiModelProperty(value = "手机号码", required = true)
    private String phone;
    @ApiModelProperty(value = "找回密码问题", required = true)
    private String question;
    @ApiModelProperty(value = "找回密码答案", required = true)
    private String answer;
    @ApiModelProperty(value = "角色0-管理员,1-普通用户", required = true)
    private Integer role;
}