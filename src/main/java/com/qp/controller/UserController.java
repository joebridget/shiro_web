package com.qp.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @RequestMapping("login")
    public Map<String,Object> login(String username, String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        System.out.println("Token："+token);
        //查询主体
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> map = new HashMap<>();
        try{
            subject.login(token);
            map.put("message",200);
            return map;
        }catch (IncorrectCredentialsException e){
            map.put("message","密码错误");
            return map;
        }catch (UnknownAccountException e){
            map.put("message","账号错误");
            return map;
        }
    }
}
