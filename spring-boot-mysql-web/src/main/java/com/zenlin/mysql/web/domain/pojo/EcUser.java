package com.zenlin.mysql.web.domain.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author zengling
 * @description
 * @date 2018/08/27 23:43
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EcUser extends BaseModel {
    private static final long serialVersionUID = 2678077201416394558L;
    public static String table = "ec_user";
    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,30}$";
    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{8,16}$";
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(14[57])|(17[0])|(17[7])|(18[0,0-9]))\\d{8}$";

    @NotBlank(message = "名称不能为空")
    @Size(min = 5, max = 30, message = "名字长度必须在5和30之间")
    @Pattern(regexp = REGEX_USERNAME)
    @ApiModelProperty(value = "用户名", example = "admin", required = true)
    private String userName;
    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 20, message = "密码长度只能在8-20之间,必须包含字母、数字")
    @Pattern(regexp = REGEX_PASSWORD)
    @ApiModelProperty(value = "用户密码", example = "admin123", required = true)
    private String password;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式有误")
    @ApiModelProperty(value = "电子邮箱", example = "xxx@xx.com", required = true)
    private String email;
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = REGEX_MOBILE)
    @ApiModelProperty(value = "手机号码", example = "17363645521", required = true)
    private String phone;
    @ApiModelProperty(value = "找回密码问题", required = true)
    private String question;
    @ApiModelProperty(value = "找回密码答案", required = true)
    private String answer;
    @Pattern(regexp = "[01]", message = "权限格式为0|1,0管理员,1代表普通用户")
    @ApiModelProperty(value = "用户角色", example = "0", required = true, hidden = true)
    private Integer role;
}