package com.gcsj4.warehouse.controller;

import com.gcsj4.warehouse.entity.Goods;
import com.gcsj4.warehouse.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    @Autowired
    private IGoodsService  goodsService;

    /**
     * 查询所有商品信息
     * @return List<Goods>
     */
    @RequestMapping("/GoodsList")
    public List<Goods> getGoods() {
        return goodsService.GetGoodsList();
    }

    @RequestMapping("/GetById")
    public Goods getGoods(@RequestParam("id") Integer id) {
        Goods good = goodsService.getGoodsById(id);
        log.info("controller getGoods {}", good);
        return good;
    }

}
