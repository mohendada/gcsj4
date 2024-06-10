package com.example.gcsj4supermarket.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.Res2.QueryPageParam;
import com.example.gcsj4supermarket.common.Res2.Result2;
import com.example.gcsj4supermarket.sys.entity.Storage;
import com.example.gcsj4supermarket.sys.service.IStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wms
 * @since 2022-10-15
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private IStorageService storageService;

    //新增
    @PostMapping("/save")
    public Result2 save(@RequestBody Storage storage) {
        return storageService.save(storage) ? Result2.suc() : Result2.fail();
    }

    //更新
    @PostMapping("/update")
    public Result2 update(@RequestBody Storage storage) {
        return storageService.updateById(storage) ? Result2.suc() : Result2.fail();
    }

    //删除
    @GetMapping("/del")
    public Result2 del(@RequestParam String id) {
        return storageService.removeById(id) ? Result2.suc() : Result2.fail();
    }

    @PostMapping("/listPage")
    public Result2 listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");

        Page<Storage> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Storage::getName, name);
        }

        IPage result = storageService.pageCC(page, lambdaQueryWrapper);
        return Result2.suc(result.getRecords(), result.getTotal());
    }

    @GetMapping("/list")
    public Result2 list() {
        List list = storageService.list();
        return Result2.suc(list);
    }
}
