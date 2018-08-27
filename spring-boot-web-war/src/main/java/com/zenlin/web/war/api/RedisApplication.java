package com.zenlin.web.war.api;

import com.alibaba.fastjson.JSONObject;
import com.zenlin.common.entity.RestMessage;
import com.zenlin.common.utils.ResultUtil;
import com.zenlin.web.war.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.web.war.api
 * @ClassName RedisApplication
 * @Author ZENLIN
 * @Date 2018-07-03 21:27
 * @Description TODO
 * @Version
 * @Modified By
 */
@RestController
@RequestMapping(value = "/spring/redis/")
public class RedisApplication {
    @Autowired
    private RedisService service;

    @RequestMapping(value = "set", method = RequestMethod.POST)
    @ResponseBody
    public RestMessage set(@RequestBody JSONObject requestBody) {
        Optional.ofNullable(requestBody);
        String key = requestBody.getString("key");
        Optional.ofNullable(key);
        Object value = requestBody.get("value");
        Optional.ofNullable(value);
        service.set(key, value);
        return ResultUtil.success(RestMessage.SUCCESS, value);
    }
}
