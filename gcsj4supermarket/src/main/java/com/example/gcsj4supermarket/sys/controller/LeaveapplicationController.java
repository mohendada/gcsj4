package com.example.gcsj4supermarket.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Leaveapplication;
import com.example.gcsj4supermarket.sys.service.ILeaveapplicationService;
import com.example.gcsj4supermarket.sys.service.IUserService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
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
 * @since 2024-05-30
 */
@RestController
@RequestMapping("/leaveapplication")
public class LeaveapplicationController {

    @Autowired
    private ILeaveapplicationService leaveapplicationService;
    @GetMapping("/list")
    public Result<Map<String,Object>>getUserLeaveapplication(@RequestParam(value = "name",required = false)String name,
                                                         @RequestParam("pageNo")Long pageNo,
                                                         @RequestParam("pageSize")Long pageSize,
                                                             @RequestParam(value = "approval",required = false)String approval){
        //分页查询
   LambdaQueryWrapper<Leaveapplication> wrapper=new LambdaQueryWrapper<>();
   //name长度是否为0 StringUtils.hasLength(name)
        wrapper.eq(StringUtils.hasLength(name),Leaveapplication::getApplicant,name);
        wrapper.eq(StringUtils.hasLength(approval),Leaveapplication::getApproval,approval);
        Page<Leaveapplication>page=new Page<>(pageNo,pageSize);
        leaveapplicationService.page(page,wrapper);
        //放数据
        Map<String,Object> data=new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Result.success(data);
    }

    @PostMapping
    public Result<?> addUserLeaveapplication(@RequestBody Leaveapplication leaveapplication){
        leaveapplicationService.save(leaveapplication);
        return Result.success("新增申请成功");
    }
    @PutMapping
    public Result<?> updateLeaveapplication(@RequestBody Leaveapplication leaveapplication){
        leaveapplicationService.updateById(leaveapplication);
        return Result.success("修改成功");
    }
    @GetMapping("/{id}")
    public Result<?> getLeaveapplicationByid(@PathVariable("id") Integer id){
       Leaveapplication leaveapplication=leaveapplicationService.getById(id);
       System.out.println(leaveapplication);
        return Result.success(leaveapplication);
    }

    //逻辑删除
    @DeleteMapping("/{id}")
    public Result<?> deleteLeaveapplication(@PathVariable("id") Integer id){
        leaveapplicationService.removeById(id);
        return Result.success("修改成功");
    }

}
