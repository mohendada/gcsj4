package com.example.gcsj4supermarket.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Employee;
import com.example.gcsj4supermarket.sys.entity.Leaveapplication;
import com.example.gcsj4supermarket.sys.entity.User;
import com.example.gcsj4supermarket.sys.mapper.EmployeeMapper;
import com.example.gcsj4supermarket.sys.service.IEmployeeService;
import com.example.gcsj4supermarket.sys.service.ILeaveapplicationService;
import com.example.gcsj4supermarket.sys.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
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
@RequestMapping("/employee")
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IUserService userService;
    @GetMapping("/list")
    public Result<Map<String,Object>> getEmployee(@RequestParam(value = "name",required = false)String name,
                                                  @RequestParam("pageNo")Long pageNo,
                                                  @RequestParam("pageSize")Long pageSize,
                                                  @RequestParam(value = "approval",required = false)String approval){
        //分页查询
        LambdaQueryWrapper<Employee> wrapper=new LambdaQueryWrapper<>();
        //name长度是否为0 StringUtils.hasLength(name)
        wrapper.eq(StringUtils.hasLength(name),Employee::getEmployeeName,name);//查询姓名
        Page<Employee> page=new Page<>(pageNo,pageSize);
        employeeService.page(page,wrapper);
        //放数据
        Map<String,Object> data=new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        System.out.println("getEmployee");
        return Result.success(data);
    }

    @PostMapping
    public Result<?> addEmployee(@RequestBody Employee employee){
        logger.info(employee.toString());
        employeeService.save(employee);

        //生成6位数的密码
        int randNum = (int)(Math.random()%(999999) + 1);
        String workPassWord = String.format("%06d",randNum);

        User user = new User();
        user.setUserName(employee.getEmployeeName());
        user.setPassword(workPassWord);
        user.setRoles(employee.getEmployeePosition());
        user.setUserAccount(" ");
        user.setAddress(" ");
        user.setEmail(" ");
        user.setPhoneNumber(" ");
        user.setSex(" ");

        userService.save(user);
        return Result.success("新增申请成功");
    }
    @PutMapping
    public Result<?> updateEmployee(@RequestBody Employee employee){
        employeeService.updateById(employee);
        User user = userService.getUserByName(employee.getEmployeeName());
        //user.setUserName(employee.getEmployeeName());
        //user.setPassword(workPassWord);
        user.setRoles(employee.getEmployeePosition());
        user.setUserAccount(" ");
        user.setAddress(" ");
        user.setEmail(" ");
        user.setPhoneNumber(" ");
        user.setSex(" ");
        userService.updateById(user);


        return Result.success("修改成功");
    }
    @GetMapping("/{id}")
    public Result<?> getEmployeeByid(@PathVariable("id") Integer id){
        Employee employee=employeeService.getById(id);
        System.out.println(employee);
        return Result.success(employee);
    }

    //逻辑删除
    @DeleteMapping("/{id}")
    public Result<?> deleteEmployee(@PathVariable("id") Integer id){
        Employee employee=employeeService.getById(id);
        User user = userService.getUserByName(employee.getEmployeeName());
        userService.removeById(user.getId());

        employeeService.removeById(id);

        return Result.success("修改成功");
    }
    @GetMapping("/GetDeliverymanList")
    public Result<?> GetDeliverymanList(){
        System.out.println("GetDeliverymanList");
        return Result.success(employeeService.GetDeliverymanList());
    }
}





