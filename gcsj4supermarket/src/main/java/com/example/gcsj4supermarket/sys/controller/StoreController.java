package com.example.gcsj4supermarket.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.Res2.QueryPageParam;
import com.example.gcsj4supermarket.common.Res2.Result2;
import com.example.gcsj4supermarket.sys.entity.Store;
import com.example.gcsj4supermarket.sys.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@RestController
@RequestMapping("/store")
public class StoreController  {

    @Autowired
    private IStoreService storeService;

    //新增
    @PostMapping("/save")
    public Result2 save(@RequestBody Store store)  {
        System.out.println(store);
        return storeService.save(store) ? Result2.suc() : Result2.fail();
    }

    //更新
    @PostMapping("/update")
    public Result2 update(@RequestBody Store store) {
        return storeService.updateById(store) ? Result2.suc() : Result2.fail();
    }

    //删除
    @GetMapping("/del")
    public Result2 del(@RequestParam int id) {
        return storeService.removeById(id) ? Result2.suc() : Result2.fail();
    }

    @PostMapping("/listPage")
    public Result2 listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String goodsName = (String) param.get("goodsName");
        String goodsKind = (String) param.get("goodsKind");
        String goodsStoreId = (String) param.get("goodsStoreId");
        System.out.println(goodsName + ' ' + goodsKind + ' ' + goodsStoreId);
        Page<Store> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Store> lambdaQueryWrapper = new LambdaQueryWrapper();

        if (StringUtils.isNotBlank(goodsName) && !"null".equals(goodsName)) {
            lambdaQueryWrapper.like(Store::getGoodsName, goodsName);
        }
        if (StringUtils.isNotBlank(goodsKind) && !"null".equals(goodsKind)) {
            lambdaQueryWrapper.eq(Store::getGoodsKind, goodsKind);
        }
        if (StringUtils.isNotBlank(goodsStoreId) && !"null".equals(goodsStoreId)) {
            lambdaQueryWrapper.eq(Store::getGoodsStoreId, goodsStoreId);
        }

        IPage result = storeService.pageCC(page, lambdaQueryWrapper);

        return Result2.suc(result.getRecords(), result.getTotal());
    }


    @PostMapping("/getStoreId")
    public Result2 listP(@RequestBody QueryPageParam query) {List<Store> stores = storeService.list();
        Page<Store> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        QueryWrapper<Store> queryWrapper = new QueryWrapper();
        IPage result = storeService.pageCC(page,queryWrapper.select("distinct goods_store_id"));
        return Result2.suc(result.getRecords(),result.getTotal());
    }
}