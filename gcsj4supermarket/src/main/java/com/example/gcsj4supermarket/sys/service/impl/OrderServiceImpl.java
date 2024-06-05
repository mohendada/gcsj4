package com.example.gcsj4supermarket.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.mapper.OrderMapper;
import com.example.gcsj4supermarket.sys.mapper.StoreMapper;
import com.example.gcsj4supermarket.sys.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zzuiding
 * @since 2024-05-27
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private StoreMapper storeMapper;

    @Override
    public int generateOrder(Order order) {
        order.setOrderId(orderMapper.maxId() + 1);
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(1);
        if (order.getOrderNumber() > storeMapper.selectById(order.getOrderId()).getGoodsNumber()) {
            return -1;
        }
        log.info(String.valueOf(order));
        return orderMapper.insert(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.update(order);
    }

    @Override
    public void delorder(Integer id) {
        orderMapper.deleter(id);
    }

    @Override
    public Order getOrderByid(Integer id) {
        return orderMapper.getById(id);
    }

    @Override
    public List<Order> GetAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public void updateStatus(Integer id) {
        Order order = orderMapper.getById(id);
        order.setOrderStatus(order.getOrderStatus()+1);
        orderMapper.update(order);
    }

    @Override
    public List<Order> GetByStatus(Integer status) {
        return orderMapper.GetByStatus(status);
    }
}
