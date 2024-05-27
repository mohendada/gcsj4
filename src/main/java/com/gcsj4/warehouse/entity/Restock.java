package com.gcsj4.warehouse.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Restock {
    private Integer restockId; // 进货编号ID
    private Date restockTime; // 进货时间
    private String restockGoodsName; // 货品名称
    private Integer restockGoodsNumber; // 货品数量
    private Integer supplierId; // 供货商编号
    private Integer restockStatue; // 订单状态（0：未完成1：已完成）
}
