package com.example.gcsj4supermarket.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author li
 * @since 2024-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Shoppingcart对象", description="")
public class Shoppingcart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "购物车号")
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "商品id")
    private Integer goodsId;
    @ApiModelProperty(value = "商品数量")
    private Integer num;

    @ApiModelProperty(value = "商品名称")
    private String  sgoodsName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    public String getSgoodsName() {
        return sgoodsName;
    }

    public void setSgoodsName(String sgoodsName) {
        this.sgoodsName = sgoodsName;
    }

    @Override
    public String toString() {
        return "Shoppingcart{" +
            "id=" + id +
            ", userId=" + userId +
            ", goodsId=" + goodsId +
            ", num=" + num +
            ", sgoodsName=" + sgoodsName +
        "}";
    }
}
