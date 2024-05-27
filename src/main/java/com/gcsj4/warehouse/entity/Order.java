package com.gcsj4.warehouse.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer orderId; // 订单编号ID
    private Integer orderKind; // 订单类型
    private Date orderTime; // 订单创建时间
    private Integer orderStatus; // 订单状态
    private String orderName; // 订单商品名称
    private Integer orderNumber; // 订单商品数量
    private Integer orderCreaterId; // 订单创建者编号
    private Date receivingTime; // 收货时间（默认为创建时间）
}
