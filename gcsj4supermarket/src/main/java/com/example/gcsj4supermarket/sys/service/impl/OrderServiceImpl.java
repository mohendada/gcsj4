package com.example.gcsj4supermarket.sys.service.impl;

import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.mapper.OrderMapper;
import com.example.gcsj4supermarket.sys.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public int generateOrder(Order order) {
        order.setOrderId(orderMapper.maxId()+1);
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
}
