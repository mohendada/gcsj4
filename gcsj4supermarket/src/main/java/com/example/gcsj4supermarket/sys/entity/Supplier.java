package com.example.gcsj4supermarket.sys.entity;
import io.swagger.annotations.ApiModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2024-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Supplier对象", description="")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "supplier_id", type = IdType.AUTO)
    @ApiModelProperty(value = "供应商编号")
    private Integer supplierId;
    @ApiModelProperty(value = "供应商电话")
    private String supplierPhoneNumber;
    @ApiModelProperty(value = "供应商地址")
    private String supplierAddress;
    @ApiModelProperty(value = "公司名称")
    private String supplierName;


}

