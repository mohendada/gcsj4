package com.example.gcsj4supermarket.sys.service.impl;

import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.mapper.OrderMapper;
import com.example.gcsj4supermarket.sys.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
