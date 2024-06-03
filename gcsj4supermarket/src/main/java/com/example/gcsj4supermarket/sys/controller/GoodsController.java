package com.example.gcsj4supermarket.sys.controller;

import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Goods;
import com.example.gcsj4supermarket.sys.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@RestController
@RequestMapping("/sys/goods")
@Slf4j
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;

    /**
     * 查询所有商品信息
     *
     * @return List<Goods>
     */
    @RequestMapping("/GoodsList")
    public Result<Object> getGoods() {
        List<Goods> goods = goodsService.GetGoodsList();
        return Result.success(goods);
    }

    @RequestMapping("/ById")
    public Result<?> getGoods(@RequestParam("id") Integer id) {
        Goods good = goodsService.getGoodsById(id);
        log.info("controller getGoods {}", good);
        return Result.success(good);
    }

    @RequestMapping("/Update")
    public Result<?> updateGoods(@RequestBody Goods goods) {
        goodsService.update(goods);
        return Result.success();
    }

    @RequestMapping("/Insert")
    public Result<?> insertGoods(Goods goods) {
        goodsService.insert(goods);
        return Result.success();
    }

    @RequestMapping("/Delete")
    public Result<?> deleteGoods(@RequestParam("id") Integer id) {
        goodsService.remove(id);
        return Result.success();
    }
}
