package com.example.gcsj4supermarket.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Refound implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableField("return_goods_id")
    private Integer returnGoodsId; // 退货编号ID
    @TableField("return_goods_time")
    private LocalDateTime returnGoodsTime; // 退货时间
    @TableField("return_goods_name")
    private String returnGoodsName; // 货品名称
    @TableField("return_id")
    private Integer returnGoodsNumber; // 货品数量
    @TableField("supplier_id")
    private Integer supplierId; // 供货商编号
    @TableField("return_statue")
    private Integer returnStatue; // 订单状态（0：未完成1：已完成）
}
