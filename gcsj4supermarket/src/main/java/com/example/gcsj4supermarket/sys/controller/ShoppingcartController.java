package com.example.gcsj4supermarket.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.Res2.QueryPageParam;
import com.example.gcsj4supermarket.common.Res2.Result;
import com.example.gcsj4supermarket.sys.entity.Goods;
import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.entity.Shoppingcart;
import com.example.gcsj4supermarket.sys.service.IGoodsService;
import com.example.gcsj4supermarket.sys.service.IShoppingcartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2024-06-09
 */

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wms
 * @since 2024-06-04
 */
@RestController
@RequestMapping("/shoppingcart")
public class ShoppingcartController {

    @Autowired
    private IShoppingcartService shoppingcartService;

    @Autowired
    private IGoodsService goodsService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Shoppingcart shoppingcart)  {
        System.out.println(shoppingcart);
        return shoppingcartService.save(shoppingcart) ? Result.suc() : Result.fail();
    }

    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Shoppingcart shoppingcart) {
        return shoppingcartService.updateById(shoppingcart) ? Result.suc() : Result.fail();
    }

    //删除
    @GetMapping("/del")
    public Result del(@RequestParam int id) {
        return shoppingcartService.removeById(id) ? Result.suc() : Result.fail();
    }


    @GetMapping("/delAll")
    public Result delAll(@RequestParam int userId ,int  goodsId) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("goods_id", goodsId);
        Shoppingcart  shoppingcart = shoppingcartService.getOne(queryWrapper);
        return shoppingcartService.removeById(shoppingcart.getId()) ? Result.suc() : Result.fail();
    }

    @PostMapping("/listPage")
    public Result listPage() {

        Page<Shoppingcart> page = new Page();
        LambdaQueryWrapper<Shoppingcart> lambdaQueryWrapper = new LambdaQueryWrapper();
        IPage result = shoppingcartService.pageCC(page,lambdaQueryWrapper);
        return Result.suc(result.getRecords(), result.getTotal());
    }


    @PostMapping("/getStoreId")
    public Result listP(@RequestBody QueryPageParam query) {
        List<Shoppingcart> stores = shoppingcartService.list();
        Page<Shoppingcart> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper();
        IPage result = shoppingcartService.pageCC(page,queryWrapper.select("distinct goods_store_id"));
        return Result.suc(result.getRecords(),result.getTotal());
    }


    @GetMapping("/Createshppingcart")
    public Result Createshppingcart(@RequestParam int userId ,int goodsId, String sgoodsName) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("goods_id", goodsId);
        Shoppingcart  shoppingcart = shoppingcartService.getOne(queryWrapper);
        if (shoppingcart!= null){
            shoppingcart.setNum(shoppingcart.getNum()+1);
            return shoppingcartService.updateById(shoppingcart) ? Result.suc() : Result.fail();
        }else {
            Shoppingcart shoppingcart1 = new Shoppingcart();
            shoppingcart1.setNum(1);
            shoppingcart1.setGoodsId(goodsId);
            shoppingcart1.setUserId(userId);
            shoppingcart1.setSgoodsName(sgoodsName);
            return shoppingcartService.save(shoppingcart1) ? Result.suc() : Result.fail();
        }
    }

    @GetMapping("/SentOrder")
    public List<Order> SentOrder(@RequestParam int userId ) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);


        List<Shoppingcart> shoppingcarts = shoppingcartService.list(queryWrapper);
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        for( Shoppingcart shoppingcart : shoppingcarts ){

            QueryWrapper<Goods> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("goods_Id", shoppingcart.getGoodsId());
            Goods good = goodsService.getOne(queryWrapper1);


            order.setOrderCreaterId(userId);
            order.setOrderKind(1);
            order.setOrderName(good.getGoodsName());
            order.setOrderStatus(1);
            order.setOrderNumber(shoppingcart.getNum());
            order.setReceivingTime(null);
            order.setOrderTime(LocalDateTime.now());

            orders.add(order);
        }
        return orders;
    }


}
