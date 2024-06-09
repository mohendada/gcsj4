package com.example.gcsj4supermarket.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.Res2.QueryPageParam;
import com.example.gcsj4supermarket.common.Res2.Result;
import com.example.gcsj4supermarket.sys.entity.Store;
import com.example.gcsj4supermarket.sys.service.IOrderService;
import com.example.gcsj4supermarket.sys.service.IRecordService;
import com.example.gcsj4supermarket.sys.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.gcsj4supermarket.sys.entity.Record;

import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wms
 * @since 2022-10-16
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private IRecordService recordService;

    @Autowired
    private IStoreService storeService;

    @Autowired
    IOrderService orderService;

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String goodsName = (String) param.get("goodsName");
        String goodsKind = (String) param.get("goodsKind");
        String goodsStoreId = (String) param.get("goodsStoreId");

        Page<Record> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Record> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(goodsName) && !"null".equals(goodsName)) {
            lambdaQueryWrapper.like(Record::getGoodsName, goodsName);
        }
        if (StringUtils.isNotBlank(goodsKind) && !"null".equals(goodsKind)) {
            lambdaQueryWrapper.eq(Record::getGoodsKind, goodsKind);
        }
        if (StringUtils.isNotBlank(goodsStoreId) && !"null".equals(goodsStoreId)) {
            lambdaQueryWrapper.eq(Record::getGoodsStoreId, goodsStoreId);
        }

        IPage result = recordService.pageCC(page, lambdaQueryWrapper);
        return Result.suc(result.getRecords(), result.getTotal());
    }


//    public Result changeOrder(String id){
//        QueryWrapper<Order> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper1.eq("order_id", id);
//        Order order = orderService.getOne(queryWrapper1);
//        Integer orderNumber = order.getOrderNumber();
//        String orderName = order.getOrderName();
//        Integer orderStatus = order.getOrderStatus();
//        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("goods_name", orderName);
//        Store store = storeService.getOne(queryWrapper);
//        //增加
//        if (orderStatus == ){
//            store.setGoodsNumber(store.getGoodsNumber()+orderNumber);
//        }
//        //减少
//        else if (orderStatus == ) {
//            store.setGoodsNumber(store.getGoodsNumber()-orderNumber);
//        }
//
//        return storeService.updateById(store) ? Result.suc() : Result.fail();
//    }

//    //减少
//    @PostMapping("/delOrder")
//    public Result delOrder(String id) {
//        QueryWrapper<Order> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper1.eq("order_id", id);
//        Order order = orderService.getOne(queryWrapper1);
//        Integer orderNumber = order.getOrderNumber();
//        String orderName = order.getOrderName();
//        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("goods_name", orderName);
//        Store store = storeService.getOne(queryWrapper);
//        store.setGoodsNumber(store.getGoodsNumber() - orderNumber);
//        return storeService.updateById(store) ? Result.suc() : Result.fail();
//    }


    @PostMapping("/save")
    public Result save(@RequestBody Record record) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", record.getGoodsId());
        Store store = storeService.getOne(queryWrapper);
        int n = record.getGoodsNumber();
        //action==2表示出库
        if ("2".equals(record.getAction())) {
            n = -n;
            record.setGoodsNumber(n);
            record.setAction("出库");
        } else {
            record.setAction("入库");
            int num = store.getGoodsNumber() + n;
            store.setGoodsNumber(num);
        }
        storeService.updateById(store);
        return recordService.save(record) ? Result.suc() : Result.fail();
    }


    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return recordService.removeById(id) ? Result.suc() : Result.fail();
    }
}
