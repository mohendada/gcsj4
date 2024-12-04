package com.wms.entity;

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
    @TableId(value = "goods_Id", type = IdType.AUTO)
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


}
