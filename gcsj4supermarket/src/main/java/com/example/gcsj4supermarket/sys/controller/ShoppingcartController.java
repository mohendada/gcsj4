package com.example.gcsj4supermarket.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.Res2.QueryPageParam;
import com.example.gcsj4supermarket.common.Res2.Result2;
import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Goods;
import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.entity.Shoppingcart;
import com.example.gcsj4supermarket.sys.mapper.OrderMapper;
import com.example.gcsj4supermarket.sys.service.IGoodsService;
import com.example.gcsj4supermarket.sys.service.IShoppingcartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
@Slf4j
@RestController
@RequestMapping("/shoppingcart")
public class ShoppingcartController {

    @Autowired
    private IShoppingcartService shoppingcartService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private OrderMapper orderMapper;
    //新增
    @PostMapping("/save")
    public Result2 save(@RequestBody Shoppingcart shoppingcart) {
        System.out.println(shoppingcart);
        return shoppingcartService.save(shoppingcart) ? Result2.suc() : Result2.fail();
    }

    //更新
    @PostMapping("/update")
    public Result2 update(@RequestBody Shoppingcart shoppingcart) {
        return shoppingcartService.updateById(shoppingcart) ? Result2.suc() : Result2.fail();
    }

    //删除
    @GetMapping("/del")
    public Result2 del(@RequestParam int id) {
        return shoppingcartService.removeById(id) ? Result2.suc() : Result2.fail();
    }


    @GetMapping("/delAll")
    public Result2 delAll(@RequestParam int userId, int goodsId) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("goods_id", goodsId);
        Shoppingcart shoppingcart = shoppingcartService.getOne(queryWrapper);
        return shoppingcartService.removeById(shoppingcart.getId()) ? Result2.suc() : Result2.fail();
    }

    @PostMapping("/listPage")
    public Result2 listPage() {

        Page<Shoppingcart> page = new Page();
        LambdaQueryWrapper<Shoppingcart> lambdaQueryWrapper = new LambdaQueryWrapper();
        IPage result = shoppingcartService.pageCC(page, lambdaQueryWrapper);
        return Result2.suc(result.getRecords(), result.getTotal());
    }


    @PostMapping("/getStoreId")
    public Result2 listP(@RequestBody QueryPageParam query) {
        List<Shoppingcart> stores = shoppingcartService.list();
        Page<Shoppingcart> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper();
        IPage result = shoppingcartService.pageCC(page, queryWrapper.select("distinct goods_store_id"));
        return Result2.suc(result.getRecords(), result.getTotal());
    }


    @GetMapping("/Createshppingcart")
    public Result2 Createshppingcart(@RequestParam("userId") int userId, int goodsId, String sgoodsName) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("goods_id", goodsId);
        Shoppingcart shoppingcart = shoppingcartService.getOne(queryWrapper);
        if (shoppingcart != null) {
            shoppingcart.setNum(shoppingcart.getNum() + 1);
            return shoppingcartService.updateById(shoppingcart) ? Result2.suc() : Result2.fail();
        } else {
            Shoppingcart shoppingcart1 = new Shoppingcart();
            shoppingcart1.setNum(1);
            shoppingcart1.setGoodsId(goodsId);
            shoppingcart1.setUserId(userId);
            shoppingcart1.setSgoodsName(sgoodsName);
            return shoppingcartService.save(shoppingcart1) ? Result2.suc() : Result2.fail();
        }
    }

//    @Transactional
    @GetMapping("/SentOrder")
    public Result<?> SentOrder(@RequestParam Integer userId, String time) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);


        List<Shoppingcart> shoppingcarts = shoppingcartService.list(queryWrapper);
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        try {
            for (Shoppingcart shoppingcart : shoppingcarts) {

                QueryWrapper<Goods> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("goods_Id", shoppingcart.getGoodsId());
                Goods good = goodsService.getOne(queryWrapper1);


                order.setOrderCreaterId(userId);
                order.setOrderKind(1);
                order.setOrderName(good.getGoodsName());
                order.setOrderStatus(1);
                order.setOrderNumber(shoppingcart.getNum());
                order.setReceivingTime(null);
                order.setOrderTime(LocalDateTime.parse(time));

                orderMapper.insert(order);
            }
        }catch (Exception e){
            log.info("发生错误：{}",e);
            return Result.fail(40001,"发生错误:"+e.toString());
        }
        return Result.success();
    }


}
