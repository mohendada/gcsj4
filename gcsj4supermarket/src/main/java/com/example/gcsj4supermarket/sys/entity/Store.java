package com.example.gcsj4supermarket.sys.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer goodsShelfId;

    private Integer goodsId;

    private Integer goodsNumber;

    private String goodsKind;

    private Integer goodsStoreId;

    private Integer goodsShelfFloor;

    public Integer getGoodsShelfId() {
        return goodsShelfId;
    }

    public void setGoodsShelfId(Integer goodsShelfId) {
        this.goodsShelfId = goodsShelfId;
    }
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }
    public String getGoodsKind() {
        return goodsKind;
    }

    public void setGoodsKind(String goodsKind) {
        this.goodsKind = goodsKind;
    }
    public Integer getGoodsStoreId() {
        return goodsStoreId;
    }

    public void setGoodsStoreId(Integer goodsStoreId) {
        this.goodsStoreId = goodsStoreId;
    }
    public Integer getGoodsShelfFloor() {
        return goodsShelfFloor;
    }

    public void setGoodsShelfFloor(Integer goodsShelfFloor) {
        this.goodsShelfFloor = goodsShelfFloor;
    }

    @Override
    public String toString() {
        return "Store{" +
            "goodsShelfId=" + goodsShelfId +
            ", goodsId=" + goodsId +
            ", goodsNumber=" + goodsNumber +
            ", goodsKind=" + goodsKind +
            ", goodsStoreId=" + goodsStoreId +
            ", goodsShelfFloor=" + goodsShelfFloor +
        "}";
    }
}
