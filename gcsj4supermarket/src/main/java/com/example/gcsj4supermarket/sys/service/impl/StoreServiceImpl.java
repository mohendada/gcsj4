package com.example.gcsj4supermarket.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.entity.Store;
import com.example.gcsj4supermarket.sys.mapper.GoodsMapper;
import com.example.gcsj4supermarket.sys.mapper.StoreMapper;
import com.example.gcsj4supermarket.sys.service.IStoreService;
import jakarta.annotation.Resource;
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
    @Resource
    private StoreMapper storeMapper;
    @Override
    public IPage pageCC(IPage<Store> page, Wrapper<Store> wrapper) {
        return storeMapper.pageCC(page,wrapper);
    }
    @Autowired
    private GoodsMapper goodsMapper;

//    @Autowired
//    private StoreMapper storeMapper;

    @Override
    public IPage pageCC2(IPage<Store> page, Wrapper wrapper) {

        return storeMapper.pageCC(page,wrapper);
    }
    @Override
    public void finishOrder(Order order) {
        int num = order.getOrderNumber();
        int id = storeMapper.selectById(goodsMapper.selectByName(order.getOrderName())).getGoodsId();
        storeMapper.storeDeduce(id,num);
    }
}
