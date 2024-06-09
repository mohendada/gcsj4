package com.example.gcsj4supermarket.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author wms
 * @since 2024-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Store对象", description="")
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "货架编号")
    private Integer goodsShelfId;
    @TableId(value = "goods_id", type = IdType.AUTO)
    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;//主键
    @ApiModelProperty(value = "商品数量")
    private Integer goodsNumber;
    @ApiModelProperty(value = "商品种类")
    private String goodsKind;
    @ApiModelProperty(value = "存储仓库编号")
    private Integer goodsStoreId;
    @ApiModelProperty(value = "层号")
    private Integer goodsShelfFloor;


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
