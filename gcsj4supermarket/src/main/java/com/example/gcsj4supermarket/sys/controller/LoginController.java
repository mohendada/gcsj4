package com.example.gcsj4supermarket.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Forgotuser;
import com.example.gcsj4supermarket.sys.entity.Form;
import com.example.gcsj4supermarket.sys.entity.User;
import com.example.gcsj4supermarket.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
   @GetMapping("/forgotfomr")
    public Result<?> forgotfomr(@RequestParam("userAccount")String userAccount,
                                 @RequestParam("phoneNumber")String phoneNumber){
      User user=userService.getforgotUserInfo(userAccount,phoneNumber);
       if(user!=null)
       {
           System.out.println("传输");
           //return Result.fail(20002,"用户密码错误");
           return  Result.success(user);
       }
       return Result.fail(20002,"信息错误");
   }
    @PutMapping("/forgotalter")
    public Result<?> forgotfomr(@RequestBody Forgotuser forgotuser){
        String userName=forgotuser.getUserAccount();
        String phoneNumber=forgotuser.getPhoneNumber();
        User user=userService.getforgotUserInfo(userName,phoneNumber);
        if(user!=null)
        {user.setPassword(forgotuser.getPassword());
        System.out.println(user);
        System.out.println("***");
        User user2=userService.getuserbyNa(user);
        System.out.println("-----");
        userService.updatePasswordById(user);
        return Result.fail(20000,"修改密码成功");}
        return Result.fail(20002,"信息错误");
    }
}