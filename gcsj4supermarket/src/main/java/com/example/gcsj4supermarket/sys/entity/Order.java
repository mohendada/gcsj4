package com.example.gcsj4supermarket.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("'order'")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @TableField("order_id")
    private Integer orderId;
    @TableField("order_kind")
    private Integer orderKind;
    @TableField("order_time")
    private LocalDateTime orderTime;
    @TableField("order_status")
    private Integer orderStatus;
    @TableField("order_name")
    private Integer orderName;
    @TableField("order_number")
    private Integer orderNumber;
    @TableField("order_creater_id")
    private Integer orderCreaterId;
    @TableField("receiving_time")
    private LocalDateTime receivingTime;

}