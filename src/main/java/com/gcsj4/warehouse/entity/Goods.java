package com.gcsj4.warehouse.entity;

import lombok.Data;

@Data
public class Goods {
    private Integer goodsId; // 商品ID
    private String goodsName; // 商品名称
    private Float goodsPrice; // 商品价格
    private String goodsPhoto; // 商品图片
    private Integer supplierId; // 供货商编号
}

