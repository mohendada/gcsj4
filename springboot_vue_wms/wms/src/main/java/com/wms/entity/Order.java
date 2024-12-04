package com.wms.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2024-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("`order`")
@ApiModel(value="Order对象", description="")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    private Integer orderKind;

    private LocalDateTime orderTime;

    private Integer orderStatus;

    private String orderName;

    private Integer orderNumber;

    private Integer orderCreaterId;

    private LocalDateTime receivingTime;


}
