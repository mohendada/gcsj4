package com.example.gcsj4supermarket.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.Res2.QueryPageParam;
import com.example.gcsj4supermarket.common.Res2.Result2;
import com.example.gcsj4supermarket.sys.entity.Goodstype;
import com.example.gcsj4supermarket.sys.service.IGoodstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2022-10-15
 */
@RestController
@RequestMapping("/goodstype")
public class GoodstypeController {
    @Autowired
    private IGoodstypeService goodstypeService;
    //新增
    @PostMapping("/save")
    public Result2 save(@RequestBody Goodstype goodstype){
        return goodstypeService.save(goodstype)? Result2.suc(): Result2.fail();
    }
    //更新
    @PostMapping("/update")
    public Result2 update(@RequestBody Goodstype goodstype){
        return goodstypeService.updateById(goodstype)? Result2.suc(): Result2.fail();
    }
    //删除
    @GetMapping("/del")
    public Result2 del(@RequestParam String id){
        return goodstypeService.removeById(id)? Result2.suc(): Result2.fail();
    }

    @PostMapping("/listPage")
    public Result2 listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");

        Page<Goodstype> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goodstype> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Goodstype::getName,name);
        }

        IPage result = goodstypeService.pageCC(page,lambdaQueryWrapper);
        return Result2.suc(result.getRecords(),result.getTotal());
    }
    @GetMapping("/list")
    public Result2 list(){
        List list = goodstypeService.list();
        return Result2.suc(list);
    }
}
