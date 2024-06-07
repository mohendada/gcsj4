package com.example.gcsj4supermarket.sys.service.impl;

import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.entity.Store;
import com.example.gcsj4supermarket.sys.mapper.GoodsMapper;
import com.example.gcsj4supermarket.sys.mapper.StoreMapper;
import com.example.gcsj4supermarket.sys.service.IStoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements IStoreService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StoreMapper storeMapper;

    @Override
    public void finishOrder(Order order) {
        int num = order.getOrderNumber();
        int id = storeMapper.selectById(goodsMapper.selectByName(order.getOrderName())).getGoodsId();
        storeMapper.storeDeduce(id,num);
    }
}
