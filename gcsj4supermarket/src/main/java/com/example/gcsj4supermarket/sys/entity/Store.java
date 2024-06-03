package com.example.gcsj4supermarket.sys.entity;

import lombok.Data;

@Data
public class Store {
    private Integer goodsShelfId; // 存储ID
    private Integer goodsId; // 商品ID
    private Integer goodsNumber; // 商品数量
    private String goodsKind; // 商品种类
    private Integer goodsStoreId; // 存储仓库编号
    private Integer goodsShelfFloor; // 层号
}
