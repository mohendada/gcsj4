package com.example.gcsj4supermarket.sys.entity;

import lombok.Data;

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
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer orderId;

    private Integer orderKind;

    private LocalDateTime orderTime;

    private Integer orderStatus;

    private Integer orderName;

    private Integer orderNumber;

    private Integer orderCreaterId;

    private LocalDateTime receivingTime;

}