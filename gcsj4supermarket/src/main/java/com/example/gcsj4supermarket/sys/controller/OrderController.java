package com.example.gcsj4supermarket.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Employee;
import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result<Integer> GenerateOrder(@RequestBody Order order, @RequestParam("time")LocalDateTime time) {
        log.info("controller order: {},time: {}", order,time);
        int num = orderService.generateOrder(order,time);
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

//    /**
//     * 获取所有订单
//     * @return list<order>
//     */
//    @RequestMapping("/GetAllOrder")
//    public Result<Object> GetAllOrder() {
//        List<Order> orders = orderService.GetAllOrders();
//        return Result.success(orders);
//    }

    /**
     * 获取所有订单（分页）
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示数量
     * @return 分页后的订单列表
     */
    @GetMapping("/GetAllOrder")
    public Result<Object> GetAllOrder(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Order> page = new Page<>(pageNum, pageSize);
        IPage<Order> orders = orderService.getAllOrders(page);
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
    public Result<?> GetByStatus(@RequestParam("orderStatus") Integer status){
        List<Order> orders = orderService.GetByStatus(status);
        return Result.success(orders);
    }



    @GetMapping("/GetAllStatusList")
    public Result<?> GetAllStatusList( @RequestParam("pageNo")Long pageNo,
                                       @RequestParam("pageSize")Long pageSize,
                                       @RequestParam(defaultValue = "0")Integer createId
    ){

        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Order::getOrderTime, Order::getOrderStatus, Order::getOrderCreaterId); // 选择所有需要的字段
        wrapper.groupBy(Order::getOrderTime, Order::getOrderStatus, Order::getOrderCreaterId);
        if(createId !=0 ) {
            wrapper.eq(Order::getOrderCreaterId, createId);
        }
        System.out.println("createId :: "+createId);
//        LambdaQueryWrapper<Order> wrapper=new LambdaQueryWrapper<>();
//        wrapper.distinct(true);
//        wrapper.select(Order::getOrderTime);
        Page<Order> page=new Page<>(pageNo,pageSize);
        orderService.page(page,wrapper);
        //放数据
        Map<String,Object> data=new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        System.out.println("GetAllStatusList");
        return Result.success(data);

    }
    @GetMapping("/GetOrderListByTime")
    public Result<?> GetOrderListByTime(@RequestParam(value = "date")String s)
    {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("GetOrderListByTime:" + zonedDateTime);
        ZonedDateTime desiredZoneDateTime = zonedDateTime.withZoneSameInstant(java.time.ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = desiredZoneDateTime.format(formatter);
        System.out.println("Date:" + formattedDateTime);
        return Result.success(orderService.GetOrderListByTime(formattedDateTime));
    }

    @PutMapping("updateOrderStatus")
    public Result<?> updateOrderStatus(@RequestParam(value = "date")String s)
    {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("updateOrderStatus:" + zonedDateTime);
        ZonedDateTime desiredZoneDateTime = zonedDateTime.withZoneSameInstant(java.time.ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = desiredZoneDateTime.format(formatter);
        System.out.println("Date:" + formattedDateTime);
        List<Order> orders =  orderService.GetOrderListByTime(formattedDateTime);
        for (Order order : orders) {
            orderService.updateStatus(order.getOrderId());
        }
        return Result.success();
    }

}
