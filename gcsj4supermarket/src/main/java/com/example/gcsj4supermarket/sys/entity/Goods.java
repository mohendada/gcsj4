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
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer goodsId;

    private String goodsName;

    private Float goodsPrice;

    private String goodsPhoto;

    private Integer supplierId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public Float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    public String getGoodsPhoto() {
        return goodsPhoto;
    }

    public void setGoodsPhoto(String goodsPhoto) {
        this.goodsPhoto = goodsPhoto;
    }
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "Goods{" +
            "goodsId=" + goodsId +
            ", goodsName=" + goodsName +
            ", goodsPrice=" + goodsPrice +
            ", goodsPhoto=" + goodsPhoto +
            ", supplierId=" + supplierId +
        "}";
    }
}
