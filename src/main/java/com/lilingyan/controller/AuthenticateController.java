package com.lilingyan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author: lilingyan
 * @Date 2018/8/1 11:09
 */
@Api(value = "登陆")
@RestController
public class AuthenticateController {

    @ApiOperation(value = "登陆",notes = "demo不需要任何参数")
    @PostMapping("/login")
    public void login() {
        UsernamePasswordToken token = new UsernamePasswordToken();
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
    }

    @ApiIgnore
    @ApiOperation(value = "去登陆",notes = "错误提示")
    @GetMapping("/gologin")
    public String goLogin() {
        return "你还没登陆";
    }


}
