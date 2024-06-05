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
 * @author Zzuiding
 * @since 2024-05-27
 */
@RestController
@Slf4j
@RequestMapping("/sys/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 生成订单
     * @param order (必须字段 kind,name,number,creater_id)
     * @return
     */

    @RequestMapping("/GenerateOrder")
    public Result<Integer> GenerateOrder(@RequestBody Order order) {
        log.info("controller order: {}", order);
        int num = orderService.generateOrder(order);
        if (num != -1) {
            return Result.success(num);
        } else return Result.fail();
    }

    /**
     * 修改订单信息
     * @param order
     * @return
     */
    @RequestMapping("/UpdateOrder")
    public Result<Integer> UpdateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
        return Result.success();
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @RequestMapping("/DeleteOrder")
    public Result<Integer> DeleteOrder(@RequestParam("id") Integer id) {
        orderService.delorder(id);
        return Result.success();
    }

    /**
     * 根据id查询订单
     * @param id
     * @return order
     */
    @RequestMapping("/GetOrderById")
    public Result<Order> GetOrderById(@RequestParam("id") Integer id) {
        return Result.success(orderService.getOrderByid(id));
    }

    /**
     * 获取所有订单
     * @return list<order>
     */
    @RequestMapping("/GetAllOrder")
    public Result<Object> GetAllOrder() {
        List<Order> orders = orderService.GetAllOrders();
        return Result.success(orders);
    }

    /**
     * 修改订单状态
     * @param id
     * @return
     */
    @RequestMapping("/OrderStatus")
    public Result<?> OrderStatus(@RequestParam("orderId") Integer id){
        orderService.updateStatus(id);
        return Result.success();
    }

    /**
     * 根据当前状态获取订单信息
     * @param status
     * @return
     */
    @RequestMapping("/GetByStatus")
    public Result<?> GetByStatus(@RequestParam("orderId") Integer status){
        List<Order> orders = orderService.GetByStatus(status);
        return Result.success(orders);
    }

}
