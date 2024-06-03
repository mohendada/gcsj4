package com.example.gcsj4supermarket.sys.controller;


import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Form;
import com.example.gcsj4supermarket.sys.entity.User;
import com.example.gcsj4supermarket.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody Form form){
        //System.out.println(userName);
        Map<String,Object> data=userService.login(form);
        System.out.println(Result.success(data));
        if(data!=null)
        {
            System.out.println("传输");
            //return Result.fail(20002,"用户密码错误");
          return  Result.success(data);
        }
        return Result.fail(20002,"用户密码错误");
    }
}