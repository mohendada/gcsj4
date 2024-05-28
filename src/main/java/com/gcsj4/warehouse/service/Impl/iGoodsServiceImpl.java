package com.gcsj4.warehouse.service.Impl;

import com.gcsj4.warehouse.entity.Goods;
import com.gcsj4.warehouse.mapper.GoodsMapper;
import com.gcsj4.warehouse.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class iGoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> GetGoodsList() {
        return goodsMapper.getGoodsList();
    }

    @Override
    public Goods getGoodsById(Integer id) {
        Goods goods = goodsMapper.getGoodsById(id);
        log.info("controller getGoods {}", goods);
        return goods;
    }

    public void insertGoods(Goods goods) {
        goodsMapper.insertGoods(goods);
    }



}
