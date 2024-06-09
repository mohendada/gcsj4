package com.example.gcsj4supermarket.sys.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Mapper
@Data
public class Goods implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableField("goods_Id")
    private Integer goodsId;

    @TableField("goods_name")
    private String goodsName;

    @TableField("goods_price")
    private Float goodsPrice;

    @TableField("goods_photo")
    private String goodsPhoto;

    @TableField("supplier_id")
    private Integer supplierId;

    @TableField("goods_status")
    private Integer goodsStatus;

}
