package com.example.gcsj4supermarket.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.entity.Refound;
import com.example.gcsj4supermarket.sys.mapper.GoodsMapper;
import com.example.gcsj4supermarket.sys.mapper.OrderMapper;
import com.example.gcsj4supermarket.sys.mapper.RefoundMapper;
import com.example.gcsj4supermarket.sys.mapper.SupplierMapper;
import com.example.gcsj4supermarket.sys.service.IRefoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    OrderMapper orderMapper;


    @Override
    public IPage<Refound> getAllRefounds(Page<Refound> page) {
        return refoundMapper.selectPage(page, null);
    }

    @Override
    @Transactional
    public void generate(int orderid) {
        Order order = orderMapper.getById(orderid);
        Refound refound = new Refound();
        refound.setReturnGoodsId(refoundMapper.selectMaxId() + 1);
        refound.setReturnGoodsTime(LocalDateTime.now());
        refound.setReturnGoodsName(order.getOrderName());
        refound.setReturnGoodsNumber(order.getOrderNumber());
        refound.setSupplierId((goodsMapper.selectByName(order.getOrderName())).getSupplierId());
        refound.setReturnStatue(1);
        refound.setReturnOrderId(order.getOrderId());
        refound.setReturnOrderStatus(order.getOrderStatus());

        //todo 处理订单表状态
        refoundMapper.insert(refound);
        orderMapper.deleteOrder(order.getOrderId());
    }

    @Transactional
    @Override
    public void request(Integer orderid) {
        Order order = orderMapper.getById(orderid);
        Refound refound = new Refound();
        refound.setReturnGoodsId(refoundMapper.selectMaxId() + 1);
        refound.setReturnGoodsTime(LocalDateTime.now());
        refound.setReturnGoodsName(order.getOrderName());
        refound.setReturnGoodsNumber(order.getOrderNumber());
        refound.setSupplierId((goodsMapper.selectByName(order.getOrderName())).getSupplierId());
        refound.setReturnStatue(0);
        refound.setReturnOrderStatus(order.getOrderStatus());

        int status = order.getOrderStatus();
        if (status != 1) {
            order.setOrderStatus(-1);
            orderMapper.update(order);
            refoundMapper.insert(refound);
        } else {         //直接删除
            refoundMapper.insert(refound);
            orderMapper.deleteOrder(order.getOrderId());
        }
    }

    @Transactional
    @Override
    public void confirm(Integer orderid, Integer status) {
        if (status == 1) { //同意
            Order order = orderMapper.getById(orderid);
            Refound refound = refoundMapper.searchByOrderId(order.getOrderId());
            refound.setReturnStatue(1);
            refoundMapper.updateStatus(refound.getReturnGoodsId(),refound.getReturnStatue());
            orderMapper.deleteById(orderid);
        } else if (status == 0) {//不同意
            Order order = orderMapper.getById(orderid);
            Refound refound = refoundMapper.searchByOrderId(orderid);
            order.setOrderStatus(refound.getReturnOrderStatus());
            orderMapper.update(order);
            refoundMapper.deleteRefound(refound.getReturnGoodsId());

        }
    }
}
