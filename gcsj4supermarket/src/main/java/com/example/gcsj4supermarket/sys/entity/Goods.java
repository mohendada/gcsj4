package com.example.gcsj4supermarket.sys.entity;


import lombok.Data;

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
@Data
public class Goods implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer goodsId;

    private String goodsName;

    private Float goodsPrice;

    private String goodsPhoto;

    private Integer supplierId;

    private Integer goodsStatus;

}
