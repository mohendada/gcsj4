package com.example.gcsj4supermarket.sys.entity;

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
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderId;

    private Integer orderKind;

    private LocalDateTime orderTime;

    private Integer orderStatus;

    private String orderName;

    private Integer orderNumber;

    private Integer orderCreaterId;

    private LocalDateTime receivingTime;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getOrderKind() {
        return orderKind;
    }

    public void setOrderKind(Integer orderKind) {
        this.orderKind = orderKind;
    }
    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
    public Integer getOrderCreaterId() {
        return orderCreaterId;
    }

    public void setOrderCreaterId(Integer orderCreaterId) {
        this.orderCreaterId = orderCreaterId;
    }
    public LocalDateTime getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(LocalDateTime receivingTime) {
        this.receivingTime = receivingTime;
    }

    @Override
    public String toString() {
        return "Order{" +
            "orderId=" + orderId +
            ", orderKind=" + orderKind +
            ", orderTime=" + orderTime +
            ", orderStatus=" + orderStatus +
            ", orderName=" + orderName +
            ", orderNumber=" + orderNumber +
            ", orderCreaterId=" + orderCreaterId +
            ", receivingTime=" + receivingTime +
        "}";
    }
}
