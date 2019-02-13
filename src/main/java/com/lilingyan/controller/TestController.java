package com.lilingyan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lilingyan
 * @Date 2018/12/16 13:38
 */
@Api(value = "数据获取",description = "登陆验证")
@RestController("/test")
public class TestController {

    @ApiOperation(value = "获取数据",notes = "登陆后可访问")
    @GetMapping("/get")
    public String get() {
        return "我是保护数据";
    }

}
