package com.gcsj4.warehouse.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Refound {
    private Integer returnGoodsId; // 退货编号ID
    private Date returnGoodsTime; // 退货时间
    private String returnGoodsName; // 货品名称
    private Integer returnGoodsNumber; // 货品数量
    private Integer returnId; // 供货商编号
    private Integer returnStatue; // 订单状态（0：未完成1：已完成）
}
