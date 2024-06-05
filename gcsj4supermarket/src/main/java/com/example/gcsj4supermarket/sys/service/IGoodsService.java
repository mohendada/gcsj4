package com.example.gcsj4supermarket.sys.service;

import com.example.gcsj4supermarket.sys.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */

public interface IGoodsService extends IService<Goods> {
    List<Goods> GetGoodsList();

    Goods getGoodsById(Integer id);

    void update(Goods goods);

    void insert(Goods goods);

    void remove(Integer id);
}