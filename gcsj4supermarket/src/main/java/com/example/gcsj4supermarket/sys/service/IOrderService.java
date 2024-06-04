package com.example.gcsj4supermarket.sys.service;

import com.example.gcsj4supermarket.sys.entity.Order;
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
public interface IOrderService extends IService<Order> {

    int generateOrder(Order order);

    void updateOrder(Order order);

    void delorder(Integer id);

    Order getOrderByid(Integer id);

    List<Order> GetAllOrders();
}
