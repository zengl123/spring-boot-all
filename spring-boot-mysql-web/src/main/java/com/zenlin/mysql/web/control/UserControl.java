package com.zenlin.mysql.web.control;

import com.zenlin.mysql.web.common.Constant;
import com.zenlin.mysql.web.common.ServerResponse;
import com.zenlin.mysql.web.domain.pojo.EcUser;
import com.zenlin.mysql.web.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
@Api(tags = "登陆|登出|注册模块")
@RestController
@RequestMapping(value = "/user/")
public class UserControl {
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户登陆")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(@RequestParam String userName, @RequestParam String password, HttpSession session) {
        ServerResponse login = userService.login(userName, password);
        if (login.isSuccess()) {
            session.setAttribute(Constant.CURRENT_USER, login.getData());
        }
        return login;
    }

    @ApiOperation(value = "用户登出")
    @RequestMapping(value = "loginOut", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse loginOut(HttpSession session) {
        session.removeAttribute(Constant.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(@RequestBody EcUser ecUser) {
        return userService.register(ecUser);
    }

    @ApiOperation(value = "数据校验")
    @RequestMapping(value = "checkValid", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse checkValid(String type, String value) {
        return userService.checkValid(type, value);
    }

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getUserInfo(HttpSession session) {
        EcUser user = (EcUser) session.getAttribute(Constant.CURRENT_USER);
        if (null == user) {
            return ServerResponse.createByErrorMessage("用户未登录");
        } else {
            return ServerResponse.createBySuccess(user);
        }
    }
}
