package com.example.gcsj4supermarket.sys.controller;

import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@RestController
@Slf4j
@RequestMapping("/sys/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/GenerateOrder")
    public Result<Integer> GenerateOrder(@RequestBody Order order) {
        log.info("controller order: {}", order);
        int num = orderService.generateOrder(order);
        if (num != -1) {
            return Result.success(num);
        } else return Result.fail();
    }

    @RequestMapping("/UpdateOrder")
    public Result<Integer> UpdateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
        return Result.success();
    }

    @RequestMapping("/DeleteOrder")
    public Result<Integer> DeleteOrder(@RequestParam("id") Integer id) {
        orderService.delorder(id);
        return Result.success();
    }

    @RequestMapping("/GetOrderById")
    public Result<Order> GetOrderById(@RequestParam("id") Integer id) {
        return Result.success(orderService.getOrderByid(id));
    }

    @RequestMapping("/GetAllOrder")
    public Result<Object> GetAllOrder() {
        List<Order> orders = orderService.GetAllOrders();
        return Result.success(orders);
    }

    @RequestMapping("/OrderStatus")
    public Result<?> OrderStatus(@RequestParam("orderId") Integer id){
        orderService.updateStatus(id);
        return Result.success();
    }

    @RequestMapping("/GetByStatus")
    public Result<?> GetByStatus(@RequestParam("orderId") Integer status){
        List<Order> orders = orderService.GetByStatus(status);
        return Result.success(orders);
    }

}
