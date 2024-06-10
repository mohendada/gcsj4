package com.example.gcsj4supermarket.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Form;
import com.example.gcsj4supermarket.sys.entity.Leaveapplication;
import com.example.gcsj4supermarket.sys.entity.User;
import com.example.gcsj4supermarket.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
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



    @GetMapping("/list")
    public Result<Map<String,Object>> getUserMsg(@RequestParam(value = "name",required = false)String name,
                                                 @RequestParam("pageNo")Long pageNo,
                                                 @RequestParam("pageSize")Long pageSize,
                                                 @RequestParam(value = "approval",required = false)String approval){
        //分页查询
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        //name长度是否为0 StringUtils.hasLength(name)
        wrapper.eq(StringUtils.hasLength(name),User::getUserName,name);//查询姓名
        wrapper.eq(User::getRoles,"customer");//查询姓名
        Page<User> page=new Page<>(pageNo,pageSize);
        userService.page(page,wrapper);
        //放数据
        Map<String,Object> data=new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        System.out.println("getUser");
        return Result.success(data);
    }

    @PostMapping("/addUserMsg")
    public Result<?> addUserMsg(@RequestBody User user){
//        logger.info(employee.toString());
//        employeeService.save(employee);
//
//        //生成6位数的密码
//        int randNum = (int)(Math.random()%(999999) + 1);
//        String workPassWord = String.format("%06d",randNum);
//
//        User user = new User();
//        user.setUserName(employee.getEmployeeName());
//        user.setPassword(workPassWord);
//        user.setRoles(employee.getEmployeePosition());
//        user.setUserAccount(" ");
//        user.setAddress(" ");
//        user.setEmail(" ");
//        user.setPhoneNumber(" ");
//        user.setSex(" ");

        userService.save(user);
        return Result.success("新增申请成功");
    }
    @PutMapping("/updateUserMsg")
    public Result<?> updateUserMsg(@RequestBody User user){
        userService.updateById(user);
//        User user = userService.getUserByName(employee.getEmployeeName());
//        //user.setUserName(employee.getEmployeeName());
//        //user.setPassword(workPassWord);
//        user.setRoles(employee.getEmployeePosition());
//        user.setUserAccount(" ");
//        user.setAddress(" ");
//        user.setEmail(" ");
//        user.setPhoneNumber(" ");
//        user.setSex(" ");
//        userService.updateById(user);


        return Result.success("修改成功");
    }
    @GetMapping("/getUserMsgById/{id}")
    public Result<?> getUserMsgById(@PathVariable("id") Integer id){
        User user=userService.getUserByUId(id);
        System.out.println(user);
        return Result.success(user);
    }

    //逻辑删除
    @DeleteMapping("/deleteUserMsg/{id}")
    public Result<?> deleteUserMsg(@PathVariable("id") Integer id){
        userService.removeById(id);
        return Result.success("修改成功");
    }


}
