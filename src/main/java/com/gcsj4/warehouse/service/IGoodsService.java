package com.gcsj4.warehouse.service;


import com.gcsj4.warehouse.entity.Goods;

import java.util.List;

public interface IGoodsService {
    List<Goods> GetGoodsList();

    Goods getGoodsById(Integer id);
}
