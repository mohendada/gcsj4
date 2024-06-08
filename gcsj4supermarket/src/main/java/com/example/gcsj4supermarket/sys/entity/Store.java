package com.example.gcsj4supermarket.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("store")
@Data
public class Store {
    @TableField("goods_shelf_id")
    private Integer goodsShelfId; // 存储ID
    @TableField("goods_id")
    private Integer goodsId; // 商品ID
    @TableField("goods_number")
    private Integer goodsNumber; // 商品数量
    @TableField("goods_kind")
    private String goodsKind; // 商品种类
    @TableField("goods_store_id")
    private Integer goodsStoreId; // 存储仓库编号
    @TableField("goods_shelf_floor")
    private Integer goodsShelfFloor; // 层号
}
