package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goods;
import com.wms.entity.Store;
import com.wms.entity.Supplier;
import com.wms.entity.User;
import com.wms.service.GoodsService;
import com.wms.service.StoreService;
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
 * @since 2024-06-04
 */
@RestController
@RequestMapping("/store")
public class StoreController  {

    @Autowired
    private StoreService storeService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Store store)  {
        System.out.println(store);
        return storeService.save(store) ? Result.suc() : Result.fail();
    }

    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Store store) {
        return storeService.updateById(store) ? Result.suc() : Result.fail();
    }

    //删除
    @GetMapping("/del")
    public Result del(@RequestParam int id) {
        return storeService.removeById(id) ? Result.suc() : Result.fail();
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
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
        return Result.suc(result.getRecords(), result.getTotal());
    }


    @PostMapping("/getStoreId")
    public Result listP(@RequestBody QueryPageParam query) {List<Store> stores = storeService.list();
        Page<Store> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
       QueryWrapper queryWrapper = new QueryWrapper();
        IPage result = storeService.pageCC(page,queryWrapper.select("distinct goods_store_id"));
        return Result.suc(result.getRecords(),result.getTotal());
    }
}
