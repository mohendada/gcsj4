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
public class Restock implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer restockId;

    private LocalDateTime restockTime;

    private String restockGoodsName;

    private Integer restockGoodsNumber;

    private Integer supplierId;

    private Integer restockStatue;

    public Integer getRestockId() {
        return restockId;
    }

    public void setRestockId(Integer restockId) {
        this.restockId = restockId;
    }
    public LocalDateTime getRestockTime() {
        return restockTime;
    }

    public void setRestockTime(LocalDateTime restockTime) {
        this.restockTime = restockTime;
    }
    public String getRestockGoodsName() {
        return restockGoodsName;
    }

    public void setRestockGoodsName(String restockGoodsName) {
        this.restockGoodsName = restockGoodsName;
    }
    public Integer getRestockGoodsNumber() {
        return restockGoodsNumber;
    }

    public void setRestockGoodsNumber(Integer restockGoodsNumber) {
        this.restockGoodsNumber = restockGoodsNumber;
    }
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
    public Integer getRestockStatue() {
        return restockStatue;
    }

    public void setRestockStatue(Integer restockStatue) {
        this.restockStatue = restockStatue;
    }

    @Override
    public String toString() {
        return "Restock{" +
            "restockId=" + restockId +
            ", restockTime=" + restockTime +
            ", restockGoodsName=" + restockGoodsName +
            ", restockGoodsNumber=" + restockGoodsNumber +
            ", supplierId=" + supplierId +
            ", restockStatue=" + restockStatue +
        "}";
    }
}
