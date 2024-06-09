package com.example.gcsj4supermarket.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.entity.Refound;
import com.example.gcsj4supermarket.sys.mapper.GoodsMapper;
import com.example.gcsj4supermarket.sys.mapper.RefoundMapper;
import com.example.gcsj4supermarket.sys.mapper.SupplierMapper;
import com.example.gcsj4supermarket.sys.service.IRefoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Service
public class RefoundServiceImpl extends ServiceImpl<RefoundMapper, Refound> implements IRefoundService {

    @Autowired
    RefoundMapper refoundMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    SupplierMapper supplierMapper;


    @Override
    public IPage<Refound> getAllRefounds(Page<Refound> page) {
        return refoundMapper.selectPage(page, null);
    }

    @Override
    public void generate(Order order) {
        Refound refound = new Refound();
        refound.setReturnGoodsId(refoundMapper.selectMaxId()+1);
        refound.setReturnGoodsTime(LocalDateTime.now());
        refound.setReturnGoodsName(order.getOrderName());
        refound.setReturnGoodsNumber(order.getOrderNumber());
        refound.setSupplierId((goodsMapper.selectByName(order.getOrderName())).getSupplierId());
        refound.setReturnStatue(0);
        //todo 处理订单表状态
        refoundMapper.insert(refound);
    }
}
