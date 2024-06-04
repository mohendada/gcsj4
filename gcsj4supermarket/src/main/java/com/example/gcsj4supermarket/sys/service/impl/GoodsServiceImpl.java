package com.example.gcsj4supermarket.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gcsj4supermarket.sys.entity.Goods;
import com.example.gcsj4supermarket.sys.mapper.GoodsMapper;
import com.example.gcsj4supermarket.sys.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> GetGoodsList() {
        return goodsMapper.getGoodsList();
    }

    @Override
    public Goods getGoodsById(Integer id) {
        return goodsMapper.getGoodsById(id);
    }

    @Override
    public void update(Goods goods) {
        goodsMapper.update(goods);
    }

    @Override
    public void insert(Goods goods) {
        int id = goodsMapper.selectId();
        goods.setGoodsId(id+1);
        goodsMapper.insert(goods);
    }

    @Override
    public void remove(Integer id) {
        goodsMapper.delete(id);
    }
}
