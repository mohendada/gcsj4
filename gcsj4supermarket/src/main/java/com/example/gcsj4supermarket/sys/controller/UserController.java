package com.example.gcsj4supermarket.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Form;
import com.example.gcsj4supermarket.sys.entity.Leaveapplication;
import com.example.gcsj4supermarket.sys.entity.User;
import com.example.gcsj4supermarket.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@RestController
@RequestMapping("/user")
//@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;
        //@PostMapping("/login")
        //@GetMapping("/all")
        public Result<List<User>> getAllUser(@RequestParam("username")String username,@RequestParam("password")String password){
        System.out.println(username);
        List<User> users=userService.list();
        System.out.println("1111");
        return Result.success(users,"查询成功");
        }

    @GetMapping("/info")
    public Result<Map<String,Object>> getUserInfo(@RequestParam("token") String token){
            //根据token获取用户信息，redis
        System.out.println(111);
      Map<String,Object>data=  userService.getUserInfo(token);
        if(data!=null)
        {
            return  Result.success(data);
        }
        return Result.fail(20003,"登录失效");
    }
    @GetMapping ("/loginuserform")
    public Result<?> getloginUserInfo(@RequestParam("username")String username,@RequestParam("password")String password){
        //根据token获取用户信息，redis
        Form form=new Form(username,password);
      User user=userService.getloginUserInfo(form);
        return  Result.success(user);
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token){
            userService.logout(token);
            return Result.success();

    }
    @PostMapping("/register")
    public Result<?> addUser(@RequestBody User user){
        userService.save(user);
        return Result.success("新增申请成功");
    }
}
