package com.zenlin.mysql.web.control;

import com.zenlin.mysql.web.common.ServerResponse;
import com.zenlin.mysql.web.domain.pojo.EcUser;
import com.zenlin.mysql.web.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.mysql.web.control
 * @ClassName UserControl
 * @Author ZENLIN
 * @Date 2018-08-27 23:58
 * @Description TODO
 * @Version
 * @Modified By
 */
@Api(tags = "")
@RestController
@RequestMapping(value = "/user/")
public class UserControl {
    @Autowired
    private IUserService userService;

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "EcUser")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> add(@RequestBody EcUser user) {
        ServerResponse insert = userService.insert(user);
        return ResponseEntity.ok(insert);
    }

}
