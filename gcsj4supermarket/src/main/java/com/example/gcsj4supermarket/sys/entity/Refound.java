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
public class Refound implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer returnGoodsId;

    private LocalDateTime returnGoodsTime;

    private String returnGoodsName;

    private Integer returnGoodsNumber;

    private Integer returnId;

    private Integer returnStatue;

    public Integer getReturnGoodsId() {
        return returnGoodsId;
    }

    public void setReturnGoodsId(Integer returnGoodsId) {
        this.returnGoodsId = returnGoodsId;
    }
    public LocalDateTime getReturnGoodsTime() {
        return returnGoodsTime;
    }

    public void setReturnGoodsTime(LocalDateTime returnGoodsTime) {
        this.returnGoodsTime = returnGoodsTime;
    }
    public String getReturnGoodsName() {
        return returnGoodsName;
    }

    public void setReturnGoodsName(String returnGoodsName) {
        this.returnGoodsName = returnGoodsName;
    }
    public Integer getReturnGoodsNumber() {
        return returnGoodsNumber;
    }

    public void setReturnGoodsNumber(Integer returnGoodsNumber) {
        this.returnGoodsNumber = returnGoodsNumber;
    }
    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }
    public Integer getReturnStatue() {
        return returnStatue;
    }

    public void setReturnStatue(Integer returnStatue) {
        this.returnStatue = returnStatue;
    }

    @Override
    public String toString() {
        return "Refound{" +
            "returnGoodsId=" + returnGoodsId +
            ", returnGoodsTime=" + returnGoodsTime +
            ", returnGoodsName=" + returnGoodsName +
            ", returnGoodsNumber=" + returnGoodsNumber +
            ", returnId=" + returnId +
            ", returnStatue=" + returnStatue +
        "}";
    }
}
